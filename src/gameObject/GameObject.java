package gameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Animation;
import graphics.Assets;
import graphics.Sound;
import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public abstract class GameObject {

	// Describe la imagen con un bufer accesible de datos de imagen.
	// Se compone de un ColorModel y un Raster de datos de imagen.
	protected BufferedImage textura;
	protected Vector2D posicion;

	protected static Sound explosion;
	// TODOS LOS GAMEOBJECT VA A IR A 1

	public GameObject(BufferedImage textura, Vector2D posicion) {
		super();
		this.textura = textura;
		this.posicion = posicion;
		explosion = new Sound(Assets.audioExplosion);
	}

	/**
	 * Logica de cada Gameobject
	 */
	public abstract void update();

	/**
	 * Muestra la imagen de cada GameObject en su posicion relativa
	 * 
	 * @param g
	 */
	public abstract void draw(Graphics g);

	abstract void objectCollision(GameObject objetoColisionado);

	protected boolean limite() {

		if (posicion.getX() < 0) {
			posicion.setX(0);
			return true;

		}
		if (posicion.getX() > Constantes.WIDTH - textura.getWidth()) {

			posicion.setX(Constantes.WIDTH - textura.getWidth());
			return true;
		}

		if (posicion.getY() < 0) {
			posicion.setY(0);
			return true;

		}
		if (posicion.getY() > (Constantes.HEIGHT - textura.getHeight())) {

			posicion.setY(Constantes.HEIGHT - textura.getHeight());
			return true;
		}

		return false;
	}

	private static void playSoundExplosion() {
		explosion.play();
		if (explosion.getFramePosition() > 10000) {
			explosion.stop();
		}
	}

	protected static void playExplosion(Vector2D posicion) {
		GameState.getAnimations()
				.add(new Animation(Assets.explosion, posicion.substract(
						new Vector2D(Assets.asteroid.get(0).getWidth() / 2, Assets.asteroid.get(0).getHeight() / 2)),
						Constantes.VEL_ANIMACION_EXPLOSION));
		playSoundExplosion();
	}

	protected void collidesWith() {
		GameObject ObjetoColisionado;
		// distancia entre objetos
		double distancia;

		for (int i = 0; i < GameState.getObjetos().size(); i++) {

			ObjetoColisionado = GameState.getObjetos().get(i);
			// Condicion que evita la comprobacion de la distancia sobre si mismo
			if (ObjetoColisionado.equals(this)) {
				continue;
			}

			// diferencia entre objeto colisionado y colisionador
			distancia = ObjetoColisionado.getCenter().substract(getCenter()).getMagnitude();

			if (distancia < (ObjetoColisionado.textura.getWidth() / 2) + (this.textura.getWidth() / 2)
					|| distancia < (ObjetoColisionado.textura.getHeight() / 2) + (this.textura.getHeight() / 2)) {
				this.objectCollision(ObjetoColisionado);
				ObjetoColisionado.objectCollision(this);
			}
		}
	}

	/**
	 * Metodo que devuelve el centro de la imagen
	 * 
	 * @return Vector2D con el centro de la imagen
	 */
	public Vector2D getCenter() {
		return new Vector2D(posicion.getX() + textura.getWidth() / 2, posicion.getY() + textura.getHeight() / 2);
	}

	/**
	 * Metodo que elimina de la lista el objeto destruido
	 */
	protected void destroy() {
		GameState.getObjetos().remove(this);
	}

	public Vector2D getPosicion() {
		return posicion;
	}

	public void setPosicion(Vector2D posicion) {
		this.posicion = posicion;
	}

	public BufferedImage getTextura() {
		return textura;
	}

	public void setTextura(BufferedImage textura) {
		this.textura = textura;
	}
}