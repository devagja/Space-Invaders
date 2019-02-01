package objectLaunchers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import gameObject.Enemy;
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

public class EnemiesLauncher implements EndGameListener, PauseGameListener {
	private static boolean pausa;
	private static int tiempoEspera;
	private static Timer timer = new Timer(tiempoEspera, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			launchEnemy();
		}

	});

	
	// no se sale de while running en ningun momento, pero deja de comprobar la
	// condicion, no entra
	// en ella despues de reanudar el juego, salvo que delante ponga un syso
	// sucede lo mismo si se sustituye por un while
	// si se comenta el metodo launchEnemy() entra en la condicion sin ningun
	// problema

	private static void launchEnemy() {
		double x = 0;
		double y = Assets.medevac.getHeight();
		int tipoEnemigo;

		x = (Math.random() * (Constantes.WIDTH - 50));

		tipoEnemigo = (int) (Math.random() * 5);
		if (tipoEnemigo == 0 || tipoEnemigo == 1) {
			GameState.getObjetos().add(new Enemy(Assets.enemy0, new Vector2D(x, y), tipoEnemigo));

		}
		if (tipoEnemigo == 2 || tipoEnemigo == 3) {
			GameState.getObjetos().add(new Enemy(Assets.enemy1, new Vector2D(x, y), tipoEnemigo));

		}
		if (tipoEnemigo == 4 || tipoEnemigo == 5) {
			GameState.getObjetos().add(new Enemy(Assets.enemy2, new Vector2D(x, y), tipoEnemigo));

		}

		esperar();

	}

	public static void esperar() {
		// el origen del fallo esta aqui
		tiempoEspera = (int) Math.floor(
				Math.random() * (Constantes.ENEMIES_GENERACION_TIME_MAX - Constantes.ENEMIES_GENERACION_TIME_MIN + 1)
						+ Constantes.ENEMIES_GENERACION_TIME_MIN);
		timer.setDelay(tiempoEspera);

		// try {
		// Thread.sleep(tiempoEspera);
		/*
		 * } catch (Exception e) { // Mensaje en caso de que falle }
		 */

	}

	/*
	 * public void continuar() { pausa = false; System.out.println("continuo" +
	 * pausa);
	 * 
	 * }
	 * 
	 * public void parar() { pausa = true; System.out.println("paro" + pausa);
	 * 
	 * }
	 */

	public void comenzar() {
		EndGameManager.addEndGameListener(this);
		PauseGameManager.addEndGameListener(this);
		timer.start();

	
	}


	@Override
	public void finJuego(EndGameEvent endGameEvent) {
	
	}

	@Override
	public void pause(PauseGameEvent pauseGameEvent) {
		pausa = pauseGameEvent.isGamePaused();
		System.out.println(pausa);
		if (timer.isRunning()) {
			timer.stop();

		} else {
			timer.restart();
		}

	}

}