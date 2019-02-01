package version_00;

import graphics.Assets;
import math.Vector2D;

public class Constantes {

	// ----------------------- VENTANA -----------------------
	public static final int WIDTH = 700, HEIGHT = 900; // DIMENSIONES VENTANA
	public static final int FPS = 90; // FPS OBJETIVOS

	// ----------------------- JUEGO -----------------------
	public static final int SCORE_FOR_FINAL = 35000; // FONDO DEL JUEGO(ELEGIDO ALEATORIAMENTE)
	public static int BACKGROUND; // FONDO DEL JUEGO(ELEGIDO ALEATORIAMENTE)
	public static int PLAYER_MISIL; // MISIL JUGADOR(ELEGIDO ALEATORIAMENTE)
	public static int ENEMIES_MISIL; // MISIL ENEMIGOS(ELEGIDO ALEATORIAMENTE)

	public static final int ASTEROID_SCORE = 50; // PUNTUACION POR ASTEROIDE
	public static final int ENEMY_SCORE = 150; // PUNTUACION POR ENEMIGO

	public static final int SCORE = 0; // PUNTUACION INICIAL JUGADOR
	public static final int LIVES = 6; // VIDA INICIAL JUGADOR
	public static int LIVES_BOSS = 100; // VIDA INICIAL BOSS

	// ----------------------- JUGADOR -----------------------
	public static final Vector2D PLAYER_INIT_POSICION = new Vector2D(WIDTH / 2 - 20, HEIGHT - 100); // POSICION INICIAL

	public static double PLAYER_VEL = 3; // DESPLAZAMIENTO(VELOCIDAD) JUGADOR
	public static int FIRERATE = 670; // TIEMPO(MS) ENTRE DISPARO JUGADOR

	// ----------------------- DISPARO JUGADOR -----------------------
	public static double SHOOT_VEL = 5; // DESPLAZAMIENTO(VELOCIDAD) DISPARO DE JUGADOR

	// ----------------------- ENEMIGO -----------------------
	public static final double ENEMIES_HORIZONTAL_DISPLACEMENT = Assets.enemy1.get(0).getWidth(); // DESPLAZAMIENTO(VELOCIDAD)
																									// HORIZONTAL
																									// ENEMIGOS
	public static final double ENEMIES_DOWN_DISPLACEMENT = Assets.enemy1.get(0).getHeight();// DESPLAZAMIENTO(VELOCIDAD)
																							// VERTICAL ENEMIGOS

	// ----------------------- DISPARO ENEMIGO -----------------------
	public static double ENEMIES_SHOOT_PROBABILITY = 0.001;// PROBABILIDAD DISPARO ENEMIGOS
	public static double ENEMIES_SHOOT_VEL = 2; // VELOCIDAD DISPARO ENEMIGOS
	public static long ENEMIES_PAUSE = 750; // VELOCIDAD ENEMIGOS

	// ----------------------- OLEADA ENEMIGOS -----------------------
	public static int ENEMIES_GENERACION_TIME_MAX = 1500; // TIEMPO GENERACION ENEMIGOS MAXIMA (MILISEGUNDOS)
	public static int ENEMIES_GENERACION_TIME_MIN = 1000; // TIEMPO GENERACION ENEMIGOS MINIMA (MILISEGUNDOS)

	// ----------------------- ASTEROIDE -----------------------
	public static double ASTEROID_VEL = 2; // DESPLAZAMIENTO(VELOCIDAD) ASTEROIDE

	// ----------------------- OLEADA ASTEROIDES -----------------------
	public static int VEL_GENERACION_ASTEROID_MAX = 2000; // VELOCIDAD GENERACION ASTEROIDE MAXIMA (MILISEGUNDOS)
	// DEBE SER >=1 , RESULTARIA ERRONEO GENERAR DOS OLEADAS EN EL MISMO
	// ESPACIO-TIEMPO
	public static int VEL_GENERACION_ASTEROID_MIN = 1000; // VELOCIDAD GENERACION ASTEROIDE MINIMA (MILISEGUNDOS)

	// ----------------------- ANIMACION -----------------------
	public static final int VEL_ANIMACION_EXPLOSION = 50; // TIEMPO ENTRE CASA FOTOGRAMA DE LA EXPLOSION
	public static final int VEL_ANIMACION_MOTOR = (int) (5 * PLAYER_VEL); // TIEMPO ENTRE CASA FOTOGRAMA DEL MOTOR

	// ----------------------- PICKUPS -----------------------
	public static double PICKUPS_VEL = 2; // DESPLAZAMIENTO(VELOCIDAD) PICKUPS

	// ----------------------- BOSS -----------------------
	public static double BOSS_VEL = 2; // DESPLAZAMIENTO(VELOCIDAD) BOSS
	public static double BOSS_SHOOT_PROBABILITY = 0.01; // PROBABILIDAD DISPARO BOSS
	public static double BOSS_SHOOT_SPEED = 2; // PROBABILIDAD DISPARO BOSS

	// ----------------------- MEDEVAC -----------------------
	public static double MEDEVAC_VEL = 2; // VELOCIDAD ASTEROIDE
	public static double MEDEVAC_DROP_PROBABILITY = 0.01; // VELOCIDAD ASTEROIDE
}
