package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import math.Vector2D;

public class AnimacionMotor {

	protected List<BufferedImage> textura = Assets.motorJugador;
	protected Vector2D posicion;
	private int velocidad;
	private long time, lastTime;
	private int i = 0;

	public AnimacionMotor(Vector2D posicion, int velocidad) {
		this.posicion = posicion;
		this.velocidad = velocidad;
		this.time = 0;
		this.lastTime = System.currentTimeMillis();

	}

	public void update(Vector2D posicion) {
		// Posiciona el motor detras de la nave
		this.posicion = posicion.sum(new Vector2D(textura.get(i).getWidth() / 4.2, textura.get(i).getHeight() - 4.5));

		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if (time > velocidad) {
			time = 0;
			cambiarImagen();

		}

	}

	public void draw(Graphics g) {

		g.drawImage(textura.get(i), (int) posicion.getX(), (int) posicion.getY(), null);
	}

	private void cambiarImagen() {
		i++;
		if (i >= textura.size()) {
			i = 0;
		}
	}
}