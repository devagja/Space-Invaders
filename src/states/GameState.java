package states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.util.ArrayList;
import java.util.List;

import gameObject.GameObject;
import gameObject.Player;
import graphics.Animation;
import graphics.Assets;
import graphics.Sound;
import input.Keyboard;
import listenerEndGame.EndGameEvent;
import listenerEndGame.EndGameListener;
import listenerEndGame.EndGameManager;
import math.Vector2D;
import objectLaunchers.AsteroidLauncher;
import objectLaunchers.EnemiesLauncher;
import objectLaunchers.PickupsLauncher;
import version_00.Constantes;

public class GameState implements EndGameListener {

	private static Player player;

	// Package debido a que las escenas van a usarlo
	static List<GameObject> objetos = new ArrayList<GameObject>();
	static List<Animation> animations = new ArrayList<Animation>();

	private static AsteroidLauncher la = new AsteroidLauncher();
	private static EnemiesLauncher le = new EnemiesLauncher();
	private static PickupsLauncher lp = new PickupsLauncher();

	static int puntuacion = Constantes.SCORE;
	static int vida = Constantes.LIVES;

	private static Sound musica = new Sound(Assets.audioMusica);
	static Sound gameOver = new Sound(Assets.audioGameOver);

	private static boolean musicaWasStarted = false;

	private static boolean[] iswave = new boolean[10];

	int i = 0;

	public GameState() {
		EndGameManager.addEndGameListener(this);
		player = new Player(Assets.player, Constantes.PLAYER_INIT_POSICION);
		player.setPosicion(new Vector2D(Constantes.WIDTH / 2 - 20, Constantes.HEIGHT - 100));

		objetos.add(player);
	}

	private static void initValues() {
		Constantes.ENEMIES_GENERACION_TIME_MAX = 1500;
		Constantes.ENEMIES_GENERACION_TIME_MIN = 1000;
		Constantes.ENEMIES_PAUSE = 750;
		Constantes.ENEMIES_SHOOT_PROBABILITY = 0.001;
		Constantes.ENEMIES_SHOOT_VEL = 2;
		Constantes.SHOOT_VEL = 5;
		Constantes.ASTEROID_VEL = 2;
		Constantes.FIRERATE = 690;
		Constantes.PLAYER_VEL = 3;
		Constantes.MEDEVAC_DROP_PROBABILITY = 0.01;
		Constantes.VEL_GENERACION_ASTEROID_MAX = 2000;
		Constantes.VEL_GENERACION_ASTEROID_MIN = 1000;

	}

	public static void init() {
		initValues();
		objetos.clear();
		player.setPosicion(new Vector2D(Constantes.WIDTH / 2 - 20, Constantes.HEIGHT - 100));

		objetos.add(player);
		le.comenzar();
		la.comenzar();
		if (!musicaWasStarted) {
			musica.loop();
			musicaWasStarted = true;
		}
		for (int i = 0; i < iswave.length; i++) {
			iswave[i] = false;

		}
	}

	static boolean isGameOver = false;

