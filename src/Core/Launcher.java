package Core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Launcher extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Window items
	public static final String gameTitle = "Turn Based Tile Strategy - V0.1";
	private final int WIDTH = 320;
	private final int HEIGHT = 180;

	// Buttons
	JButton play, quit;
	int buttonWidth = 80, buttonHeight = 20;

	public Launcher() {
		init();
	}

	private void init() {
		// Creating Window
		setTitle(gameTitle);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		createButtons();
	}

	private void createButtons() {
		play = new JButton("Play");
		quit = new JButton("Quit");

		play.setBounds(WIDTH / 2 - buttonWidth / 2, HEIGHT / 4 - buttonHeight / 2, buttonWidth, buttonHeight);
		quit.setBounds(WIDTH / 2 - buttonWidth / 2, HEIGHT / 2 - buttonHeight / 2, buttonWidth, buttonHeight);

		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Game().start();
				dispose();
			}
		});

		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		add(play);
		add(quit);
	}

	public static void main(String[] args) {
		new Launcher();
	}
}