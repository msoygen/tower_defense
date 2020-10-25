package view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GameUser;

interface GameStageInterface {
	public Scene getScene();

	public Stage getStage();

	public void InitStage(int width, int height);

	public void InitController();
}

public class GameStage implements GameStageInterface {
	protected AnchorPane pane;
	protected Stage currentStage;
	protected Scene currentScene;
	public int level;
	private GameUser user;
	private int userGold;
	

	public GameStage() {
		this.user = null;
	}

	public void InitStage(int width, int height) {
		this.pane = new AnchorPane();
		this.currentScene = new Scene(this.pane, width, height);
		this.currentStage = new Stage();
		this.currentStage.setScene(this.currentScene);
	}

	public Scene getScene() {
		return this.currentScene;
	}

	public Stage getStage() {
		return this.currentStage;
	}

	public void InitController() {
	}

    public GameUser getUser(){
        return this.user;
    }

    public void setUser(GameUser user){
        this.user = user;
    }

    public int getUserGold(){
        return this.userGold;
    }

    public void setUserGold(int userGold){
        this.userGold = userGold;
    }
}