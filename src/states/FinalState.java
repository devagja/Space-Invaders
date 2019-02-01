package states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.util.ArrayList;
import java.util.List;

import gameObject.Boss;
import gameObject.GameObject;
import gameObject.Player;
import graphics.Animation;
import graphics.Assets;
import graphics.Sound;
import input.Keyboard;
import math.Vector2D;
import version_00.Constantes;
import version_00.Ventana;

public class FinalState {
	private Player player;
	private Boss boss;

	static int vidaBoss = Constantes.LIVES_BOSS;

	public Sound musica;

	private boolean musicaWasStarted = false;

	int i = 0;

	public FinalState() {
		player = new Player(Assets.player, Constantes.PLAYER_INIT_POSICION);
		boss = new Boss(Assets.boss, new Vector2D(100, 100));
		musica = new Sound(Assets.audioFinal);

	}

	public void init() {
		// solucionar, la constante se cambia de valor sienda final
		GameState.objetos.clear();
		vidaBoss = Constantes.LIVES_BOSS;

		player.setPosicion(new Vector2D(Constantes.WIDTH / 2 - 20, Constantes.HEIGHT - 100));
		boss.setPosicion(new Vector2D(100, 100));

		GameState.objetos.add(player);
		GameState.objetos.add(boss);

		if (!musicaWasStarted) {
			musica.loop();
			musicaWasStarted = true;
		}

	}

	public void update() {
		// no se puede usar for each debido para recorrer la coleccion se basa en ella
		// misma, por lo tanto no permite modificarla.

		for (int i = 0; i < GameState.objetos.size(); i++) {
			GameState.objetos.get(i).update();

		}

		for (int i = 0; i < GameState.animations.size(); i++) {
			GameState.animations.get(i).update();
			if (!GameState.animations.get(i).isRunning()) {
				GameState.animations.remove(i);

			}

		}
		i++;

		if (Keyboard.RESTART && GameState.getVida() <= 0) {
			GameState.isGameOver = false;
			GameState.setVida(Constantes.LIVES);
			GameState.setPuntuacion(Constantes.SCORE);
			Ventana.setInitFinal(false);

			GameState.init();

		} else if (GameState.getVida() <= 0 && GameState.isGameOver == false) {
			GameState.isGameOver = true;
			GameState.gameOver.play();
			System.out.println("has muerto");
		}

	}

	public void draw(Graphics g) {
		// Elimina los dientes de sierra de las imagenes que rotan(asteroides)
		Graphics2D g2d = (Graphics2D) g;
		// Poner fondo scroll baja los fps constantemente solucionar
		g2d.setPaint(new TexturePaint(Assets.fondos.get(Constantes.BACKGROUND),
				new Rectangle(0, i, Assets.fondos.get(Constantes.BACKGROUND).getWidth(),
						Assets.fondos.get(Constantes.BACKGROUND).getHeight())));
		g2d.fillRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT);

		for (int i = 0; i < GameState.objetos.size(); i++) {
			GameState.objetos.get(i).draw(g);
		}

		Animation animacion;
		for (int i = 0; i < GameState.animations.size(); i++) {
			animacion = GameState.animations.get(i);
			g2d.drawImage(animacion.getFotogramaActual(), (int) animacion.getPosicion().getX(),
					(int) animacion.getPosicion().getY(), null);

		}
		if (vidaBoss >= 1 && vidaBoss <= 20) {
			g.drawImage(Assets.finishHim, (Constantes.WIDTH - Assets.finishHim.getWidth()) / 2,
					(Constantes.HEIGHT - Assets.finishHim.getHeight()) / 2, null);

		}
		if (vidaBoss == 0) {

			g.drawImage(Assets.fatality, (Constantes.WIDTH - Assets.fatality.getWidth()) / 2,
					(Constantes.HEIGHT - Assets.fatality.getHeight()) / 2, null);
		}

		drawScore(g);
		drawLives(g);
		if (GameState.getVida() <= 0) {
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

		String puntuacionToString = Integer.toString(GameState.getPuntuacion());
		for (int i = 0; i < puntuacionToString.length(); i++) {
			g.drawImage(Assets.numeros.get(Integer.parseInt(puntuacionToString.substring(i, i + 1))), (int) pos.getX(),
					(int) pos.getY(), null);

			pos.setX(pos.getX() + 20);
		}
	}

	private static void drawLives(Graphics g) {
		Vector2D pos = new Vector2D(5, Constantes.HEIGHT - Assets.lives.get(0).getHeight() - 50);
		for (int i = 1; i <= GameState.getVida(); i++) {

			if (i % 2 == 0) {
				g.drawImage(Assets.lives.get(0), (int) pos.getX(), (int) pos.getY(), null);

			}

			pos.setX(pos.getX() + 20);

		}

		if (GameState.getVida() % 2 != 0.0) {
			g.drawImage(Assets.lives.get(1), (int) pos.getX(), (int) pos.getY(), null);

		}

	}

	public static void playExplosion(Vector2D posicion) {
		GameState.animations.add(new Animation(Assets.explosion,
				posicion.substract(
						new Vector2D(Assets.asteroid.get(0).getWidth() / 2, Assets.asteroid.get(0).getHeight() / 2)),
				Constantes.VEL_ANIMACION_EXPLOSION));
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
		GameState.setPuntuacion(GameState.getPuntuacion() + score);
	}

	public static List<GameObject> getObjetos() {
		return GameState.objetos;
	}

	public static void setObjetos(ArrayList<GameObject> objetos) {
		GameState.objetos = objetos;
	}

	public Sound getMusica() {
		return musica;
	}

	public void setMusica(Sound musica) {
		this.musica = musica;
	}

	public static int getVidaBoss() {
		return vidaBoss;
	}

	public static void setVidaBoss(int vidaBoss) {
		FinalState.vidaBoss = vidaBoss;
	}

}
