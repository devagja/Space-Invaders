package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Assets;
import math.Cronometro;
import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public class Medevac extends GameObject {
	private Cronometro pickupWait;

	public Medevac(BufferedImage textura, Vector2D posicion) {
		super(textura, posicion);
		pickupWait = new Cronometro();

	}

	private int tipoPickup;

	@Override
	public void update() {

		posicion.setX(posicion.getX() + Constantes.MEDEVAC_VEL);
		if (limite()) {
			GameState.getObjetos().remove(this);

		}
		if (Math.random() < Constantes.MEDEVAC_DROP_PROBABILITY && !pickupWait.isRunning()) {
			tipoPickup = (int) (Math.random() * Assets.pickups.size());

			GameState.getObjetos().add(new Pickup(Assets.pickups.get(tipoPickup),
					this.getCenter().sum(new Vector2D(0, textura.getHeight())), tipoPickup));
			pickupWait.run(200);

		}
		pickupWait.update();

	}

	@Override
	public void draw(Graphics g) {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		g.drawImage(textura, (int) posicion.getX(), (int) posicion.getY(), null);

	}

	/**
	 * Metodo que detecta la colision entre objetos
	 * 
	 * @param a
	 *            GameObject recibidor de la colision
	 * @param b
	 *            GameObject provocador de la colision
	 */
	public void objectCollision(GameObject a) {
		// Condicion que evita que al disparar no se destruya el jugador ni el disparo
		// Destruye ambos objetos y reproduce la animacion
		if (a instanceof Player) {

			playExplosion(this.getCenter());
			destroy();

		}
		if (a instanceof Shoot) {
			playExplosion(this.getCenter());

			destroy();

		}
	}

	/**
	 * Metodo que determina el sentido de la rotacion
	 */
	public double sentidoInicial() {
		// determina el sentido de la rotacion
		if (Math.random() >= 0.5) {
			return Constantes.MEDEVAC_VEL;
		}
		return -Constantes.MEDEVAC_VEL;

	}

}
