package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import graphics.Assets;
import math.Cronometro;
import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public class Enemy extends GameObject {

	private double sentido;
	private int tipoEnemigo;
	private Cronometro paradaMovimiento;
	private List<BufferedImage> enemies;

	public Enemy(List<BufferedImage> enemies, Vector2D posicion, int tipoEnemigo) {
		super(enemies.get(0), posicion);
		this.enemies = enemies;
		sentido = sentidoInicial();
		determinarFotogramaAnimacionInicio(tipoEnemigo);
		paradaMovimiento = new Cronometro();

	}

	private void movimientoHorizontal() {

		if (!paradaMovimiento.isRunning()) {

			// Cambia la animacion
			cambiarFotogramaAnimacion();

			// Desplazamiento en horizontal del enemigo
			posicion.setX(posicion.getX() + sentido);

			paradaMovimiento.run(Constantes.ENEMIES_PAUSE);
		}
		paradaMovimiento.update();

	}

	@Override
	public void update() {

		// Mueve en horizontal el enemigo cada determinado tiempo
		movimientoHorizontal();

		// Elimina el asteroide(de la List) en el caso de salirse del marco del juego
		if (limite()) {
			// Cambio de sentido(en horizontal)
			sentido = -sentido;
			// Desplazamiento hacia abajo del enemigo
			posicion.setY(posicion.getY() + Constantes.ENEMIES_DOWN_DISPLACEMENT);
		}
		disparo();

	}

	private void disparo() {
		if (Math.random() < Constantes.ENEMIES_SHOOT_PROBABILITY) {

			Constantes.ENEMIES_MISIL = (int) (Math.random() * Assets.shootEnemigos.size());
			GameState.getObjetos().add(new EnemiesShoot(Assets.shootEnemigos.get(Constantes.ENEMIES_MISIL),
					this.getCenter().sum(new Vector2D(0, enemies.get(tipoEnemigo).getHeight()))));
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(enemies.get(tipoEnemigo), (int) posicion.getX(), (int) posicion.getY(), null);

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
	 * Metodo que determina el sentido del movimiento inicial
	 */
	private double sentidoInicial() {
		// determina el sentido de la rotacion
		if (Math.random() >= 0.5) {
			return Constantes.ENEMIES_HORIZONTAL_DISPLACEMENT;
		}
		return -Constantes.ENEMIES_HORIZONTAL_DISPLACEMENT;

	}

	/**
	 * 
	 * @param tipoEnemigo
	 *            tipo de enemigo(de entre los 3 diferentes tipos de enemigos que
	 *            existen)
	 */
	private void determinarFotogramaAnimacionInicio(int tipoEnemigo) {

		if (tipoEnemigo % 2 != 0) {
			this.tipoEnemigo = 1;

		} else {
			this.tipoEnemigo = 0;

		}
	}

	private void cambiarFotogramaAnimacion() {
		if (tipoEnemigo == 1) {
			tipoEnemigo = 0;
		} else {
			tipoEnemigo = 1;
		}
	}
}