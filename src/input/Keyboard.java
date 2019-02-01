package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	// teclado con todas las teclas
	private boolean[] keys = new boolean[256];
	public static boolean UP, DOWN, LEFT, RIGHT, SHOOT, BOMB, RESTART, PAUSE, ENTER;

	public Keyboard() {
		UP = false;
		DOWN = false;
		LEFT = false;
		RIGHT = false;
		SHOOT = false;
		BOMB = false;
		RESTART = false;
		PAUSE = false;
		ENTER = false;

	}

	public void update() {
		UP = keys[KeyEvent.VK_UP];
		DOWN = keys[KeyEvent.VK_DOWN];
		LEFT = keys[KeyEvent.VK_LEFT];
		RIGHT = keys[KeyEvent.VK_RIGHT];
		SHOOT = keys[KeyEvent.VK_SPACE];
		BOMB = keys[KeyEvent.VK_B];
		RESTART = keys[KeyEvent.VK_R];
		PAUSE = keys[KeyEvent.VK_ESCAPE];
		ENTER = keys[KeyEvent.VK_ENTER];

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}