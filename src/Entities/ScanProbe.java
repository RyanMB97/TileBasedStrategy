package Entities;

import java.awt.Graphics;

import Core.Game;

public class ScanProbe extends Entity {

	public ScanProbe(Game game, int x, int y) {
		this.game = game;
		
		this.x = x;
		this.y = y;

		viewRange = 5;
		damage = 0;
		attackRange = 0;
		imageID = 0;

		this.tileX = x / 32;
		this.tileY = y / 32;
	}

	public void update() {
		seeTiles(game.getLevel().getTiles());
	}

	public void render(Graphics g) {
		g.drawImage(game.getGameResources().units[imageID], x, y, game);
	}
}