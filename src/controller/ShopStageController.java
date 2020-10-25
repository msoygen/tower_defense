package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Label;
import model.GameButton;
import model.GameStore;
import model.ShopRead;
import model.UserRead;
import model.weapon.LightWeapon;
import model.weapon.HeavyWeapon;
import model.weapon.FastWeapon;
import model.weapon.Weapon;
import view.LevelStage;
import view.LevelsStage;
import view.ShopStage;

public class ShopStageController extends GameStageController {

	private GameButton nextButton, testGoldButton, buyLButton, buyHButton, buyFButton, sellLButton, sellHButton,
			sellFButton, cancelButton;
	private Label lLabel, hLabel, fLabel, goldLabel;

	// shop buttons

	private int fastWeaponCount = 0;
	private int heavyWeaponCount = 0;
	private int lightWeaponCount = 0;
	private int[] gunLimit; // light, fast, heavy

	public ShopStageController(ShopStage shopStage) {
		this.level = shopStage.level;
		this.currentStage = shopStage.getStage();

		ShopRead storeRead = new ShopRead();
		GameStore store = storeRead.XmlReader(shopStage.level);

		this.gunLimit = new int[] { store.getLightWeaponLimit(), store.getFastWeaponLimit(),
				store.getHeavyWeaponLimit() };

		this.setUser(shopStage.getUser());

		this.lLabel = shopStage.getLLabel();
		this.hLabel = shopStage.getHLabel();
		this.fLabel = shopStage.getFLabel();
		this.goldLabel = shopStage.getGoldLabel();

		this.goldLabel.setText("GOLD: " + String.valueOf(this.getUser().getGold()));

		this.nextButton = shopStage.getNextButton();
		this.testGoldButton = shopStage.getTestGoldButton();
		this.buyLButton = shopStage.getBuyLButton();
		this.buyHButton = shopStage.getBuyHButton();
		this.buyFButton = shopStage.getBuyFButton();
		this.sellLButton = shopStage.getSellLButton();
		this.sellHButton = shopStage.getSellHButton();
		this.sellFButton = shopStage.getSellFButton();
		this.cancelButton = shopStage.getCancelButton();

		this.level = shopStage.level;
	}

	public void startButtonHandlers() {
		this.buyLButtonHandler();
		this.buyHButtonHandler();
		this.buyFButtonHandler();
		this.sellLButtonHandler();
		this.sellHButtonHandler();
		this.sellFButtonHandler();
		this.nextButtonHandler();
		this.cancelButtonHandler();
		this.testGoldButtonHandler();
	}

	private void updateGolds() {
		UserRead userRead = new UserRead();
		userRead.ModifyXML(this.getUser());
	}

	private ArrayList<Weapon> initWeapons() {
		ArrayList<Weapon> weaphoneList = new ArrayList<Weapon>();

		for (int i = this.lightWeaponCount; i > 0; i--) {
			Weapon lWeapon = new LightWeapon(10, 6, 150, null);
			weaphoneList.add(lWeapon);
		}

		for (int i = this.heavyWeaponCount; i > 0; i--) {
			Weapon hWeapon = new HeavyWeapon(15, 9, 200, null);
			weaphoneList.add(hWeapon);
		}

		for (int i = this.fastWeaponCount; i > 0; i--) {
			Weapon fWeapon = new FastWeapon(20, 4, 120, null);
			weaphoneList.add(fWeapon);
		}

		return weaphoneList;
	}

	private boolean checkGunCount() {
		boolean lWCheck = false, fWCheck = false, hWCheck = false;
		if (this.gunLimit[0] > 0 && this.lightWeaponCount > 0) {
			lWCheck = true;
		}
		if (this.gunLimit[1] > 0 && this.fastWeaponCount > 0) {
			fWCheck = true;
		}
		if (this.gunLimit[2] > 0 && this.heavyWeaponCount > 0) {
			hWCheck = true;
		}
		return lWCheck || fWCheck || hWCheck;
	}

	private void nextButtonHandler() {
		this.nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (checkGunCount()) {
					updateGolds();
					setNextStage(new LevelStage(level, initWeapons(), getUser()));
					currentStage.close();
					currentStage = null;
					currentStage = nextStage;
					currentStage.show();
				}
			}
		});
	}

	private void cancelButtonHandler() {
		this.cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
					setNextStage(new LevelsStage(getUser()));
					currentStage.close();
					currentStage = null;
					currentStage = nextStage;
					currentStage.show();
			}
		});
	}

	private void testGoldButtonHandler() {
		this.testGoldButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getUser().setGold(50 + getUser().getGold());
				String textToSet = "GOLD";
				if (getUser().getGold() < 10) {
					textToSet = textToSet + "0";
				}
				goldLabel.setText("GOLD: " + getUser().getGold());
			}
		});
	}

	private void buyLButtonHandler() {
		lLabel.setText(lightWeaponCount + "/" + gunLimit[0]);
		this.buyLButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if ((getUser().getGold() - 10) >= 0 && gunLimit[0] > lightWeaponCount) {
					getUser().setGold(getUser().getGold() - 10);
					goldLabel.setText("GOLD: " + (getUser().getGold()));
					lightWeaponCount++;
					lLabel.setText(lightWeaponCount + "/" + gunLimit[0]);
				}
			}
		});
	}

	private void buyFButtonHandler() {
		fLabel.setText(fastWeaponCount + "/" + gunLimit[1]);
		this.buyFButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if ((getUser().getGold() - 20) >= 0 && gunLimit[1] > fastWeaponCount) {
					getUser().setGold(getUser().getGold() - 20);
					goldLabel.setText("GOLD: " + (getUser().getGold()));
					fastWeaponCount++;
					fLabel.setText(fastWeaponCount + "/" + gunLimit[1]);
				}
			}
		});
	}

	private void buyHButtonHandler() {
		hLabel.setText(heavyWeaponCount + "/" + gunLimit[2]);
		this.buyHButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if ((getUser().getGold() - 15) >= 0 && gunLimit[2] > heavyWeaponCount) {
					getUser().setGold(getUser().getGold() - 15);
					goldLabel.setText("GOLD: " + (getUser().getGold()));
					heavyWeaponCount++;
					hLabel.setText(heavyWeaponCount + "/" + gunLimit[2]);
				}
			}
		});
	}

	private void sellLButtonHandler() {
		this.sellLButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (lightWeaponCount > 0) {
					getUser().setGold(getUser().getGold() + 10);
					goldLabel.setText("GOLD: " + (getUser().getGold()));
					lightWeaponCount--;
					lLabel.setText(lightWeaponCount + "/" + gunLimit[0]);
				}
			}
		});
	}

	private void sellFButtonHandler() {
		this.sellFButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (fastWeaponCount > 0) {
					getUser().setGold(getUser().getGold() + 20);
					goldLabel.setText("GOLD: " + (getUser().getGold()));
					fastWeaponCount--;
					fLabel.setText(fastWeaponCount + "/" + gunLimit[1]);
				}
			}
		});
	}

	private void sellHButtonHandler() {
		this.sellHButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (heavyWeaponCount > 0) {
					getUser().setGold(getUser().getGold() + 15);
					goldLabel.setText("GOLD: " + (getUser().getGold()));
					heavyWeaponCount--;
					hLabel.setText(heavyWeaponCount + "/" + gunLimit[2]);
				}
			}
		});
	}

}