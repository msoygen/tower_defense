package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import model.GameLevel;
import model.zombie.BossZombie;
import model.zombie.FastZombie;
import model.zombie.NormalZombie;
import model.zombie.SlowZombie;
import model.zombie.Zombie;
import view.LevelStage;

public class ZombieController {
    private int zombieCount;
    private int normalZombieCount;
    private int fastZombieCount;
    private int slowZombieCount;
    private int bossZombieCount;
    private Point2D zombieStartVector = null;
    private Point2D zombiePadding;
    private int[][] map;
    private boolean isFinished;

    private Queue<Zombie> zombieQueue = new LinkedList<Zombie>();
    public ArrayList<Zombie> drawnZombieList = new ArrayList<Zombie>();
    public ArrayList<Zombie> escapedZombieList = new ArrayList<Zombie>();
    public ArrayList<Zombie> deadZombieList = new ArrayList<Zombie>();
    private Scene currentScene;

    public ZombieController(int[][] map, LevelStage levelStage, GameLevel level) {
        this.isFinished = false;
        this.currentScene = levelStage.getScene();
        this.map = level.getMap();
        this.zombieCount = level.getZombieCount();
        this.normalZombieCount = level.getNormalZombieCount();
        this.fastZombieCount = level.getFastZombieCount();
        this.slowZombieCount = level.getSlowZombie();
        this.bossZombieCount = level.getBossZombie();
        this.zombiePadding = new Point2D(5.0, 5.0);
    }

    public void initZombies() {
        int outer_index = 0;
        int inner_index = 0;
        for (int[] row : this.map) {
            for (int block : row) {
                if (block == 1 && this.zombieStartVector == null) {
                    double y = (double) 50 * outer_index;
                    double x = (double) 50 * inner_index;
                    this.zombieStartVector = new Point2D(x + this.zombiePadding.getX(), y + this.zombiePadding.getY());
                }
                inner_index++;
            }
            outer_index++;
            inner_index = 0;
        }

        for (int i = 0; i < this.zombieCount; i++) {
            while (!this.randomZombieGenerator(i))
                ;
        }
    }

    public boolean spawnZombies() {

        if (this.drawnZombieList.isEmpty()) // avoiding uninitialized ArrayList
            return false;

        Zombie zombie = this.drawnZombieList.get(drawnZombieList.size() - 1);
        Point2D zombieVector;
        boolean isAway = true;

        if (zombie instanceof NormalZombie) {
            zombieVector = zombie.getPositionVector();
            isAway = zombieVector.getX() >= this.zombieStartVector.getX() + 70.0f
                    || zombieVector.getY() >= this.zombieStartVector.getY() + 60.0f;
            if (isAway && this.zombieQueue.peek() != null) { // avoiding empty queue
                Zombie temp = this.zombieQueue.remove();
                drawnZombieList.add(temp);
            }

        } else if (zombie instanceof FastZombie) {
            zombieVector = zombie.getPositionVector();
            isAway = zombieVector.getX() >= this.zombieStartVector.getX() + 95.0f
                    || zombieVector.getY() >= this.zombieStartVector.getY() + 85.0f;
            if (isAway && this.zombieQueue.peek() != null) { // avoiding empty queue
                Zombie temp = this.zombieQueue.remove();
                drawnZombieList.add(temp);
            }

        } else if (zombie instanceof SlowZombie) {
            zombieVector = zombie.getPositionVector();
            isAway = zombieVector.getX() >= this.zombieStartVector.getX() + 70.0f
                    || zombieVector.getY() >= this.zombieStartVector.getY() + 60.0f;
            if (isAway && this.zombieQueue.peek() != null) { // avoiding empty queue
                Zombie temp = this.zombieQueue.remove();
                drawnZombieList.add(temp);
            }

        } else if (zombie instanceof BossZombie) {
            zombieVector = zombie.getPositionVector();
            isAway = zombieVector.getX() >= this.zombieStartVector.getX() + 70.0f
                    || zombieVector.getY() >= this.zombieStartVector.getY() + 60.0f;
            if (isAway && this.zombieQueue.peek() != null) { // avoiding empty queue
                Zombie temp = this.zombieQueue.remove();
                drawnZombieList.add(temp);
            }

        }

        return true;
    }

