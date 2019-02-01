package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public class Shoot extends GameObject {

	private static final Sound shoot = new Sound(Assets.audioMissile);;

	public Shoot(BufferedImage textura, Vector2D posicion) {
		super(textura, posicion);
		playShootSound();
	}

	private static void playShootSound() {
		shoot.play();
		if (shoot.getFramePosition() > 10000) {
			shoot.stop();
		}
	}

	@Override
	public void update() {
		posicion.setY(posicion.getY() - Constantes.SHOOT_VEL);
		if (limite()) {
			GameState.getObjetos().remove(this);

		}

		// Comprueba si se ha colisionado con la nave
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
		// Condicion que evita que al disparar no se destruya el jugador ni el disparo
		// Destruye ambos objetos y reproduce la animacion
		if (a instanceof Asteroid) {
			GameState.setPuntuacion(GameState.getPuntuacion() + Constantes.ASTEROID_SCORE);
			destroy();

		}
		if (a instanceof Enemy) {
			GameState.setPuntuacion(GameState.getPuntuacion() + Constantes.ENEMY_SCORE);
			destroy();

		}
		if (a instanceof Boss) {
			playExplosion(posicion);
			destroy();

		}
		if (a instanceof EnemiesShoot) {
			destroy();
		}
	}
}