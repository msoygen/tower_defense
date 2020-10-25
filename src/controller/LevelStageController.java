package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.GameLevel;
import model.LevelRead;
import model.UserRead;
import model.weapon.Weapon;
import model.zombie.BossZombie;
import model.zombie.FastZombie;
import model.zombie.NormalZombie;
import model.zombie.SlowZombie;
import model.zombie.Zombie;
import view.LevelStage;
import view.LevelsStage;

public class LevelStageController extends GameStageController {

	private int[][] map;
	private GameLevel level;

	private int rewardAmount;
	private boolean isFinished;
	private boolean isSuccess;
	private Timeline gameLoop;

	private ZombieController zc;
	private WeaponController wc;

	public LevelStageController(LevelStage levelStage, ArrayList<Weapon> weaphoneList, int frameCount,
			double frameRate) {
		this.setUser(levelStage.getUser());

		LevelRead levelRead = new LevelRead();
		level = levelRead.XmlReader(levelStage.level);
		this.map = level.getMap();
		this.rewardAmount = level.getReward();

		this.zc = new ZombieController(this.map, levelStage, level);
		this.wc = new WeaponController(this.map, levelStage, weaphoneList, frameCount, frameRate);

		this.currentStage = levelStage.getStage();
		this.currentScene = levelStage.getScene();
		this.rewardAmount = level.getReward();
		this.isFinished = false;
		this.isSuccess = false;
		this.gameLoop = null;
	}

	public ZombieController getZombieController() {
		return this.zc;
	}

	public WeaponController getWeaponController() {
		return this.wc;
	}

	public void drawMap(GraphicsContext gc) {
		Point2D rectVector = new Point2D(0.0, 0.0);

		// scanning map info from levels.txt
		for (int[] row : this.map) {
			for (int block : row) {

				// zeroes (empty blocks)
				if (block == 0 || block == 2) {
					Color c = Color.web("271511", 1.0);
					gc.setFill(c);
					gc.fillRect(rectVector.getX(), rectVector.getY(), 50.0, 50.0);
				} else if (block == 1) {// ones (road blocks)
					Color c = Color.web("5E3F31", 1.0);
					gc.setFill(c);
					gc.fillRect(rectVector.getX(), rectVector.getY(), 50.0, 50.0);
				}
				rectVector = rectVector.add(50.0, 0.0);

			}
			rectVector = rectVector.subtract(rectVector.getX(), -50.0);
		}
		rectVector = rectVector.multiply(0);
	}

	public void drawEndLevel(GraphicsContext gc, ArrayList<Zombie> escapedZombieList, ArrayList<Zombie> deadZombieList,
			double screenWidth, double screenHeight, boolean isFinished) {
		this.isFinished = isFinished;

		gc.clearRect(0, 0, screenWidth, screenHeight);

		gc.setFill(Color.web("271511", 1.0));
		gc.fillRect(0, 0, screenWidth, screenHeight);

		String FONT_PATH = "/kenvector_future.ttf";
		try {
			gc.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 18));
		} catch (FileNotFoundException e) {
			gc.setFont(Font.font("Verdana", 23));
		}
		gc.setFill(Color.web("315e3f", 1.0));
		gc.fillText("dead zombies: " + deadZombieList.size(), 20.0, 160.0);

		int bZombieCount = 0;
		int fZombieCount = 0;
		int nZombieCount = 0;
		int sZombieCount = 0;
		for (Zombie zombie : deadZombieList) {
			if (zombie instanceof BossZombie) {
				bZombieCount++;
			} else if (zombie instanceof FastZombie) {
				fZombieCount++;
			} else if (zombie instanceof NormalZombie) {
				nZombieCount++;
			} else if (zombie instanceof SlowZombie) {
				sZombieCount++;
			}
		}

		gc.fillText("dead boss zombie count: " + bZombieCount, 20.0, 190.0);
		gc.fillText("dead fast zombie count: " + fZombieCount, 20.0, 210.0);
		gc.fillText("dead normal zombie count: " + nZombieCount, 20.0, 230.0);
		gc.fillText("dead slow zombie count: " + sZombieCount, 20.0, 250.0);

		bZombieCount = 0;
		fZombieCount = 0;
		nZombieCount = 0;
		sZombieCount = 0;
		for (Zombie zombie : escapedZombieList) {
			if (zombie instanceof BossZombie) {
				bZombieCount++;
			} else if (zombie instanceof FastZombie) {
				fZombieCount++;
			} else if (zombie instanceof NormalZombie) {
				nZombieCount++;
			} else if (zombie instanceof SlowZombie) {
				sZombieCount++;
			}
		}

		gc.fillText("escaped zombies: " + escapedZombieList.size(), 400.0, 160.0);

		gc.fillText("escaped boss zombie count: " + bZombieCount, 400.0, 190.0);
		gc.fillText("escaped fast zombie count: " + fZombieCount, 400.0, 210.0);
		gc.fillText("escaped normal zombie count: " + nZombieCount, 400.0, 230.0);
		gc.fillText("escaped slow zombie count: " + sZombieCount, 400.0, 250.0);

		if(zc.escapedZombieList.isEmpty()){
			this.isSuccess = true;
		}

		if(this.isSuccess){
			gc.fillText("reward amount: " + this.rewardAmount, 280, 360);
		}else{
			gc.fillText("reward amount: " + (this.rewardAmount / 2), 280, 360);
		}

		gc.setFill(Color.web("5E3F31", 1.0));
		gc.fillText("press ENTER to exit", 280, 450);


	}

	private void updateGolds(int gold) {
		this.getUser().setGold(this.getUser().getGold() + gold);
		UserRead userRead = new UserRead();
		userRead.ModifyXML(this.getUser());
	}

	public void updateGameState(Timeline gameLoop) {
		this.gameLoop = gameLoop;
	}

	public void startButtonHandler() {

		this.currentScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String code = event.getCode().toString();
				if (code.equals("ENTER")) {
					if (isSuccess) {
						updateGolds(rewardAmount);
					}else{
						updateGolds(rewardAmount / 2);
					}
					gameLoop.stop();
					setNextStage(new LevelsStage(getUser()));
					currentStage.close();
					currentStage = null;
					currentStage = nextStage;
					currentStage.show();
				}
			}
		});
	}
}