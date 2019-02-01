package gameObject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;
import version_00.Constantes;

public class Asteroid extends GameObject {

	private double rotacion;
	private double gradoDeRotacion;

	/**
	 * Constructor del asteroide
	 * 
	 * @param textura
	 *            Imagen de asteroide
	 * @param posicion
	 *            Posicion de la generacion del asteroide
	 */
	public Asteroid(BufferedImage textura, Vector2D posicion) {
		super(textura, posicion);
		// Inicializa con un grado de rotacion y un sentido concreto
		// Divide entre 10 para asegurarnos que la rotacion no va a ser muy brusca
		gradoDeRotacion = (Math.random() / 10);
		sentidoRotacion();
	}

	@Override
	public void update() {

		// Actualiza la rotacion para que sea constante
		rotacion += gradoDeRotacion;

		// Desplazamiento hacia abajo del asteroide
		posicion.setY(posicion.getY() + Constantes.ASTEROID_VEL);

		// Elimina el asteroide(de la List) en el caso de salirse del marco del juego
		if (limite()) {
			GameState.getObjetos().remove(this);
		}
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// Objeto tipo AffineTransform que obtiene la posicion actual del asteroide
		AffineTransform at = AffineTransform.getTranslateInstance(posicion.getX(), posicion.getY());

		// Rota la imagen sobre un eje, el centro de la textura
		at.rotate(rotacion, textura.getWidth() / 2, textura.getHeight() / 2);

		// Dibuja la textura con la rotacion de at,
		g2d.drawImage(textura, at, null);
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

		// Destruye el asteroide
		if (a instanceof Player) {
			playExplosion(this.getCenter());
			this.destroy();
		}

		// Destruye el asteroide
		if (a instanceof Shoot) {
			playExplosion(this.getCenter());
			this.destroy();
		}
	}

	/**
	 * Metodo que determina el sentido de la rotacion
	 */
	private void sentidoRotacion() {
		// determina el sentido de la rotacion
		if (Math.random() >= 0.5) {
			gradoDeRotacion = -gradoDeRotacion;
		}
	}
}