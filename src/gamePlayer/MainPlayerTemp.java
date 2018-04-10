package gamePlayer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Main class where Tabs are created
 * 
 * @author Calvin Ma
 *
 */
public class MainPlayerTemp extends Application {


	private Stage myStage;

	// private static final Map<String, double[]> GUIProperties = createMap();

	// Additional setup for the main menu
	private Scene myScene;

	/**
	 * initalizes the stage
	 */
	@Override
	public void start(Stage stage) {
		myStage = stage;
		initialize();
	}

	/**
	 * initializes the group and scene. Creates a tabpane which is used for multiple
	 * workspaces
	 */
	private void initialize() {

		GamePlayer gamePlayer = new ConcreteGamePlayer(myStage);

		setStage();

	}

	private void setStage() {
		myStage.show();
		myStage.setResizable(false);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
