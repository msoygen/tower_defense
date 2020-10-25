package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import controller.LevelsStageController;
import model.GameButton;
import model.GameUser;

public class LevelsStage extends GameStage{
	
	private final int height = 600;
	private final int width = 800;
	private LevelsStageController controller;
	
	private List<GameButton> levelsButtons;

	public LevelsStage(GameUser user) {
		this.setUser(user);
		this.InitStage(this.width, this.height);
		this.InitStageElements();
		this.InitController();
	}

	public void StartController() {
		this.controller.startButtonHandlers();
	}

	@Override
	public void InitController(){
		this.controller = new LevelsStageController(this);
		this.StartController();
	}

    public void InitStageElements(){
		this.levelsButtons = new ArrayList<>();
		
		this.createBackground();
		this.createButtons();
		this.createExitLabel();
	}

    public GameButton getLevel01Button() {
        return this.levelsButtons.get(0);
    }
    
    public GameButton getLevel02Button(){
        return this.levelsButtons.get(1);
    }
    
    public void createExitLabel() {
        Label exitLabel = new Label();
        exitLabel.setLayoutX(170);
        exitLabel.setLayoutY(400);
        exitLabel.setFont(new Font("Verdana", 30));
        exitLabel.setTextFill(Color.web("5E3F31", 1.0));
        exitLabel.setText("PRESS ESC TO GO MAIN MENU");
        this.pane.getChildren().add(exitLabel);
    }
    
	private void addlevelButtons(GameButton button, int x, int y) {	
		button.setLayoutX(x);
		button.setLayoutY(y);
		levelsButtons.add(button);
		pane.getChildren().add(button);
	}
	
	private void createButtons() {
		createLevelButtons();
	}

	private void createLevelButtons() {
		String pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/level_button1.png');";
		String free_style = "-fx-background-color: transparent; -fx-background-image: url('/level_button2.png');";
		GameButton levelButton1 = new GameButton("1", 100, 100, pressed_style, free_style);
        addlevelButtons(levelButton1, 250, 250);
    
		GameButton levelButton2 = new GameButton("2", 100, 100, pressed_style, free_style);
        addlevelButtons(levelButton2, 450, 250);
	}
	private void createBackground() {
		Image backgroundImage = new Image("/levels.png", 800, 600, false, true);
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		pane.setBackground(new Background(background));
	}
}
