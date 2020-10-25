package model.zombie;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NormalZombie extends Zombie {

	public NormalZombie(double x, double y, int path[][]){
		super(x, y, path);
		this.health = 15;
		this.speed = 5; 
	}

	public NormalZombie(int health, int speed, int healthMultiplier, int speedMultiplier, double x, double y,
			int path[][]) {
		super(health, speed, healthMultiplier, speedMultiplier, x, y, path);
	}

	@Override
	public String toString() {
		return " normal";
	}

	@Override
	public void draw(GraphicsContext gc, Zombie zombie) {
		this.transparency = 1.0;
		this.color = Color.web("EBA909", this.transparency);
		gc.setFill(this.color);
		gc.fillRect(zombie.getPositionVector().getX(), zombie.getPositionVector().getY(), 40.0, 40.0);

	}
	
}