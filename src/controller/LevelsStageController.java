package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.GameButton;
import view.LevelsStage;
import view.MainStage;
import view.ShopStage;

public class LevelsStageController extends GameStageController{

	private GameButton level01Button, level02Button;

	public LevelsStageController(LevelsStage levelsStage){
		this.currentStage = levelsStage.getStage();
		this.setUser(levelsStage.getUser());
        this.level01Button = levelsStage.getLevel01Button();
        this.level02Button = levelsStage.getLevel02Button();
        this.currentScene = levelsStage.getScene();
    }
	
	public void startButtonHandlers(){
        this.level01ButtonHandler();
        this.level02ButtonHandler();
        this.escapeButtonHandler();
    }
    
    private void level01ButtonHandler(){

		this.level01Button.setOnAction( new EventHandler<ActionEvent>() {
			  
			@Override
			public void handle(ActionEvent event) {
                setNextStage(new ShopStage(0, getUser()));
				currentStage.close();
				currentStage = null;
				currentStage = nextStage;
				currentStage.show();
			}
		});
    }
    
    private void escapeButtonHandler(){
		this.currentScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					setNextStage(new MainStage());
					currentStage.close();
					currentStage = null;
					currentStage = nextStage;
					currentStage.show();
				}
			}
		});
    }
    
    private void level02ButtonHandler(){

		this.level02Button.setOnAction( new EventHandler<ActionEvent>() {
			  
			@Override
			public void handle(ActionEvent event) {
				setNextStage(new ShopStage(1, getUser()));
				currentStage.close();
				currentStage = null;
				currentStage = nextStage;
				currentStage.show();
			}
		});
    }
}