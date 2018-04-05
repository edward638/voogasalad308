package display;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author August Ning
 * Main driver class that is used for choosing between game authoring and game player
 */
public class Main extends Application {
	private SplashScreen splash; 

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		splash = new SplashScreen(stage);
		stage.setTitle("VOOGA");
		stage.setScene(splash.getSplashScreen());
		stage.setResizable(false);
		stage.show();
	}

}
