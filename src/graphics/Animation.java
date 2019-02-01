package graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import math.Vector2D;

public class Animation {

	private List<BufferedImage> animacion = new ArrayList<BufferedImage>();
	private Vector2D posicion;
	private int velocidad;
	private int indice;
	private boolean running;
	private long time, lastTime;

	public Animation(List<BufferedImage> animacion, Vector2D posicion, int velocidad) {
		super();
		this.animacion = animacion;
		this.velocidad = velocidad;
		this.running = true;
		this.posicion = posicion;
		this.time = 0;
		this.lastTime = System.currentTimeMillis();
	}

	public void update() {
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if (time > velocidad) {
			time = 0;
			indice++;
			if (indice >= animacion.size()) {
				running = false;

			}

		}

	}

	public boolean isRunning() {
		return running;

	}

	public Vector2D getPosicion() {
		return posicion;

	}

	public BufferedImage getFotogramaActual() {
		return animacion.get(indice);
	}

}