	private void waves() {

		// OLEADAS ---------------------------------------------
		if (puntuacion >= 1500 && !iswave[0])

		{
			iswave[0] = true;

			Constantes.ENEMIES_GENERACION_TIME_MAX -= 500;
			Constantes.ENEMIES_PAUSE -= 200;
			Constantes.ENEMIES_GENERACION_TIME_MIN -= 250;

		}
		if (puntuacion >= 4000 && !iswave[1]) {

			iswave[1] = true;
			Constantes.FIRERATE -= 40;

			Constantes.ENEMIES_GENERACION_TIME_MAX -= 50;
			Constantes.ENEMIES_PAUSE -= 50;

		}
		if (puntuacion >= 8000 && !iswave[2]) {
			iswave[2] = true;
			lp.comenzar();

			Constantes.FIRERATE -= 100;
			Constantes.VEL_GENERACION_ASTEROID_MAX -= 500;
			Constantes.ENEMIES_PAUSE -= 50;
			Constantes.ENEMIES_SHOOT_PROBABILITY += 0.001;

		}
		if (puntuacion >= 12000 && !iswave[3]) {
			iswave[3] = true;
			Constantes.FIRERATE -= 100;
			Constantes.ASTEROID_VEL += 2;
			Constantes.ENEMIES_SHOOT_PROBABILITY += 0.002;
			Constantes.ENEMIES_GENERACION_TIME_MIN -= 200;

		}
		if (puntuacion >= 16000 && !iswave[4]) {
			iswave[4] = true;

			Constantes.FIRERATE -= 50;
			Constantes.VEL_GENERACION_ASTEROID_MIN -= 300;
			Constantes.ENEMIES_SHOOT_VEL += 0.5;
			Constantes.SHOOT_VEL += 1;

		}

		if (puntuacion >= 20000 && !iswave[5]) {
			iswave[5] = true;
			Constantes.FIRERATE -= 10;

			Constantes.ASTEROID_VEL += 1;

			Constantes.VEL_GENERACION_ASTEROID_MAX -= 200;

			Constantes.ENEMIES_GENERACION_TIME_MAX -= 100;

		}
		if (puntuacion >= 24000 && !iswave[6]) {
			iswave[6] = true;

			Constantes.SHOOT_VEL += 1;

			Constantes.ENEMIES_GENERACION_TIME_MIN -= 100;

		}
		if (puntuacion >= 28000 && !iswave[7]) {
			iswave[7] = true;

			Constantes.SHOOT_VEL += 1;
			Constantes.ASTEROID_VEL += 0.8;

			Constantes.VEL_GENERACION_ASTEROID_MAX -= 200;

		}
		if (puntuacion >= 32000 && !iswave[8]) {
			iswave[8] = true;

			Constantes.FIRERATE -= 70;

			Constantes.VEL_GENERACION_ASTEROID_MAX -= 100;

			Constantes.ENEMIES_GENERACION_TIME_MIN -= 50;

		}
		if (puntuacion >= 36000 && !iswave[9]) {
			iswave[9] = true;
			Constantes.VEL_GENERACION_ASTEROID_MIN -= 200;

		}
	}

	public void update() {
		// no se puede usar for each debido para recorrer la coleccion se basa en ella
		// misma, por lo tanto no permite modificarla.

		for (int i = 0; i < objetos.size(); i++) {
			objetos.get(i).update();

		}

		for (int i = 0; i < animations.size(); i++) {
			animations.get(i).update();
			if (!animations.get(i).isRunning()) {
				animations.remove(i);

			}

		}
		i++;

		if (Keyboard.RESTART && vida <= 0) {
			vida = Constantes.LIVES;
			puntuacion = Constantes.SCORE;
			gameOver.stop();
			isGameOver = false;

			init();
		} else if (vida <= 0 && isGameOver == false) {
			EndGameManager.fireEndGameListeners(true);
			/*
			 * la.stop(); le.stop(); lp.stop();
			 */

			gameOver.play();
		}
		waves();

		// --------- ---------------------------------------------

		// Comprueba que no haya ningun meteorito para asi iniciar una oleada nueva
		/*
		 * for (int i = 0; i < objetos.size(); i++) { if (objetos.get(i) instanceof
		 * Asteroid) { return; } }
		 */
		// startWave();
	}

	public void draw(Graphics g) {
		// Elimina los dientes de sierra de las imagenes que rotan(asteroides)
		Graphics2D g2d = (Graphics2D) g;
		// Poner fondo scroll baja los fps constantemente solucionar
		g2d.setPaint(new TexturePaint(Assets.fondos.get(Constantes.BACKGROUND),
				new Rectangle(0, i, Assets.fondos.get(Constantes.BACKGROUND).getWidth(),
						Assets.fondos.get(Constantes.BACKGROUND).getHeight())));
		g2d.fillRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT);

