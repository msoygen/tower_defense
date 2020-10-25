package controller;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import model.weapon.LightWeapon;
import model.weapon.HeavyWeapon;
import model.weapon.FastWeapon;
import model.weapon.Weapon;
import model.zombie.Zombie;

import view.LevelStage;

public class WeaponController {
    private int[][] map;
    private Point2D weaphonePadding;
    private Scene currentScene;
    private double frameRate;
    private int frameCount;

    private ArrayList<Weapon> weaphoneList;

    public WeaponController(int[][] map, LevelStage levelStage, ArrayList<Weapon> weaphoneList, int frameCount,
            double frameRate) {
        this.currentScene = levelStage.getScene();
        this.map = map;
        this.weaphoneList = weaphoneList;
        this.weaphonePadding = new Point2D(10.0, 10.0);
        this.frameCount = frameCount;
        this.frameRate = frameRate;
    }

    private Zombie inRange(ArrayList<Zombie> drawnZombieList, Weapon weapon) {
        for (Zombie zombie : drawnZombieList) {
            if (zombie.getPositionVector().getX() > (weapon.position.getX() - weapon.range)
                    && zombie.getPositionVector().getX() < (weapon.position.getX() + weapon.range)
                    && zombie.getPositionVector().getY() > (weapon.position.getY() - weapon.range)
                    && zombie.getPositionVector().getY() < (weapon.position.getY() + weapon.range)) {
                return zombie;
            }
        }

        return null;
    }

    public void updateWeapon(GraphicsContext gc, ArrayList<Zombie> drawnZombieList, double frameRate, int frameCount,
            ZombieController zController) {
        this.frameCount = frameCount;
        Zombie inRangeZombie;

        for (Weapon weapon : weaphoneList) {
            if (weapon instanceof FastWeapon) {
                FastWeapon fWeapon = (FastWeapon) weapon;
                inRangeZombie = this.inRange(drawnZombieList, weapon);
                if (inRangeZombie != null) {
                    fWeapon.drawBullet(gc, weapon, inRangeZombie);
                    fWeapon.shoot(inRangeZombie, this.frameCount);
                }
                inRangeZombie = null;
            } else if (weapon instanceof HeavyWeapon) {
                HeavyWeapon hWeapon = (HeavyWeapon) weapon;
                inRangeZombie = this.inRange(drawnZombieList, weapon);
                if (inRangeZombie != null) {
                    hWeapon.drawBullet(gc, weapon, inRangeZombie);
                    hWeapon.shoot(inRangeZombie, this.frameCount);
                }
                inRangeZombie = null;
            } else if (weapon instanceof LightWeapon) {
                LightWeapon lWeapon = (LightWeapon) weapon;
                inRangeZombie = this.inRange(drawnZombieList, weapon);
                if (inRangeZombie != null) {
                    lWeapon.drawBullet(gc, weapon, inRangeZombie);
                    lWeapon.shoot(inRangeZombie, this.frameCount);
                }
                inRangeZombie = null;
            }
        }

    }

    public void initWeapons() {
        int outer_index = 0;
        int inner_index = 0;

        ArrayList<Point2D> weaphonePositions = new ArrayList<Point2D>();

        for (int[] row : this.map) {
            for (int block : row) {
                if (block == 2) {
                    weaphonePositions.add(new Point2D(inner_index * 50, outer_index * 50));
                }
                inner_index++;
            }
            outer_index++;
            inner_index = 0;
        }

        // iterating two lists simultaneously
        // avoiding giving same coordinates to all weaphones
        Iterator<Weapon> weaphoneIterator = weaphoneList.iterator();
        Iterator<Point2D> weaphonePositionIterator = weaphonePositions.iterator();
        while (weaphoneIterator.hasNext() && weaphonePositionIterator.hasNext()) {
            Weapon weaphone = weaphoneIterator.next();
            Point2D position = weaphonePositionIterator.next();
            weaphone.position = new Point2D(position.getX() + this.weaphonePadding.getX(),
                    position.getY() + this.weaphonePadding.getY());
        }

        for (Weapon weaphone : this.weaphoneList) {
            if (weaphone instanceof LightWeapon) {
                weaphone.fireRate = (int) this.frameRate / 3; // 3 per sec
            } else if (weaphone instanceof HeavyWeapon) {
                weaphone.fireRate = (int) this.frameRate / 2; // 2 per sec
            } else if (weaphone instanceof FastWeapon) {
                weaphone.fireRate = (int) this.frameRate / 5; // 5 per sec
            }
        }
    }

    public void drawWeapons(GraphicsContext gc) {
        for (Weapon weaphone : this.weaphoneList) {
            if (weaphone instanceof LightWeapon) {
                LightWeapon l = (LightWeapon) weaphone;
                l.draw(gc);
            } else if (weaphone instanceof HeavyWeapon) {
                HeavyWeapon h = (HeavyWeapon) weaphone;
                h.draw(gc);
            } else if (weaphone instanceof FastWeapon) {
                FastWeapon f = (FastWeapon) weaphone;
                f.draw(gc);
            }
        }
    }
}