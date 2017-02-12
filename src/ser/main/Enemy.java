package ser.main;

import java.awt.Graphics;
import java.util.Random;

public class Enemy {
	
	private double x, y;
	Random r = new Random();
	
	private Texture tex;
	
	public Enemy(double x, double y, Texture tex)
	{
	this.x = x;
	this.y = y;		
	this.tex = tex;
	}

	public void tick()
	{
		y += 2;
		
		if (y>(Game.HEIGHT * Game.SCALE))
		{
			y = 0;
			x= r.nextInt(Game.WIDTH * Game.SCALE);
		}	
			
	}
	
	public void render(Graphics g)
	{
		g.drawImage(tex.enemy, (int) x, (int) y, null);
	}
	
	public double getY()
	{
		return y;
	}
	public void setY(double y)
	{
		this.y = y;
	}
	
}