    private void trackPath(Zombie zombie) {
        int j = (int) zombie.mapPosition.getX(); // its no more vector its indice of an 2d array
        int i = (int) zombie.mapPosition.getY();

        if (i < 16 && j < 12) {
            if (this.map[i + 1][j] == 1 && zombie.path[i + 1][j] == 1) { // down
                zombie.path[i][j] = 0;
                zombie.trackPath = new Point2D(0.0, zombie.getSpeed());
            } else if (this.map[i - 1][j] == 1 && zombie.path[i - 1][j] == 1) { // up
                zombie.path[i][j] = 0;
                zombie.trackPath = new Point2D(0.0, -zombie.getSpeed());
            } else if (this.map[i][j + 1] == 1 && zombie.path[i][j + 1] == 1) { // right
                zombie.path[i][j] = 0;
                zombie.trackPath = new Point2D(zombie.getSpeed(), 0.0);
            }
        }
    }

    private void getNextMapPosition(Point2D nextZombieVector, Zombie zombie) {
        int j = (int) zombie.mapPosition.getX(); // its no more vector its indice of an 2d array
        int i = (int) zombie.mapPosition.getY();

        if (i < 16 && j < 12) {
            if (this.map[i + 1][j] == 1 && zombie.path[i + 1][j] == 1) { // down
                zombie.nextMapPosition = nextZombieVector.add(0, 50);
            } else if (this.map[i - 1][j] == 1 && zombie.path[i - 1][j] == 1) { // up
                zombie.nextMapPosition = nextZombieVector.add(0, -50);
            } else if (this.map[i][j + 1] == 1 && zombie.path[i][j + 1] == 1) { // right
                zombie.nextMapPosition = nextZombieVector.add(50, 0);
            }
        }

    }

    private void getDirection(Zombie zombie) {
        int j = (int) zombie.mapPosition.getX(); // its no more vector its indice of an 2d array
        int i = (int) zombie.mapPosition.getY();

        if (i < 16 && j < 12) {
            if (this.map[i + 1][j] == 1 && zombie.path[i + 1][j] == 1) { // down
                zombie.direction = "down";
            } else if (this.map[i - 1][j] == 1 && zombie.path[i - 1][j] == 1) { // up
                zombie.direction = "up";
            } else if (this.map[i][j + 1] == 1 && zombie.path[i][j + 1] == 1) { // right
                zombie.direction = "right";
            }
        }
    }

    private void getMapPosition(Point2D zombieVector, Zombie zombie) {
        Point2D screen = new Point2D(this.currentScene.getWidth(), this.currentScene.getHeight());
        Point2D multiplier = new Point2D(screen.getY() / this.map.length, screen.getX() / this.map[0].length);

        int i = (int) Math.floor(((zombieVector.getX() - 5) / multiplier.getY()));
        int j = (int) Math.floor(((zombieVector.getY() - 5) / multiplier.getX()));
        zombie.mapPosition = new Point2D(i, j);

        if (zombie.direction == "up") {
            i = (int) ((zombieVector.getX() - 5) / multiplier.getY());
            j = (int) ((zombieVector.getY() + 35) / multiplier.getX());
            zombie.mapPosition = new Point2D(i, j);
        } else if (zombie.direction == "right") {
            i = (int) ((zombieVector.getX() + 35) / multiplier.getY());
            j = (int) ((zombieVector.getY() - 5) / multiplier.getX());
            zombie.mapPosition = new Point2D(i, j);
        }

    }

    public boolean updateZombies() {
        if (!this.drawnZombieList.isEmpty()) {
            List<Zombie> list = this.drawnZombieList;
            for (Iterator<Zombie> iterator = list.iterator(); iterator.hasNext();) {
                Zombie value = iterator.next();
                this.updateExistance(value, iterator);
            }
        }

        return this.isFinished;
    }

    public void updateExistance(Zombie zombie, Iterator<Zombie> iterator) {
        if (!this.inMap(zombie)) {
            Zombie deleted = zombie; 
            iterator.remove();
            this.escapedZombieList.add(deleted);
        }
        if (zombie.getHealth() <= 0) {
            Zombie deleted = zombie; 
            iterator.remove();
            this.deadZombieList.add(deleted);
        }
        if(this.zombieQueue.isEmpty() && this.drawnZombieList.isEmpty()){
            this.isFinished = true;
        }
    }

    public void updateDirection(Point2D zombieVector, Zombie zombie) {
        this.getMapPosition(zombieVector, zombie);
        this.getNextMapPosition(zombieVector, zombie);
        this.trackPath(zombie);
        this.getDirection(zombie);
    }

