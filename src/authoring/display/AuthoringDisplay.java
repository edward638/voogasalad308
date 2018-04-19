package authoring.display;

import java.util.ResourceBundle;
import authoring.Game;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Maddie Wilkinson
 *
 */
public class AuthoringDisplay {
	public static final String DEFAULT_RESOURCE_PATH = "authoring.display.resources/";
	public static final String DEFAULT_CSS_PATH = "authoring/display/resources/";

	public static final String DEFAULT_LANGUAGE = "English";
	public static final String DEFAULT_STYLE = "myStyle.css";

	private BorderPane root;
	private ResourceBundle myResources; //rename more accurately; it's the button names & stuff specifically
	private Game myGame;

	private LevelPanel myLevelPanel;
	private GameViewWindow myGameViewWindow;
	private PropertyPanel myPropertyPanel;
	private TemplateObjectPanel myTemplatePanel;
	private SaveBar mySaveBar;

	public AuthoringDisplay(Stage stage, Game game) {
		myGame = new Game();
		loadResources();
		initialize(stage);
	}

	public void loadResources() {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + DEFAULT_LANGUAGE);
	}

	public void initialize(Stage stage) {
		Scene newScene = setUpScene();
//		newScene.getStylesheets().add(DEFAULT_CSS_PATH + DEFAULT_STYLE);
		stage.setScene(newScene);
		stage.show();
	}


	public Scene setUpScene() {
		initVars();
		root = new BorderPane();
		root.setRight(myGameViewWindow.asNode());
		root.setLeft(myLevelPanel.asNode());
		root.setCenter(myPropertyPanel.asNode());
		root.setBottom(myTemplatePanel.asNode());
		root.setTop(mySaveBar.asNode());

		return new Scene(root);
	}

	private void initVars() {
		makeGameViewWindow();
		makeObjectPropertyPanel();
		// dependency: GameViewWindow and PropertyPanel must be created before LevelPanel for it to work
		// is this removable? the problem is selecting things in the LevelPanel needs to be able to alter things in the other areas
		makeLevelPanel();
		makeTemplatePanel();
		makeSaveBar();
	}

	private Node makeLevelPanel() {
		myLevelPanel = new LevelPanel(myResources, myGame, root, myGameViewWindow, myPropertyPanel);
		return myLevelPanel.asNode();
	}

	private Node makeTemplatePanel() {
		myTemplatePanel = new TemplateObjectPanel(myResources, myGame, root);
		return myTemplatePanel.asNode();
	}

	private Node makeObjectPropertyPanel() {
		myPropertyPanel = new PropertyPanel(myResources, myGame, root);
		return myPropertyPanel.asNode();
	}

	private Node makeGameViewWindow() {
		myGameViewWindow = new GameViewWindow(myResources, myGame, root, 600, 600);
		return myGameViewWindow.asNode();
	}

	private Node makeSaveBar() {
		mySaveBar = new SaveBar(myResources, myGame, root);
		return mySaveBar.asNode();
	}
}
