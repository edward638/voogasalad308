package GamePlayer;

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

	private final static Paint BACKGROUND = Color.ANTIQUEWHITE;

	private String title;
	private final static double SCREEN_HEIGHT = 300;
	private final static double SCREEN_WIDTH = 500;// 915;
	private Stage myStage;
	private int workSpaceNum = 1;
	private Group root;
	private TabPane tabPane;

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
		root = new Group();
		myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
		setStage();
		tabPane = new TabPane();

	}

	private void setStage() {
		myStage.setScene(myScene);
		myStage.setTitle(title);
		myStage.show();
		myStage.setResizable(false);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
