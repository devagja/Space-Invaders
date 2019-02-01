package objectLaunchers;

import gameObject.Asteroid;
import graphics.Assets;
import listenerEndGame.EndGameEvent;
import listenerEndGame.EndGameListener;
import listenerEndGame.EndGameManager;
import listenerPauseGame.PauseGameEvent;
import listenerPauseGame.PauseGameListener;
import listenerPauseGame.PauseGameManager;
import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public class AsteroidLauncher implements Runnable, EndGameListener, PauseGameListener {
	private Thread thread;
	private boolean running;
	private boolean pausa;
	private int tiempoEspera;

	@Override
	public void run() {
		while (running) {

			if (!pausa) {
				launchAsteroid();
			}
		}
	}

	private void launchAsteroid() {
		double x, y = 0;
		int tipometeorito;

		x = (Math.random() * (Constantes.WIDTH - 50));

		tipometeorito = (int) (Math.random() * Assets.asteroid.size());

		GameState.getObjetos().add(new Asteroid(Assets.asteroid.get(tipometeorito), new Vector2D(x, y)));
		esperar();

	}

	public void esperar() {
		tiempoEspera = (int) Math.floor(
				Math.random() * (Constantes.VEL_GENERACION_ASTEROID_MAX - Constantes.VEL_GENERACION_ASTEROID_MIN + 1)
						+ Constantes.VEL_GENERACION_ASTEROID_MIN);
		try {
			Thread.sleep(tiempoEspera);
		} catch (Exception e) {
			// Mensaje en caso de que falle
		}
	}

	public void continuar() {
		pausa = false;

	}

	public void parar() {
		pausa = true;

	}

	public void comenzar() {
		EndGameManager.addEndGameListener(this);
		PauseGameManager.addEndGameListener(this);

		thread = new Thread(this);
		running = true;
		thread.start();
	}

	public void stop() {

		running = false;

	}

	@Override
	public void finJuego(EndGameEvent endGameEvent) {
		stop();
	}

	@Override
	public void pause(PauseGameEvent pauseGameEvent) {
		pausa = pauseGameEvent.isGamePaused();
	}

}
