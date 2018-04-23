package authoring.tests;

import authoring.displayrefactored.AuthoringDisplayRefactored;
import javafx.application.Application;
import javafx.stage.Stage;

public class RefactorTest extends Application {
	
	AuthoringDisplayRefactored authoringDisplayRefactored;
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		authoringDisplayRefactored = new AuthoringDisplayRefactored(primaryStage);
		primaryStage.show();
	}
}
