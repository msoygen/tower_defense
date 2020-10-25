package model;

import javafx.scene.input.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;

public class GameButton extends Button {
	
	public String FONT_PATH = ("/kenvector_future.ttf");
	public InputStream FONT_INPUT_STREAM = this.getClass().getResourceAsStream("kenvector_future.ttf");
	private String BUTTON_PRESSED_STYLE= "-fx-background-color: transparent; -fx-background-image: url('/yellow_button03.png');";
	private String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/yellow_button04.png');";
	private int prefWidth;
	private int prefHeight;
	
	/**
	 * @param String text buton yazısı
	 */
	public GameButton(String text, int prefWidth, int prefHeight, String pressed_style, String free_style) {
		this.prefWidth = prefWidth;
		this.prefHeight = prefHeight;
		this.BUTTON_PRESSED_STYLE = pressed_style;
		this.BUTTON_FREE_STYLE = free_style;

		setText(text);
		setButtonFont();
		setPrefWidth(this.prefWidth);
		setPrefHeight(this.prefHeight);
		setStyle(this.BUTTON_FREE_STYLE);
		initializebuttonListeners();
	}
	
	private void setButtonFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		}catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 23));
		}
	}
	
	private void setButtonPressedStyle() {
		setStyle(this.BUTTON_PRESSED_STYLE);
		setPrefHeight(this.prefHeight - 1);
		setLayoutY(getLayoutY() + 4);
	}
	
	private void setButtonReleasedStyle() {
		setStyle(this.BUTTON_FREE_STYLE);
		setPrefHeight(this.prefHeight);
		setLayoutY(getLayoutY() - 4);
	}
	
	private void initializebuttonListeners() {
		
		setOnMousePressed(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				if(event.getButton().equals(MouseButton.PRIMARY))
				{
					setButtonPressedStyle();
				}
			}
		
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				setEffect(null);
			}
	});
}
}
	

