package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public class Bomb extends GameObject {

	public Bomb(BufferedImage textura, Vector2D posicion) {
		super(textura, posicion);
	}

	@Override
	public void update() {
		posicion.setY(posicion.getY() - Constantes.SHOOT_VEL);
		if (limite()) {
			GameState.getObjetos().remove(this);

		}
		collidesWith();

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(textura, (int) posicion.getX() - (textura.getWidth() / 2),
				(int) posicion.getY() - textura.getHeight(), null);

	}

	/**
	 * Metodo que detecta la colision entre objetos
	 * 
	 * @param a
	 *            GameObject recibidor de la colision
	 */
	public void objectCollision(GameObject a) {
	}
}
