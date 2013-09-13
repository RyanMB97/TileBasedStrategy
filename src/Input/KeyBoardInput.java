package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import Core.Game;

public class KeyBoardInput implements KeyListener {

	public class Key {

		public boolean down, clicked;
		private short absorbs, presses;

		public void update() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}

		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}

			if (pressed) {
				presses++;
			}
		}

		public Key() {
			keys.add(this);
		}
	}

	Game game;

	public List<Key> keys = new ArrayList<Key>();

	public Key left = new Key();
	public Key right = new Key();
	public Key up = new Key();
	public Key down = new Key();

	public KeyBoardInput(Game game) {
		game.addKeyListener(this);
		this.game = game;
	}

	public void update() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).update();
		}
	}

	public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}

	public void toggle(KeyEvent e, boolean pressed) {
		// Movement
		if (e.getKeyCode() == KeyEvent.VK_A)
			left.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_D)
			right.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_W)
			up.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_S)
			down.toggle(pressed);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e, true);

		if (e.getKeyCode() == KeyEvent.VK_G)
			game.showGrid = !game.showGrid;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}