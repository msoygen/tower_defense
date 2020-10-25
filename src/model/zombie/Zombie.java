package model.zombie;

import java.util.Arrays;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

abstract public class Zombie {
	private Point2D positionVector;

	protected int health;
	protected int speed;
	protected Color color;
	protected double transparency;
	
	public int path[][];
    public Point2D mapPosition = null, nextMapPosition = null, trackPath = null;
	public String direction = null;
	
	abstract public void draw(GraphicsContext gc, Zombie zombie);
	
	public Zombie(int health, int speed, int healthMultiplier, int speedMultiplier, double x, double y, int map[][]) {
		this.health = health * healthMultiplier;
		this.speed = speed  * speedMultiplier;
		this.positionVector = new Point2D(x, y);
        this.path = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
	}

	public Zombie(double x, double y, int map[][]){
		this.positionVector = new Point2D(x, y);
        this.path = Arrays.stream(map).map(int[]::clone).toArray(int[][]::new);
	}

	public void updateVector(Point2D diff) {
		Point2D newZombieVector;
		newZombieVector = this.getPositionVector().add(diff);
		this.setPositionVector(newZombieVector);
	}

	public Point2D getPositionVector(){
		return this.positionVector;
	}

	public void setPositionVector(Point2D vector){
		this.positionVector = vector;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getHealth(){
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void takeHit(int damageTaken) {
		this.health -= damageTaken;
	}

	public double getTransparency() {
		return this.transparency;
	}

	public void setTransparency(double transparency) {
		this.transparency = transparency;
	}
	
	@Override
	public String toString(){
		return "zombie";
	}
}
