package ser.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import ser.main.interfaces.EntityA;
import ser.main.interfaces.EntityB;

public class Enemy extends GameObject implements EntityB {

	private Texture tex;
	Random r = new Random();

	private Game game;
	private Controller c;
	private int level;
	private int speed = r.nextInt(3) + 4 / 3;
	private Player p;

	public Enemy(double x, double y, Texture tex, Controller c, Game game) {
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		p = game.getPlayer();
		level = game.getLevel();
	}

	public void tick() {
		y += speed + (game.getLevel() / 2);
		setX(p.getX());

		if (y > Game.HEIGHT * Game.SCALE) {
			x = p.getX();
			y = -5;
		}

		for (int i = 0; i < game.ea.size(); i++) {
			EntityA tempEnt = game.ea.get(i);

			if (Physics.Collision(this, tempEnt)) {
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				game.setScore(); // adding score
				game.setEnemy_killed(game.getEnemy_killed() + 1);

			}
		}

	}

	public void render(Graphics g) {
		if (level >= 1 && level <= 3) {
			g.drawImage(tex.enemy[0], (int) x, (int) y, null);
		} else if (level > 3 && level <= 6) {
			g.drawImage(tex.enemy[1], (int) x, (int) y, null);
		} else {
			g.drawImage(tex.enemy[2], (int) x, (int) y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y * 0.8;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		if (x < this.x) {
			this.x -= 0.5;
		} else {
			this.x += 0.5;

		}

	}

}
