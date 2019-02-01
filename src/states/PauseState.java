package states;

import java.awt.Graphics;

import graphics.Assets;
import graphics.Sound;
import version_00.Constantes;

public class PauseState {
	public Sound enterSound;
	public Sound exitSound;

	public PauseState() {

		enterSound = new Sound(Assets.audioMenuEnter);
		exitSound = new Sound(Assets.audioMenuExit);

	}

	public void init() {

	}

	public void update() {

	}

	public void draw(Graphics g) {
		g.drawImage(Assets.pause, (Constantes.WIDTH / 2 - Assets.pause.getWidth() / 2),
				(Constantes.HEIGHT / 2 - Assets.pause.getHeight() / 2), null);
	}

}
