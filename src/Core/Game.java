package Core;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Entities.ScanProbe;
import Input.KeyBoardInput;
import Input.MouseInput;
import Level.Level;

public class Game extends Canvas implements Runnable {
	/**
	 * Ryan Ballou 2013
	 */
	private static final long serialVersionUID = 1L;

	// Class References
	private GameResourceLoader gameResources;
	private Level level;
	private KeyBoardInput kbInput;
	private MouseInput mInput;

	// Test Units
	ScanProbe testProbe;

	Thread gameThread = new Thread(this);

	public static boolean running = false;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final Dimension gameDim = new Dimension(WIDTH, HEIGHT);
	JFrame frame;

	public Point mouseP = new Point(-1, -1);

	public int xOffset = 0;
	public int yOffset = 0;

	public boolean showGrid = true;

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	double delta;
	boolean shouldRender, limitFrameRate;
	int FPS, UPS, ticks, frames;

	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		long lastTimer = System.currentTimeMillis();
		delta = 0D;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			// If you want to limit frame rate, shouldRender = false
			shouldRender = false;

			// If the time between ticks = 1, then various things (shouldRender = true, keeps FPS locked at UPS)
			while (delta >= 1) {
				ticks++;
				update();
				delta -= 1;
				shouldRender = true;
			}
			if (!limitFrameRate && ticks > 0)
				shouldRender = true;

			// If you should render, render!
			if (shouldRender) {
				frames++;
				render();
			}

			// Reset stuff every second for the new "FPS" and "UPS"
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				FPS = frames;
				UPS = ticks;
				frames = 0;
				ticks = 0;
			}
		}
	}

	public synchronized void start() {
		running = true;
		gameThread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public Game() {
		init();
		initClasses();

		requestFocus();
	}

	private void init() {
		setMinimumSize(gameDim);
		setMaximumSize(gameDim);
		setPreferredSize(gameDim);
		frame = new JFrame(Launcher.gameTitle);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void initClasses() {
		setGameResources(new GameResourceLoader());
		level = new Level(this);
		kbInput = new KeyBoardInput(this);
		mInput = new MouseInput(this);

		testProbe = new ScanProbe(this, 320, 320);
	}

	public void update() {
		frame.setTitle(Launcher.gameTitle + " FPS: " + FPS + " UPS: " + UPS);

		testProbe.update();
		level.update();
		kbInput.update();
	}// End tick method

	public void render() {
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) { // If the bufferstrategy doesn't exist, create one
			createBufferStrategy(4);
			return;
		}

		Graphics g = bs.getDrawGraphics(); // Grab graphics from buffer strategy for drawing

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		level.render(g);

		testProbe.render(g);

		// Clear and show
		g.dispose();
		bs.show();
	} // End render method

	// Getters and Setters

	public GameResourceLoader getGameResources() {
		return gameResources;
	}

	public void setGameResources(GameResourceLoader gameResources) {
		this.gameResources = gameResources;
	}

	public KeyBoardInput getKbInput() {
		return kbInput;
	}

	public void setKbInput(KeyBoardInput kbInput) {
		this.kbInput = kbInput;
	}

	public MouseInput getmInput() {
		return mInput;
	}

	public void setmInput(MouseInput mInput) {
		this.mInput = mInput;
	}

	public Level getLevel() {
		return level;
	}

}