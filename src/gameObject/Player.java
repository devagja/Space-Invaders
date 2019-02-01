package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.AnimacionMotor;
import graphics.Assets;
import graphics.Sound;
import input.Keyboard;
import math.Cronometro;
import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public class Player extends GameObject {
	private Cronometro fireWait;

	private static AnimacionMotor motor;
	private static Sound catchSound = new Sound(Assets.audioCatch);

	public Player(BufferedImage textura, Vector2D posicion) {
		super(textura, posicion);
		fireWait = new Cronometro();
		motor = new AnimacionMotor(posicion, Constantes.VEL_ANIMACION_MOTOR);

	}

	@Override
	public void update() {

		// Disparo cada x tiempo
		disparo();

		movimiento();

		// Movimiento de la nave

		limite();
		// Comprueba si se ha colisionado con la nave
		collidesWith();

		// Actualiza la posicion del motor acorde al jugador
		motor.update(posicion);

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(textura, (int) posicion.getX(), (int) posicion.getY(), null);
		motor.draw(g);

	}

	public void objectCollision(GameObject a) {
		if (a instanceof Asteroid) {
			GameState.setVida(GameState.getVida() - 2);

		} else if (a instanceof Enemy || a instanceof EnemiesShoot || a instanceof Boss || a instanceof BossShoot) {
			GameState.setVida(GameState.getVida() - 1);
		} else if (a instanceof Pickup) {
			playCatchSound();

		}
		if (GameState.getVida() <= 0) {
			playExplosion(this.getCenter());
			destroy();
		}
	}

	private static void playCatchSound() {
		catchSound.play();
		if (catchSound.getFramePosition() > 10000) {
			catchSound.stop();
		}
	}

	private void movimiento() {
		if (Keyboard.UP) {
			posicion.setY(posicion.getY() - Constantes.PLAYER_VEL);

		}
		if (Keyboard.DOWN) {
			posicion.setY(posicion.getY() + Constantes.PLAYER_VEL);

		}
		if (Keyboard.RIGHT) {
			posicion.setX(posicion.getX() + Constantes.PLAYER_VEL);

		}
		if (Keyboard.LEFT) {
			posicion.setX(posicion.getX() - Constantes.PLAYER_VEL);

		}
	}

	private void disparo() {
		if (Keyboard.SHOOT && !fireWait.isRunning()) {
			GameState.getObjetos().add(new Shoot(Assets.shoot.get(Constantes.PLAYER_MISIL), getCenter()));
			fireWait.run(Constantes.FIRERATE);
		}
		fireWait.update();
	}

}