		for (int i = 0; i < objetos.size(); i++) {
			objetos.get(i).draw(g);
		}

		Animation animacion;
		for (int i = 0; i < animations.size(); i++) {
			animacion = animations.get(i);
			g2d.drawImage(animacion.getFotogramaActual(), (int) animacion.getPosicion().getX(),
					(int) animacion.getPosicion().getY(), null);

		}
		drawScore(g);
		drawLives(g);
		if (vida <= 0) {
			drawGameOver(g);
		}
	}

	private static void drawGameOver(Graphics g) {
		Vector2D pos = new Vector2D((Constantes.WIDTH - Assets.gameover.getWidth()) / 2,
				(Constantes.HEIGHT - Assets.gameover.getHeight()) / 2);

		g.drawImage(Assets.gameover, (int) pos.getX(), (int) pos.getY(), null);

	}

	private static void drawScore(Graphics g) {
		Vector2D pos = new Vector2D(25, 25);

		String puntuacionToString = Integer.toString(puntuacion);
		for (int i = 0; i < puntuacionToString.length(); i++) {
			g.drawImage(Assets.numeros.get(Integer.parseInt(puntuacionToString.substring(i, i + 1))), (int) pos.getX(),
					(int) pos.getY(), null);

			pos.setX(pos.getX() + 20);
		}
	}

	private static void drawLives(Graphics g) {
		Vector2D pos = new Vector2D(5, Constantes.HEIGHT - Assets.lives.get(0).getHeight() - 50);
		for (int i = 1; i <= vida; i++) {

			if (i % 2 == 0) {
				g.drawImage(Assets.lives.get(0), (int) pos.getX(), (int) pos.getY(), null);

			}

			pos.setX(pos.getX() + 20);

		}

		if (vida % 2 != 0.0) {
			g.drawImage(Assets.lives.get(1), (int) pos.getX(), (int) pos.getY(), null);

		}

	}

	/*
	 * // Preguntar // Al iniciar la animacion se le pasa una unica direcion en la
	 * cual se muestran todas las animaciones de motor // El fuego no sigue la nave
	 * por ese motivo public static void playMotor(Vector2D posicion) {
	 * animations.add(new AnimacionDinamica(Assets.motorJugador,
	 * Constantes.VEL_ANIMACION_EXPLOSION, posicion.sum(new
	 * Vector2D(Assets.player.getWidth() / 5, Assets.motorJugador.get(0).getHeight()
	 * - 3))));
	 * 
	 * for (int i = 0; i < Assets.motorJugador.size(); i++) {
	 * Assets.motorJugador.get(i) } }
	 */

	public static void addPuntuacion(int score) {
		puntuacion += score;
	}

	public static List<GameObject> getObjetos() {
		return objetos;
	}

	public static void setObjetos(ArrayList<GameObject> objetos) {
		GameState.objetos = objetos;
	}

	public static int getPuntuacion() {
		return puntuacion;
	}

	public static void setPuntuacion(int puntuacion) {
		GameState.puntuacion = puntuacion;
	}

	public static int getVida() {
		return vida;
	}

	public static void setVida(int vida) {
		GameState.vida = vida;
	}

	public Sound getMusica() {
		return musica;
	}

	public void setMusica(Sound musica) {
		GameState.musica = musica;
	}

	public static AsteroidLauncher getLa() {
		return la;
	}

	public static void setLa(AsteroidLauncher la) {
		GameState.la = la;
	}

	public static EnemiesLauncher getLe() {
		return le;
	}

	public static void setLe(EnemiesLauncher le) {
		GameState.le = le;
	}

	public static PickupsLauncher getLp() {
		return lp;
	}

	public static void setLp(PickupsLauncher lp) {
		GameState.lp = lp;
	}

	public static List<Animation> getAnimations() {
		return animations;
	}

	public static void setAnimations(List<Animation> animations) {
		GameState.animations = animations;
	}

	@Override
	public void finJuego(EndGameEvent event) {

		isGameOver = true;
	}

}
