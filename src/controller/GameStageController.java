package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GameUser;
import view.GameStage;
import view.LevelStage;
import view.LevelsStage;
import view.MainStage;
import view.ShopStage;

interface GameStageControllerInterface{
    public Scene getScene();
    public Stage getStage();
    public void setNextStage(GameStage gameStage);
}

abstract class GameStageController implements GameStageControllerInterface{
    protected Stage currentStage, nextStage;
    protected Scene currentScene;
    private GameUser user;
    public int level;
    
    public GameStageController(){
        this.user = null;
    }

    public Scene getScene(){
        return this.currentScene;
    }

    public Stage getStage(){
        return this.currentStage;
    }

    public void setNextStage(GameStage gameStage){
        if(gameStage instanceof LevelsStage){
            LevelsStage levelsStage = (LevelsStage) gameStage;
            this.nextStage = levelsStage.getStage();
        }else if(gameStage instanceof LevelStage){
            LevelStage levelStage = (LevelStage) gameStage;
            this.nextStage = levelStage.getStage();
        }else if(gameStage instanceof ShopStage){
            ShopStage shopStage = (ShopStage) gameStage;
            this.nextStage = shopStage.getStage();
        }else if(gameStage instanceof MainStage) {
            MainStage mainStage = (MainStage) gameStage;
            this.nextStage = mainStage.getStage();
        }
    }
    
    public GameUser getUser(){
        return this.user;
    }

    public void setUser(GameUser user){
        this.user = user;
    }
}