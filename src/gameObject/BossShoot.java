package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public class BossShoot extends GameObject {
	private Vector2D tipoBolaFuego;
	private static final Sound shoot = new Sound(Assets.audioBossAttack);

	/**
	 * Constructor del disparo enemigo
	 * 
	 * @param textura
	 *            Imagen del disparo enemigo
	 * @param posicion
	 *            Posicion de la generacion del disparo enemigo
	 */
	public BossShoot(BufferedImage textura, Vector2D posicion, int tipoBolaFuego) {
		super(textura, posicion);
		tipoDisparo(tipoBolaFuego);
		playShootSound();
	}

	private static void playShootSound() {
		shoot.play();
		if (shoot.getFramePosition() > 1000) {
			shoot.stop();
		}
	}

	private void tipoDisparo(int tipoDisparo) {
		double x = 0, y = 0;

		switch (tipoDisparo) {
		case 6:
			x -= Constantes.BOSS_SHOOT_SPEED;
			y -= Constantes.BOSS_SHOOT_SPEED;
			break;
		case 5:
			x += 0;
			y -= Constantes.BOSS_SHOOT_SPEED;
			break;
		case 7:
			x += Constantes.BOSS_SHOOT_SPEED;
			y -= Constantes.BOSS_SHOOT_SPEED;
			break;
		case 3:
			x -= Constantes.BOSS_SHOOT_SPEED;
			y += 0;
			break;
		case 4:
			x += Constantes.BOSS_SHOOT_SPEED;
			y += 0;
			break;
		case 1:
			x -= Constantes.BOSS_SHOOT_SPEED;
			y += Constantes.BOSS_SHOOT_SPEED;
			break;
		case 0:
			x += 0;
			y += Constantes.BOSS_SHOOT_SPEED;
			break;
		case 2:
			x += Constantes.BOSS_SHOOT_SPEED;
			y += Constantes.BOSS_SHOOT_SPEED;
			break;

		}
		tipoBolaFuego = new Vector2D(x, y);

	}

	@Override
	public void update() {
		posicion = posicion.sum(tipoBolaFuego);

		// Elimina el disparo(de la List) en el caso de salirse del marco del juego
		if (limite()) {
			GameState.getObjetos().remove(this);
		}
	}

	@Override
	public void draw(Graphics g) {

		g.drawImage(textura, (int) posicion.getX() - (textura.getWidth() / 2),
				(int) posicion.getY() - (textura.getHeight() / 2), null);
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

		} // Destruye el disparo
		if (a instanceof Shoot) {
			this.destroy();

			// Destruye el disparo
		}
	}

}
