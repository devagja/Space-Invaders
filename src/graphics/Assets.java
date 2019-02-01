package graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;

public class Assets {
	public static BufferedImage player;
	public static BufferedImage boss;
	public static BufferedImage medevac;
	public static BufferedImage tituloInicio;
	public static BufferedImage gameover;
	public static BufferedImage pause;
	public static BufferedImage finishHim;
	public static BufferedImage fatality;

	public static List<BufferedImage> bossShoot = new ArrayList<BufferedImage>();
	public static List<BufferedImage> bomba = new ArrayList<BufferedImage>();
	public static List<BufferedImage> pickups = new ArrayList<BufferedImage>();
	public static List<BufferedImage> lives = new ArrayList<BufferedImage>();
	public static List<BufferedImage> fondos = new ArrayList<BufferedImage>();
	public static List<BufferedImage> shoot = new ArrayList<BufferedImage>();
	public static List<BufferedImage> shootEnemigos = new ArrayList<BufferedImage>();
	public static List<BufferedImage> numeros = new ArrayList<BufferedImage>();
	public static List<BufferedImage> asteroid = new ArrayList<BufferedImage>();
	public static List<BufferedImage> explosion = new ArrayList<BufferedImage>();
	public static List<BufferedImage> motorJugador = new ArrayList<BufferedImage>();
	public static List<BufferedImage> enemy0 = new ArrayList<BufferedImage>();
	public static List<BufferedImage> enemy1 = new ArrayList<BufferedImage>();
	public static List<BufferedImage> enemy2 = new ArrayList<BufferedImage>();

	public static Clip audioMenuEnter;
	public static Clip audioMenuExit;
	public static Clip audioExplosion;
	public static Clip audioMissile;
	public static Clip audioMissileEnemies;
	public static Clip audioMusica;
	public static Clip audioIntro;
	public static Clip audioPickup;
	public static Clip audioGameOver;
	public static Clip audioCatch;
	public static Clip audioSxS;
	public static Clip audioFinal;
	public static Clip audioInjured1;
	public static Clip audioInjured2;
	public static Clip audioDeath;
	public static Clip audioBossAttack;

	/**
	 * Inicializa todos los sprites almacenandolos en Buffered
	 */
	public static void init() {
		player = Loader.cargarImagen("res/imagenes/jugador/nave.gif");
		boss = Loader.cargarImagen("res/imagenes/enemigos/boss.gif");
		medevac = Loader.cargarImagen("res/imagenes/medevac.png");
		tituloInicio = Loader.cargarImagen("res/imagenes/menus/tituloInicio.png");
		pause = Loader.cargarImagen("res/imagenes/menus/pause.png");
		finishHim = Loader.cargarImagen("res/imagenes/menus/finishhim.png");
		fatality = Loader.cargarImagen("res/imagenes/menus/fatality.png");
		gameover = Loader.cargarImagen("res/imagenes/menus/gameover.png");

		for (int i = 0; i < 8; i++) {
			bossShoot.add(Loader.cargarImagen("res/imagenes/disparos/boss/bomb" + i + ".gif"));

		}
		for (int i = 0; i < 1; i++) {
			bomba.add(Loader.cargarImagen("res/imagenes/bomba" + i + ".png"));

		}
		for (int i = 0; i < 17; i++) {
			pickups.add(Loader.cargarImagen("res/imagenes/pickups/fruit" + i + ".gif"));

		}
		for (int i = 0; i < 2; i++) {
			lives.add(Loader.cargarImagen("res/imagenes/menus/vida" + i + ".gif"));

		}

		for (int i = 0; i < 5; i++) {
			fondos.add(Loader.cargarImagen("res/imagenes/fondos/fondo" + i + ".gif"));

		}
		for (int i = 0; i < 3; i++) {
			shoot.add(Loader.cargarImagen("res/imagenes/disparos/misil" + i + ".gif"));

		}
		for (int i = 0; i < 4; i++) {
			shootEnemigos.add(Loader.cargarImagen("res/imagenes/disparos/disparo" + i + ".gif"));

		}
		for (int i = 0; i < 11; i++) {
			numeros.add(Loader.cargarImagen("res/imagenes/numeros/" + i + ".png"));

		}
		for (int i = 0; i < 4; i++) {
			asteroid.add(Loader.cargarImagen("res/imagenes/asteroides/asteroid" + i + ".gif"));

		}
		for (int i = 0; i < 6; i++) {
			explosion.add(Loader.cargarImagen("res/imagenes/explosiones/explosion" + i + ".gif"));

		}
		for (int i = 0; i < 6; i++) {
			motorJugador.add(Loader.cargarImagen("res/imagenes/motor/motor" + i + ".gif"));

		}
		for (int i = 0; i < 2; i++) {
			enemy0.add(Loader.cargarImagen("res/imagenes/enemigos/bicho" + i + ".gif"));

		}
		for (int i = 2; i < 4; i++) {
			enemy1.add(Loader.cargarImagen("res/imagenes/enemigos/bicho" + i + ".gif"));

		}
		for (int i = 4; i < 6; i++) {
			enemy2.add(Loader.cargarImagen("res/imagenes/enemigos/bicho" + i + ".gif"));

		}
		audioMenuEnter = Loader.cargarSonido("res/audio/menuenter.wav");
		audioMenuExit = Loader.cargarSonido("res/audio/menuexit.wav");
		audioExplosion = Loader.cargarSonido("res/audio/explosion.wav");
		audioMissile = Loader.cargarSonido("res/audio/missile.wav");
		audioMissileEnemies = Loader.cargarSonido("res/audio/missilenemies.wav");
		audioMusica = Loader.cargarSonido("res/audio/musica.wav");
		audioIntro = Loader.cargarSonido("res/audio/musicaIntro.wav");
		audioPickup = Loader.cargarSonido("res/audio/pickupdrop.wav");
		audioGameOver = Loader.cargarSonido("res/audio/gameover.wav");
		audioCatch = Loader.cargarSonido("res/audio/pickupcatch.wav");
		audioSxS = Loader.cargarSonido("res/audio/shootxshoot.wav");
		audioFinal = Loader.cargarSonido("res/audio/finalfight.wav");
		audioInjured1 = Loader.cargarSonido("res/audio/injured1.wav");
		audioInjured2 = Loader.cargarSonido("res/audio/injured2.wav");
		audioDeath = Loader.cargarSonido("res/audio/death.wav");
		audioBossAttack = Loader.cargarSonido("res/audio/bossattack.wav");

	}

}
