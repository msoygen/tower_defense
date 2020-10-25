package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import controller.ShopStageController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.GameButton;
import model.GameUser;

public class ShopStage extends GameStage {
    private List<GameButton> shopButtons;
    private List<Label> shopLabels;
    private ShopStageController controller;

    private final int width = 800;
    private final int height = 600;

    public ShopStage(int level, GameUser user) {
        // initializeStage();
        this.setUser(user);
        this.level = level;
        this.InitStage(this.width, this.height);
        this.InitStageElements();
        this.InitController();
    }

    @Override
    public void InitController() {
        this.controller = new ShopStageController(this);
        this.StartController();
    }

    public void StartController() {
        this.controller.startButtonHandlers();
    }

    public void InitStageElements() {
        this.shopButtons = new ArrayList<>();
        this.shopLabels = new ArrayList<>();

        this.createBackground();
        this.createButtons();
        this.createHeaders();
    }

    public Label getLLabel() {
        return this.shopLabels.get(0);
    }

    public Label getHLabel() {
        return this.shopLabels.get(1);
    }

    public Label getFLabel() {
        return this.shopLabels.get(2);
    }

    public Label getGoldLabel(){
        return this.shopLabels.get(3);
    }

    private void createHeaders() {
        // l count
        String lCount1 = "";
        Label lLabel = new Label();
        lLabel.setLayoutX(122);
        lLabel.setLayoutY(210);
        lLabel.setFont(new Font("Verdana", 30));
        lLabel.setTextFill(Color.web("#01ce58"));
        lLabel.setText(lCount1);
        this.pane.getChildren().add(lLabel);
        this.addLabel(lLabel);

        // h count
        String hCount1 = "";
        Label hLabel = new Label();
        hLabel.setLayoutX(122);
        hLabel.setLayoutY(375);
        hLabel.setFont(new Font("Verdana", 30));
        hLabel.setTextFill(Color.web("#ce1511"));
        hLabel.setText(hCount1);
        this.pane.getChildren().add(hLabel);
        this.addLabel(hLabel);

        // f count
        String fCount1 = "";
        Label fLabel = new Label();
        fLabel.setLayoutX(455);
        fLabel.setLayoutY(210);
        fLabel.setFont(new Font("Verdana", 30));
        fLabel.setTextFill(Color.web("#01cadf"));
        fLabel.setText(fCount1);
        this.pane.getChildren().add(fLabel);
        this.addLabel(fLabel);

        // player gold
        Label goldLabel = new Label();
        String goldCount = "GOLD: 00";
        goldLabel.setLayoutX(550);
        goldLabel.setLayoutY(20);
        goldLabel.setFont(new Font("Verdana", 30));
        goldLabel.setTextFill(Color.web("#dfce58"));
        goldLabel.setText(goldCount);
        this.pane.getChildren().add(goldLabel);
        this.addLabel(goldLabel);
    }

    public void createButtons() {
        this.createShopButtons();
    }

    public GameButton getNextButton() {
        return this.shopButtons.get(0);
    }

    public GameButton getTestGoldButton() {
        return this.shopButtons.get(1);
    }

    public GameButton getBuyLButton() {
        return this.shopButtons.get(2);
    }

    public GameButton getBuyHButton() {
        return this.shopButtons.get(3);
    }

    public GameButton getBuyFButton() {
        return this.shopButtons.get(4);
    }

    public GameButton getSellLButton() {
        return this.shopButtons.get(5);
    }

    public GameButton getSellHButton() {
        return this.shopButtons.get(6);
    }

    public GameButton getSellFButton() {
        return this.shopButtons.get(7);
    }

    public GameButton getCancelButton() {
        return this.shopButtons.get(8);
    }

