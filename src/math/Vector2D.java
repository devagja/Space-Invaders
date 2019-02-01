package math;

public class Vector2D {
	private double x, y;

	public Vector2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Vector2D() {
		super();
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Metodo que devuelve la raiz de x^2 + y^2
	 */
	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}

	public Vector2D sum(Vector2D v) {
		return new Vector2D(x + v.getX(), y + v.getY());
	}

	public Vector2D substract(Vector2D v) {
		return new Vector2D(x - v.getX(), y - v.getY());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}