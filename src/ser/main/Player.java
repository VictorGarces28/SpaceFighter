package ser.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ser.main.interfaces.EntityA;
import ser.main.interfaces.EntityB;

public class Player extends GameObject implements EntityA {

	private double velX = 0;
	private double velY = 0;

	private Texture tex;

	Game game;
	private int level;
	Controller controller;
	Random rand = new Random();
	int randomPlayer;

	public Player(double x, double y, Texture tex, Game game, Controller controller) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
		level = game.getLevel();
		randomPlayer = rand.nextInt(5);
	}

	public void tick() {
		x += velX;
		y += velY;

		if (x <= 0)
			x = 0;
		if (x >= 640 - 32)
			x = 640 - 32;
		if (y < 0)
			y = 0;
		if (y >= 480 - 40)
			y = 480 - 40;

		for (int i = 0; i < game.eb.size(); i++) {
			EntityB tempEnt = game.eb.get(i);

			if (Physics.Collision(this, tempEnt)) {
				controller.removeEntity(tempEnt);
				Game.HEALTH -= 25;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}

		}
	}

	public void render(Graphics g) {
		if (randomPlayer == 4) {
			g.drawImage(tex.mainPlayer, (int) x, (int) y, null);

		} else {
			g.drawImage(tex.players[randomPlayer], (int) x, (int) y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

}
