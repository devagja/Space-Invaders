package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public class EnemiesShoot extends GameObject {

	private static final Sound shoot = new Sound(Assets.audioMissileEnemies);
	private static final Sound sxs = new Sound(Assets.audioSxS);

	/**
	 * Constructor del disparo enemigo
	 * 
	 * @param textura
	 *            Imagen del disparo enemigo
	 * @param posicion
	 *            Posicion de la generacion del disparo enemigo
	 */
	public EnemiesShoot(BufferedImage textura, Vector2D posicion) {
		super(textura, posicion);
		playShootSound();
	}

	private static void playShootSound() {
		shoot.play();
		if (shoot.getFramePosition() > 1000) {
			shoot.stop();
		}
	}

	private static void playSxSSound() {
		sxs.play();
		if (sxs.getFramePosition() > 100) {
			sxs.stop();
		}
	}

	@Override
	public void update() {

		// Desplazamiento hacia abajo del disparo
		posicion.setY(posicion.getY() + Constantes.ENEMIES_SHOOT_VEL);

		// Elimina el disparo(de la List) en el caso de salirse del marco del juego
		if (limite()) {
			GameState.getObjetos().remove(this);
		}
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

		// Destruye el disparo
		if (a instanceof Player) {
			this.destroy();

			// Destruye el disparo
		}
		if (a instanceof Shoot) {
			playSxSSound();
			this.destroy();

		}
	}

}