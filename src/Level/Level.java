package Level;

import java.awt.Graphics;

import Core.Game;

public class Level {
	Game game;
	private Tile tiles[][];
	int worldWidth = 60, worldHeight = 60;

	public Level(Game game) {
		this.game = game;
		tiles = new Tile[worldWidth][worldHeight];
		long beginTime = System.currentTimeMillis();
		generateLevel();
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - beginTime;
		System.out.println("It took " + resultTime + "ms to generate the level!");
	} // End constructor

	public void generateLevel() {
		// Tile generator

		for (int x = 0; x < worldWidth; x++) {
			for (int y = 0; y < worldHeight; y++) {
				tiles[x][y] = new GrassTile(x * 32, y * 32, game);
			}
		}
	} // End generateLevel

	public void update() {
		for (int x = 0; x < worldWidth; x++) {
			for (int y = 0; y < worldHeight; y++) {
				tiles[x][y].update(game);
			}
		}
	} // End update

//	private void rightClick(Tile tile) { // if Right click
//
//	}
//
//	private void leftClick(Tile tile) { // If Left click
//
//	}// End left-click

	public void render(Graphics g) {
		// Tile loops
		for (int x = 0; x < worldWidth; x++) {
			for (int y = 0; y < worldHeight; y++) {
				if (tiles[x][y].isVisible)
					tiles[x][y].render(g);
			}
		}
	} // End render
	
	// Getters and Setters
	
	public Tile[][] getTiles(){
		return tiles;
	}
	
}