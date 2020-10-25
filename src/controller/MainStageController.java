package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import model.GameButton;
import model.GameUser;
import model.UserRead;
import view.LevelsStage;
import view.MainStage;

public class MainStageController extends GameStageController {

	private GameButton startButton, exitButton;
	private TextField userTextField;

	public MainStageController(MainStage mainStage) {
		this.currentStage = mainStage.getStage();
		this.userTextField = mainStage.getUserTextField();
		this.startButton = mainStage.getStartButton();
		this.exitButton = mainStage.getExitButton();
	}

	public void startButtonHandlers() {
		this.startButtonHandler();
		this.exitButtonHandler();
	}

	private GameUser checkTextField(){
		GameUser user = null;
		if(this.userTextField.getText().isEmpty()){
			this.userTextField.setPromptText("please fill out this field");
			return null;
		}else{
			UserRead userRead = new UserRead();
			user = userRead.initUser(this.userTextField.getText());
			return user;
		}
	}

	private void startButtonHandler() {

		this.startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameUser user = checkTextField();
				if(user != null){
					setNextStage(new LevelsStage(user));
					currentStage.close();
					currentStage = null;
					currentStage = nextStage;
					currentStage.show();
				}
			}
		});
	}

	private void exitButtonHandler() {
		this.exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				currentStage.close();
			}
		});
	}
}