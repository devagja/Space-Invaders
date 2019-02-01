package version_00;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import graphics.Assets;
import input.Keyboard;
import listenerPauseGame.PauseGameManager;
import math.Cronometro;
import states.FinalState;
import states.GameState;
import states.PauseState;
import states.PrincipalState;

@SuppressWarnings("serial")
public class Ventana extends JFrame implements Runnable {

	private Canvas canvas;
	private Thread thread;

	private Dimension dimension = new Dimension(Constantes.WIDTH, Constantes.HEIGHT);
	private static boolean running = false;

	private BufferStrategy bs;
	private Graphics g;

	private final int FPS = Constantes.FPS;
	// tiempo que debe alcanzar cada refresco de pantalla(nanosegundos)
	private double tiempoObjetivo = 1000000000 / FPS;
	// cambio fps respecto al tiempo
	private double delta = 0;
	// media fps
	private int average = FPS;

	private PrincipalState principalState;
	private GameState gameState;
	private PauseState pauseState;
	private FinalState finalState;

	private Keyboard keyboard;

	private Cronometro menuWait;

	boolean pressStart = false;

	boolean isInitGame = false;
	private static boolean isInitFinal = false;
	private static boolean continueGame = false;

	private boolean r1 = true;

	public Ventana() {
		menuWait = new Cronometro();

		setName("Invaders");
		setSize(dimension);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// no permite cambiar tamano de la ventana
		setResizable(true);
		// se despliega en el centro de la pantalla
		setLocationRelativeTo(null);

		canvas = new Canvas();
		keyboard = new Keyboard();

		// tamano del lienzo
		canvas.setPreferredSize(dimension);
		canvas.setMaximumSize(dimension);
		canvas.setMinimumSize(dimension);
		canvas.setFocusable(true);

		add(canvas);
		canvas.addKeyListener(keyboard);
		setVisible(true);
		// Debe ser la ultima linea, dado que muestra la ventana

	}

	public void run() {
		// tiempo del frame actual
		long now = 0;
		// tiempo del frame anterior
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;

		init();

		while (running) {
			now = System.nanoTime();
			// tiempo transcurrido 0 el an0terior y el actual dividido por el objetivo
			delta += (now - lastTime) / tiempoObjetivo;
			// suma el tiempo transcurrido entre cada fotograma del segundo
			time += (now - lastTime);
			lastTime = now;
			// cuando delta llegue a ser 1 o mas(fotograma) se realizar update y draw
			if (delta >= 1) {
				// no se puede pausar update debido a que el teclado se actualiza en update

				update();

				draw();

				delta--;
				frames++;
			}

			// cuando el tiempo llega a un segundo
			if (time >= 1000000000) {
				// la media es la cantidad de frames en un segundo
				average = frames;
				// reestablece a 0 los contadores de frames(imagenes) y tiempo
				frames = 0;
				time = 0;
			}
			// ACABAR, PAUSAR JUEGO Y MOSTRAR MENU
			// PREGUNTAR POR QUE FINALIZA EL JUEGO AL COMPLETO AL EJECUTAR LA PAUSA
			// SUCESIVAMENTE
			if (pressStart) {

				if (Keyboard.PAUSE && r1 == true && !menuWait.isRunning()) {

					pauseState.exitSound.stop();
					pauseState.enterSound.play();
					menuWait.run(150);
					r1 = false;
					PauseGameManager.fireEndGameListeners(true);
					/*
					 * GameState.getLa().parar(); GameState.getLe().parar();
					 * GameState.getLp().parar();
					 */
				} else if (Keyboard.PAUSE && r1 == false && !menuWait.isRunning()) {
					pauseState.enterSound.stop();
					pauseState.exitSound.play();

					menuWait.run(150);
					r1 = true;
					PauseGameManager.fireEndGameListeners(false);
					/*
					 * GameState.getLa().continuar(); GameState.getLe().continuar();
					 * GameState.getLp().continuar();
					 */

				}
				menuWait.update();
			}
		}
	}

	// inicializa el Juego y los assets
	private void init() {
		Assets.init();
		principalState = new PrincipalState();
		principalState.init();
		gameState = new GameState();
		finalState = new FinalState();
		pauseState = new PauseState();
		pauseState.init();
	}

	public void update() {
		keyboard.update();

		if (!pressStart) {
			principalState.update();
			UserPressStart();
		} else {
			if (!isInitGame) {
				GameState.init();
				isInitGame = true;

			}

			if (!isInitFinal && GameState.getPuntuacion() >= Constantes.SCORE_FOR_FINAL) {
				finalState.init();
				finalState.getMusica().play();

				isInitFinal = true;
				gameState.getMusica().stop();
				GameState.getLa().stop();
				
				//GameState.getLe().stop();
				GameState.getLp().stop();

			}

			if (!r1) {
				gameState.getMusica().stop();
				finalState.getMusica().stop();
				pauseState.update();
			} else {
				if (isInitFinal) {
					if (FinalState.getVidaBoss() > 0) {
						finalState.update();
						finalState.getMusica().continuar();

					} else {

						if (!continueGame) {
							GameState.init();
							continueGame = true;

						}

						finalState.getMusica().stop();
						gameState.update();
						gameState.getMusica().continuar();
					}

				} else {
					finalState.getMusica().stop();
					gameState.update();
					gameState.getMusica().continuar();
				}

			}

		}
	}

	public void draw() {

		bs = canvas.getBufferStrategy();
		if (bs == null) {
			/*
			 * Se crean 3 buffers para asi tener 3 imagenes cargadas, evitando freeze fps en
			 * caso de sobrecarga de gpu(que en este caso no va a pasar) y una mayor fluidez
			 * dado que tenemos en memoria cargada 2 imagenes mientras se muestra la
			 * primera. NO TIENE REPERCUSION EN EL RENDIMIENTO DE GPU, simplemente se hace
			 * uso de la RAM.
			 */
			canvas.createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		// -------------------------

		// limpia la interfaz
		g.setColor(Color.BLACK);
		g.clearRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT);

		// TEMPORAL
		g.fillRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT);

		// Dibuja todo lo incluido en gameState

		// TEMPORAL
		if (!pressStart) {
			principalState.draw(g);
		} else {
			if (isInitFinal) {
				if (FinalState.getVidaBoss() > 0) {
					finalState.draw(g);
				} else {
					gameState.draw(g);

				}

			} else {
				gameState.draw(g);
			}
			if (!r1) {
				pauseState.draw(g);
			}
		}

		// muestra la media de fps
		g.setColor(Color.WHITE);

		g.drawString("Media FPS " + average, 10, 10);

		// -------------------------

		// Libera los recursos una vez usados debido a que se han inicializado
		// manualmente con getDrawGraphics()
		// g.dispose();

		// muestra el buffer
		/*
		 * Hace que el siguiente b�fer disponible sea visible copiando la memoria
		 * (blitting) o cambiando el puntero de la pantalla (volteando).
		 */
		bs.show();
	}

	public void UserPressStart() {
		if (Keyboard.ENTER) {
			pressStart = true;
			principalState.musica.stop();
		}

	}

	public void comenzar() {
		thread = new Thread(this);
		thread.start();
		running = true;

	}

	public void stop() {
		try {
			// pone el hilo en espera
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public boolean isInitFinal() {
		return isInitFinal;
	}

	public static void setInitFinal(boolean isInitFinal) {
		Ventana.isInitFinal = isInitFinal;
	}

}
