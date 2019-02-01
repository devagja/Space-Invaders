package objectLaunchers;

import gameObject.Medevac;
import graphics.Assets;
import math.Vector2D;
import states.GameState;

public class PickupsLauncher implements Runnable {
	private Thread thread;
	private boolean running;
	private boolean pausa;
	private int tiempoEspera;

	@Override
	public void run() {
		while (running) {
			if (!pausa) {
				startWave();
			}
		}
	}

	private void startWave() {
		double x = 0, y = 0;
		GameState.getObjetos().add(new Medevac(Assets.medevac, new Vector2D(x, y)));
		esperar();
	}

	public void esperar() {
		tiempoEspera = (int) Math.floor((Math.random() * 15000) + 1000);
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
		thread = new Thread(this);
		running = true;
		thread.start();
	}

	public void stop() {

		running = false;

	}
}