package model.zombie;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FastZombie extends Zombie {

	public FastZombie(double x, double y, int path[][]){
		super(x, y, path);
		this.health = 5;
		this.speed = 5; 
	}

    public FastZombie(int health, int speed, int healthMultiplier, int speedMultiplier, double x, double y, int path[][]) {
        super(health, speed, healthMultiplier, speedMultiplier, x, y, path);
    }
	
	@Override
	public String toString(){
		return " fast";
	}
	
	
	@Override
	public void draw(GraphicsContext gc, Zombie zombie) {
		this.transparency = 1.0;
		this.color = Color.web("84C3BE", this.transparency);
		gc.setFill(this.color);
		gc.fillRect(zombie.getPositionVector().getX(), zombie.getPositionVector().getY(), 40.0, 40.0);
	}
}
