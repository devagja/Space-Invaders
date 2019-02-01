package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.FinalState;
import states.GameState;
import version_00.Constantes;

public class Boss extends GameObject {
	private double sentidoX, sentidoY;
	private static Sound injured1 = new Sound(Assets.audioInjured1);

	private static Sound injured2 = new Sound(Assets.audioInjured2);

	private static Sound death = new Sound(Assets.audioDeath);
	private static List<Vector2D> pos = new ArrayList<>();

	public Boss(BufferedImage boss, Vector2D posicion) {
		super(boss, posicion);
		sentidoX = sentidoInicial();
		sentidoY = sentidoInicial();

	}

	@Override
	public void update() {

		posicion.setX(posicion.getX() + sentidoX);
		posicion.setY(posicion.getY() + sentidoY);
		limite();

		// mutacion de movimiento
		mutacionMovimiento();

		disparo();

		if (FinalState.getVidaBoss() <= 0) {
			death.play();
			GameState.getObjetos().remove(this);

		}

	}

	@Override
	public void draw(Graphics g) {
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
		if (a instanceof Shoot) {
			if (FinalState.getVidaBoss() < (Constantes.LIVES_BOSS / 3)) {
				injured2.play();

			} else {
				injured1.play();
			}
			FinalState.setVidaBoss(FinalState.getVidaBoss() - 1);
		}

	}

	@Override
	protected boolean limite() {
		// De esta forma si toca un limite pasa a negativo el desplazamiento actual

		if (posicion.getX() < 0) {
			posicion.setX(0);
			sentidoX = -sentidoX;

			return true;

		}
		if (posicion.getX() > Constantes.WIDTH - textura.getWidth()) {

			posicion.setX(Constantes.WIDTH - textura.getWidth());
			sentidoX = -sentidoX;

			return true;
		}

		if (posicion.getY() < 0) {
			posicion.setY(0);
			sentidoY = -sentidoY;

			return true;

		}
		if (posicion.getY() > (Constantes.HEIGHT - textura.getHeight())) {

			posicion.setY(Constantes.HEIGHT - textura.getHeight());
			sentidoY = -sentidoY;

			return true;
		}

		return false;
	}

	/**
	 * Metodo que determina el sentido de la rotacion
	 */
	public double sentidoInicial() {
		// determina el sentido de la rotacion
		if (Math.random() >= 0.5) {
			return Constantes.BOSS_VEL;
		}
		return -Constantes.BOSS_VEL;

	}

	private void mutacionMovimiento() {
		if (Math.random() < 0.05) {
			switch ((int) (Math.random() * 3)) {
			case 0:
				sentidoX += (Math.random() * 0.75);
				break;

			case 1:
				sentidoX -= (Math.random() * 0.75);
				break;

			case 2:
				sentidoY -= (Math.random() * 0.75);
				break;

			case 3:
				sentidoY += (Math.random() * 0.75);
				break;
			}
		}
	}

	private void posicionDisparos() {
		pos.clear();

		double x = 0, y = 0;
		for (int i = 0; i < Assets.bossShoot.size(); i++) {
			switch (i) {
			case 6:
				x = posicion.getX();
				y = posicion.getY();
				break;
			case 5:
				x = posicion.getX() + (textura.getWidth() / 2);
				y = posicion.getY();
				break;
			case 7:
				x = posicion.getX() + textura.getWidth();
				y = posicion.getY();
				break;
			case 3:
				x = posicion.getX();
				y = posicion.getY() + (textura.getHeight() / 2);
				break;
			case 4:
				x = posicion.getX() + textura.getWidth();
				y = posicion.getY() + (textura.getHeight() / 2);
				break;
			case 1:
				x = posicion.getX();
				y = posicion.getY() + textura.getHeight();
				break;
			case 0:
				x = posicion.getX() + (textura.getWidth() / 2);
				y = posicion.getY() + textura.getHeight();
				break;
			case 2:
				x = posicion.getX() + textura.getWidth();
				y = posicion.getY() + textura.getHeight();
				break;

			}
			pos.add(new Vector2D(x, y));
		}

	}

	private void disparo() {
		if (Math.random() < Constantes.BOSS_SHOOT_PROBABILITY) {
			posicionDisparos();
			for (int i = 0; i < Assets.bossShoot.size(); i++) {

				GameState.getObjetos().add(new BossShoot(Assets.bossShoot.get(i), pos.get(i), i));
			}
		}

	}
}
