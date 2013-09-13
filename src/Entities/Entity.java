package Entities;

import java.awt.Graphics;

import Core.Game;
import Level.Tile;

public abstract class Entity {
	// Class References
	Game game;

	// Unit Variables
	public int viewRange;
	public int damage;
	public int attackRange;
	public int imageID;

	// Location Variables
	int x, y;
	int tileX, tileY;

	// Required methods
	public abstract void update();

	public abstract void render(Graphics g);

	public void seeTiles(Tile tiles[][]) {
		for (int i = viewRange * -1; i <= viewRange; i++) {
			if (tiles[tileX + i][tileY + i] != null) {
				//tiles[tileX + i][tileY + i].setCanBeSeen(true); // First attempt, draws a diagonal line 5 tiles up-left and down-right of unit
				
				tiles[tileX + i][tileY].setCanBeSeen(true); // Creates a horizontal line, 5 tiles left/right of unit
				tiles[tileX][tileY + i].setCanBeSeen(true); // Creates a vertical line, 5 tiles above/below unit
			}
		}
	}
}