package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import controller.MainStageController;
import model.GameButton;

public class MainStage extends GameStage {

	private final int height = 600;
	private final int width = 800;
	private MainStageController controller;
	private TextField userTextField;

	private final static int MENU_BUTTONS_START_X = 305;
	private final static int MENU_BUTTONS_START_Y = 300;

	private List<GameButton> menuButtons;

	public MainStage() {
		this.InitStage(this.width, this.height);
		this.InitStageElements();
		this.AddUserLogin();
		this.InitController();
	}

	public void StartController() {
		this.currentStage.show();
		this.controller.startButtonHandlers();
	}

	@Override
	public void InitController() {
		this.controller = new MainStageController(this);
		this.StartController();
	}

	public void InitStageElements() {
		this.menuButtons = new ArrayList<>();

		this.createButtons();
		//this.createProfileInfo();
		this.createBackground();
	}

	public GameButton getStartButton() {
		return this.menuButtons.get(0);
	}

	public GameButton getExitButton() {
		return this.menuButtons.get(1);
	}

	public TextField getUserTextField(){
		return this.userTextField;
	}

	private void AddUserLogin() {
		// final TextField user = new TextField();
		this.userTextField = new TextField();
		this.userTextField.setPrefWidth(190);
		this.userTextField.setPromptText("Enter username...");
		this.userTextField.setLayoutX(305);
		this.userTextField.setLayoutY(250);
		this.pane.getChildren().add(this.userTextField);
		// setName(user.getText());
	}

	private void addMenuButtons(GameButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		pane.getChildren().add(button);
	}

	private void createButtons() {
		createStartButton();
		createExitButton();
	}

	private void createStartButton() {
		String pressed_style = "-fx-background-color: transparent; -fx-background-image: url('yellow_button03.png');";
		String free_style = "-fx-background-color: transparent; -fx-background-image: url('/yellow_button04.png');";
		GameButton startButton = new GameButton("PLAY", 190, 49, pressed_style, free_style);
		addMenuButtons(startButton);
	}

	private void createExitButton() {
		String pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/yellow_button03.png');";
		String free_style = "-fx-background-color: transparent; -fx-background-image: url('/yellow_button04.png');";
		GameButton exitButton = new GameButton("QUIT", 190, 49, pressed_style, free_style);
		addMenuButtons(exitButton);
	}

	private void createBackground() {
		Image backgroundImage = new Image("/bg.png", 800, 600, false, true);
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		pane.setBackground(new Background(background));
	}

}