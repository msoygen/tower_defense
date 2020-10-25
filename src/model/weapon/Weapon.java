package model.weapon;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import model.zombie.Zombie;

public abstract class Weapon {
	public int cost;
	public int attack;
	public int range;
	public int fireRate; // per sec
	public Point2D position;

	public Weapon() {

	}

	public Weapon(int attack, int cost) {
		this.attack = attack;
		this.cost = cost;
	}

	public Weapon(int attack, int cost, int range, Point2D position) {
		this.attack = attack;
		this.cost = cost;
		this.range = range;
		this.position = position;
	}

	abstract public void shoot(Zombie zombie, int frameCount);

	abstract public void draw(GraphicsContext gc);

	abstract public void drawBullet(GraphicsContext gc, Weapon weapon, Zombie inRangeZombie);
}
