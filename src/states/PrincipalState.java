
package states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;

import graphics.Assets;
import graphics.Sound;
import version_00.Constantes;

public class PrincipalState {

	public Sound musica;

	int i = 0;

	public PrincipalState() {
		musica = new Sound(Assets.audioIntro);

	}

	public void init() {
		Constantes.BACKGROUND = (int) (Math.random() * Assets.fondos.size());
		Constantes.PLAYER_MISIL = (int) (Math.random() * Assets.shoot.size());

		musica.loop();
	}

	public void update() {
		// no se puede usar for each debido para recorrer la coleccion se basa en ella
		// misma, por lo tanto no permite modificarla.
		for (int i = 0; i < GameState.objetos.size(); i++) {
			GameState.objetos.get(i).update();

		}
 
		for (int i = 0; i < GameState.animations.size(); i++) {
			GameState.animations.get(i).update();
			if (!GameState.animations.get(i).isRunning()) {
				GameState.animations.remove(i);

			}

		}

	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(new TexturePaint(Assets.fondos.get(Constantes.BACKGROUND),
				new Rectangle(0, i, Assets.fondos.get(Constantes.BACKGROUND).getWidth(),
						Assets.fondos.get(Constantes.BACKGROUND).getHeight())));
		g2d.fillRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT);
		for (int i = 0; i < GameState.objetos.size(); i++) {
			GameState.objetos.get(i).draw(g);
		}

		g.drawImage(Assets.tituloInicio, 0, 0, null);

	}

}
