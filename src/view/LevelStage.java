package view;

import javafx.util.Duration;
import model.GameUser;
import model.weapon.Weapon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;

import controller.LevelStageController;
import controller.WeaponController;
import controller.ZombieController;

public class LevelStage extends GameStage {
    private ArrayList<Weapon> weaphoneList = null;

    private Group root;
    private Canvas canvas;

    private double frameRate = 30;
    private int frameCount = 1;

    private boolean isFinished;

    private GraphicsContext gc;
    private Timeline gameLoop;
    private KeyFrame kf;
    final long timeStart = System.currentTimeMillis();

    private LevelStageController controller;
    private ZombieController zController;
    private WeaponController wController;

    public LevelStage(int level, ArrayList<Weapon> weaphoneList, GameUser user) {
        this.isFinished = false;

        this.level = level;
        this.weaphoneList = weaphoneList;

        this.setUser(user);

        this.InitStage(800, 600);
        this.InitController();

        this.startGameLoop();
    }

    @Override
    public void InitStage(int width, int height) {
        // screen elements
        this.root = new Group();
        this.currentStage = new Stage();
        this.currentScene = new Scene(this.root, 800, 600);
        this.currentStage.setScene(this.currentScene);

        this.canvas = new Canvas(800, 600);
        this.root.getChildren().add(this.canvas);
        this.currentStage.setTitle("level " + (this.level + 1));
    }

    @Override
    public void InitController() {
        this.controller = new LevelStageController(this, this.weaphoneList, this.frameCount, this.frameRate);
        this.zController = this.controller.getZombieController();
        this.wController = this.controller.getWeaponController();
    }

    public ZombieController getZombieController() {
        return this.zController;
    }

    public WeaponController getWeaponController() {
        return this.wController;
    }

    public void initGameLoop() {
        this.gc = canvas.getGraphicsContext2D();
        this.gameLoop = new Timeline();
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);
    }

    public void startGameLoop() {

        this.initGameLoop();
        this.zController.initZombies();
        this.wController.initWeapons();
        this.controller.startButtonHandler();

        this.kf = new KeyFrame(Duration.seconds(1 / frameRate), // 60 FPS
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        if (!isFinished) {
                            // Clear the canvas
                            gc.clearRect(0, 0, 800, 600);

                            controller.updateGameState(gameLoop);
                            controller.drawMap(gc);

                            wController.updateWeapon(gc, zController.drawnZombieList, frameRate, frameCount,
                                    zController);
                            wController.drawWeapons(gc);

                            isFinished = zController.updateZombies();
                            zController.drawZombies(gc);
                        } else {
                            controller.updateGameState(gameLoop);
                            // Clear the canvas
                            controller.drawEndLevel(gc, zController.escapedZombieList, zController.deadZombieList,
                                    canvas.getWidth(), canvas.getHeight(), isFinished);
                        }

                        frameCount++;
                        if (frameCount == frameRate) {
                            frameCount = 1;
                        }
                    }
                });

        this.execGameLoop();
    }

    public void execGameLoop() {
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        this.currentStage.show();
    }
}