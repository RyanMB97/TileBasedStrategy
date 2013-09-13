package Core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameResourceLoader {
	public static byte Fog = 0;
	public static byte Grass = 1;

	public BufferedImage tileMap; // All tiles in one image to be separated
	public BufferedImage tiles[] = new BufferedImage[2]; // Each separate tile

	public BufferedImage unitMap;
	public BufferedImage units[] = new BufferedImage[1];

	public GameResourceLoader() {
		loadImages();
	}

	private void loadImages() {
		try {
			// Load tiles
			tileMap = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Images/Tiles.png"));
			for (byte i = 0; i < tiles.length; i++) {
				tiles[i] = tileMap.getSubimage(i * 32, 0, 32, 32);
			}

			// Load units
			unitMap = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Images/Units.png"));
			for (byte i = 0; i < units.length; i++) {
				units[i] = unitMap.getSubimage(i * 32, 0, 32, 32);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}