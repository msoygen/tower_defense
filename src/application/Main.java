package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainStage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			
			MainStage mainStage = new MainStage();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