    public boolean inMap(Zombie zombie) {
        Point2D screen = new Point2D(this.currentScene.getWidth(), this.currentScene.getHeight());
        if (zombie.getPositionVector().getX() > screen.getX() && zombie.getPositionVector() != null) {
            return false;
        }

        return true;
    }

    public Point2D updatePosition(Point2D zombieVector, Zombie zombie) {
        if (zombie.trackPath == null) {
            this.updateDirection(zombieVector, zombie);
        } else {
            if (zombie.direction == "up") {
                if (zombieVector.getY() <= zombie.nextMapPosition.getY()) {
                    this.updateDirection(zombieVector, zombie);
                }
            } else if (zombie.direction == "down") {
                if (zombieVector.getY() >= zombie.nextMapPosition.getY()) {
                    this.updateDirection(zombieVector, zombie);
                }
            } else if (zombie.direction == "right") {
                if (zombieVector.getX() >= zombie.nextMapPosition.getX()) {
                    this.updateDirection(zombieVector, zombie);
                }
            }
        }

        return zombie.trackPath;
    }

    public void drawZombies(GraphicsContext gc) {
        this.spawnZombies();

        if (this.drawnZombieList.isEmpty() && !this.zombieQueue.isEmpty()) {
            Zombie temp = this.zombieQueue.remove();
            this.drawnZombieList.add(temp);
        }

        for (Zombie zombie : drawnZombieList) {
            Point2D diff;
            if (zombie instanceof NormalZombie) {
                NormalZombie nZombie = (NormalZombie) zombie;
                nZombie.draw(gc, zombie);

                diff = this.updatePosition(zombie.getPositionVector(), zombie);
                zombie.updateVector(diff);
            } else if (zombie instanceof FastZombie) {
                FastZombie fZombie = (FastZombie) zombie;
                fZombie.draw(gc, zombie);

                diff = this.updatePosition(zombie.getPositionVector(), zombie);
                zombie.updateVector(diff);
            } else if (zombie instanceof SlowZombie) {
                SlowZombie sZombie = (SlowZombie) zombie;
                sZombie.draw(gc, zombie);

                diff = this.updatePosition(zombie.getPositionVector(), zombie);
                zombie.updateVector(diff);
            } else if (zombie instanceof BossZombie) {
                BossZombie bZombie = (BossZombie) zombie;
                bZombie.draw(gc, zombie);

                diff = this.updatePosition(zombie.getPositionVector(), zombie);
                zombie.updateVector(diff);
            }
        }
    }

    private Boolean randomZombieGenerator(int iterator) {
        Random rand = new Random();
        int option = rand.nextInt((3 - 0) + 1) + 0;

        if (option == 0) {
            if (this.normalZombieCount > 0) {
                if (iterator == 0) {
                    this.zombieQueue.add(new NormalZombie(this.zombieStartVector.getX(),
                            this.zombieStartVector.getY(), this.map));
                } else {
                    this.zombieQueue.add(new NormalZombie(this.zombieStartVector.getX(),
                            this.zombieStartVector.getY(), this.map));
                }

                this.normalZombieCount--;
                return true;
            } else {
                return false;
            }
        } else if (option == 1) {
            if (this.fastZombieCount > 0) {
                if (iterator == 0) {
                    this.zombieQueue.add(new FastZombie(this.zombieStartVector.getX(),
                            this.zombieStartVector.getY(), this.map));
                } else {
                    this.zombieQueue.add(new FastZombie(this.zombieStartVector.getX(),
                            this.zombieStartVector.getY(), this.map));
                }

                this.fastZombieCount--;
                return true;
            } else {
                return false;
            }
        } else if (option == 2) {
            if (this.slowZombieCount > 0) {
                if (iterator == 0) {
                    this.zombieQueue.add(new SlowZombie(this.zombieStartVector.getX(),
                            this.zombieStartVector.getY(), this.map));
                } else {
                    this.zombieQueue.add(new SlowZombie(this.zombieStartVector.getX(),
                            this.zombieStartVector.getY(), this.map));
                }

                this.slowZombieCount--;
                return true;
            } else {
                return false;
            }

        } else if (option == 3) {
            if (this.bossZombieCount > 0) {
                if (iterator == 0) {
                    this.zombieQueue.add(new BossZombie(this.zombieStartVector.getX(),
                            this.zombieStartVector.getY(), this.map));
                } else {
                    this.zombieQueue.add(new BossZombie(this.zombieStartVector.getX(),
                            this.zombieStartVector.getY(), this.map));
                }

                this.bossZombieCount--;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}