    private void createShopButtons() {
        String pressed_style = null;
        String free_style = null;
        int FONT_SIZE_VERDANA = 15;
        int FONT_SIZE_CUSTOM = 13;

        // next
        pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/next_button_pressed.png');";
        free_style = "-fx-background-color: transparent; -fx-background-image: url('/next_button_free.png');";
        GameButton nextButton = new GameButton("NEXT", 176, 59, pressed_style, free_style);
        try {
            nextButton.setFont(Font.loadFont(new FileInputStream(nextButton.FONT_PATH), FONT_SIZE_CUSTOM));
        } catch (Exception e) {
			nextButton.setFont(Font.font("Verdana", FONT_SIZE_VERDANA));
        }
        this.addButton(nextButton, 549, 518);

        // test gold
        pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_pressed.png');";
        free_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_free.png');";
        GameButton TestGold = new GameButton("+50 GOLD", 117, 50, pressed_style, free_style);
        try {
            TestGold.setFont(Font.loadFont(new FileInputStream(TestGold.FONT_PATH), FONT_SIZE_CUSTOM));
        } catch (Exception e) {
			TestGold.setFont(Font.font("Verdana", FONT_SIZE_VERDANA));
        }
        this.addButton(TestGold, 62, 100);

        // buy button l
        pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_pressed.png');";
        free_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_free.png');";
        GameButton buyButtonL = new GameButton("buy (-10g)", 117, 50, pressed_style, free_style);
        try {
            buyButtonL.setFont(Font.loadFont(new FileInputStream(buyButtonL.FONT_PATH), FONT_SIZE_CUSTOM));
        } catch (FileNotFoundException e) {
			buyButtonL.setFont(Font.font("Verdana", FONT_SIZE_VERDANA));
        }
        this.addButton(buyButtonL, 250, 243);

        // buy button h

        pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_pressed.png');";
        free_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_free.png');";
        GameButton buyButtonH = new GameButton("buy (-15g)", 117, 50, pressed_style, free_style);
        try {
            buyButtonH.setFont(Font.loadFont(new FileInputStream(buyButtonH.FONT_PATH), FONT_SIZE_CUSTOM));
        } catch (FileNotFoundException e) {
			buyButtonH.setFont(Font.font("Verdana", FONT_SIZE_VERDANA));
        }
        this.addButton(buyButtonH, 250, 405);

        // buy button f

        pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_pressed.png');";
        free_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_free.png');";
        GameButton buyButtonF = new GameButton("buy (-20g)", 117, 50, pressed_style, free_style);
        try {
            buyButtonF.setFont(Font.loadFont(new FileInputStream(buyButtonF.FONT_PATH), FONT_SIZE_CUSTOM));
        } catch (FileNotFoundException e) {
			buyButtonF.setFont(Font.font("Verdana", FONT_SIZE_VERDANA));
        }
        this.addButton(buyButtonF, 585, 243);

        
        // sell button l

        pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_pressed.png');";
        free_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_free.png');";
        GameButton sellButtonL = new GameButton("sell (+10g)", 117, 50, pressed_style, free_style);
        try {
            sellButtonL.setFont(Font.loadFont(new FileInputStream(sellButtonL.FONT_PATH), FONT_SIZE_CUSTOM));
        } catch (FileNotFoundException e) {
			sellButtonL.setFont(Font.font("Verdana", FONT_SIZE_VERDANA));
        }
        this.addButton(sellButtonL, 250, 186);

        // sell button h

        pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_pressed.png');";
        free_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_free.png');";
        GameButton sellButtonH = new GameButton("sell (+15g)", 117, 50, pressed_style, free_style);
        try {
            sellButtonH.setFont(Font.loadFont(new FileInputStream(sellButtonH.FONT_PATH), FONT_SIZE_CUSTOM));
        } catch (FileNotFoundException e) {
			sellButtonH.setFont(Font.font("Verdana", FONT_SIZE_VERDANA));
        }
        this.addButton(sellButtonH, 250, 348);

        // sell button f

        pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_pressed.png');";
        free_style = "-fx-background-color: transparent; -fx-background-image: url('/buy_button_free.png');";
        GameButton sellButtonF = new GameButton("sell (+20g)", 117, 50, pressed_style, free_style);
        try {
            sellButtonF.setFont(Font.loadFont(new FileInputStream(sellButtonF.FONT_PATH), FONT_SIZE_CUSTOM));
        } catch (FileNotFoundException e) {
        	sellButtonF.setFont(Font.font("Verdana", FONT_SIZE_VERDANA));
        }
        this.addButton(sellButtonF, 585, 186);

        // cancel
        pressed_style = "-fx-background-color: transparent; -fx-background-image: url('/next_button_pressed.png');";
        free_style = "-fx-background-color: transparent; -fx-background-image: url('/next_button_free.png');";
        GameButton cancelButton = new GameButton("CANCEL", 176, 59, pressed_style, free_style);
        try {
            cancelButton.setFont(Font.loadFont(new FileInputStream(cancelButton.FONT_PATH), FONT_SIZE_CUSTOM));
        } catch (FileNotFoundException e) {
			cancelButton.setFont(Font.font("Verdana", FONT_SIZE_VERDANA));
        }
        this.addButton(cancelButton, 60, 518);
    }

    private void createBackground() {
        Image backgroundImage = new Image("/shop_bg.png", 800, 600, false, true);
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        this.pane.setBackground(new Background(background));
    }

    private void addButton(GameButton button, int x, int y) {
        button.setLayoutX(x);
        button.setLayoutY(y);
        this.shopButtons.add(button);
        this.pane.getChildren().add(button);
    }

    private void addLabel(Label label) {
        this.shopLabels.add(label);
    }
}