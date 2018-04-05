package authoring.display;

import javafx.application.Application;
import javafx.stage.Stage;

public class RunAuthoring extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		new AuthoringDisplay(primaryStage);
	}
	
	public static void main(String[] args){
		launch();
	}

}
