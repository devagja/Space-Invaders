
package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public class Pickup extends GameObject {
	private int tipoPickup;
	private static Sound pickupSound = new Sound(Assets.audioPickup);

	public Pickup(BufferedImage textura, Vector2D posicion, int tipoPickup) {
		super(textura, posicion);
		this.tipoPickup = tipoPickup;
		pickupSound.play();
	}

	@Override
	public void update() {

		// Se incrementa la rotacion por el grado, dando la sensacion de que gira
		// constantemente
		// Determina la velocidad de la caida del asteroide
		posicion.setY(posicion.getY() + Constantes.PICKUPS_VEL);

		// Condicion que elimina un asteroide en el caso de salirse del marco del juego
		if (limite()) {

			GameState.getObjetos().remove(this);
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(textura, (int) posicion.getX(), (int) posicion.getY(), null);
	}

	public void objectCollision(GameObject a) {
		// Condicion que evita que al disparar no se destruya el jugador ni el disparo
		// Destruye ambos objetos y reproduce la animacion
		if (a instanceof Player) {
			pickupBonus();
			destroy();

		}
	}

	private void pickupBonus() {

		switch (tipoPickup) {

		case 0:// cereza
		case 1:// manzana
		case 4:// melon
		case 9:// mazorca
		case 10:// brocoli
		case 12:// lechuga
			GameState.setVida(GameState.getVida() + 2);
			break;

		case 2:// zanahoria
			Constantes.FIRERATE -= 10;
			break;

		case 3:// pina
		case 7:// seta
		case 8:// champinones
			Constantes.PLAYER_VEL += 0.8;
			break;

		case 5:// platano
			Constantes.SHOOT_VEL += 0.5;
			break;

		case 6:// onigiri
			GameState.addPuntuacion(100);
			break;

		case 11:// pomelo
			Constantes.ENEMIES_PAUSE += 100;
			break;

		case 13:// jalapeno
			Constantes.ENEMIES_GENERACION_TIME_MAX -= 100;
			Constantes.ENEMIES_GENERACION_TIME_MIN -= 100;
			Constantes.FIRERATE -= 45;
			break;

		case 14:// fruta del dragon
			Constantes.FIRERATE -= 15;
			GameState.setVida(GameState.getVida() + 2);
			break;

		case 15:// uvas
		case 16:// berenjena
			Constantes.MEDEVAC_DROP_PROBABILITY += 0.002;
			break;

		}
	}
}