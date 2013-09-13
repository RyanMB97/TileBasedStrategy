package Level;

import java.awt.Graphics;
import java.awt.Rectangle;

import Core.Game;

public abstract class Tile {
	private Rectangle tileBoundaries;

	// Class References
	Game game;

	// Fog of War
	private boolean canBeSeen = false; // True if the tile is within viewRange of a unit/building/entity

	// Coordinates and Size
	private int x, y, tileX, tileY;
	private int tileID;
	private int oX, oY; // Original x,y coordinates when created
	public static final int tileSize = 32; // Size of tiles

	// Misc
	public boolean isVisible; // If the tile is within the JFrame area
	private boolean containsMouse; // If the tile has the mouse in it

	// Methods
	public abstract void update(Game game);

	public abstract void render(Graphics g);

	protected void Visibility() { // Check if the tile is within the JFrame area
		if (getX() >= 0 - 32 && getX() <= game.getWidth() + 32 && getY() >= 0 - 32 && getY() <= game.getHeight() + 32) {
			isVisible = true;
		} else {
			isVisible = false;
		}
	}

	// World tile positions
	protected void setTilePos() {
		setTileX(x / 32);
		setTileY(y / 32);
	}

	// Getters and Setters

	public int getTileID() {
		return tileID;
	}

	public void setTileID(int tileID) {
		this.tileID = tileID;
	}

	public int getoX() {
		return oX;
	}

	public void setoX(int oX) {
		this.oX = oX;
	}

	public int getoY() {
		return oY;
	}

	public void setoY(int oY) {
		this.oY = oY;
	}

	public boolean isContainsMouse() {
		return containsMouse;
	}

	public void setContainsMouse(boolean containsMouse) {
		this.containsMouse = containsMouse;
	}

	public int getTileSize() {
		return tileSize;
	}

	public Rectangle getTileBoundaries() {
		return tileBoundaries;
	}

	public void setTileBoundaries(int x, int y, int width, int height) {
		this.tileBoundaries = new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public boolean isCanBeSeen() {
		return canBeSeen;
	}

	public void setCanBeSeen(boolean canBeSeen) {
		this.canBeSeen = canBeSeen;
	}
}