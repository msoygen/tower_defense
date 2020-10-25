package model.weapon;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.zombie.Zombie;

public class LightWeapon extends Weapon {
    public LightWeapon(int cost, int attack, int range, Point2D position) {
        this.cost = cost;
        this.attack = attack;
        this.range = range;
        this.position = position;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Color c = Color.web("274b2e", 1.0);
        gc.setFill(c);
        gc.fillRect(this.position.getX(), this.position.getY(), 30.0, 30.0);
    }

    @Override
    public void drawBullet(GraphicsContext gc, Weapon weapon, Zombie inRangeZombie) {
        gc.setFill(Color.RED);
        gc.setStroke(Color.YELLOWGREEN);
        gc.setLineWidth(1);
        gc.beginPath();
        gc.moveTo(weapon.position.getX() + 15, weapon.position.getY() + 15);
        gc.stroke();
        gc.lineTo(inRangeZombie.getPositionVector().getX() + 20, inRangeZombie.getPositionVector().getY() + 20);
        gc.stroke();
    }

    @Override
    public void shoot(Zombie zombie, int frameCount) {
		if (frameCount % this.fireRate == 0) {
            zombie.takeHit(attack);
            zombie.setTransparency(zombie.getTransparency() - 0.1);
		}
    }
}
