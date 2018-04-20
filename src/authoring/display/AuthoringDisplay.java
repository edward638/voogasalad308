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
	private ObjectInfoPanel myObjectInfoPanel;
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
		root.setCenter(myObjectInfoPanel.asNode());
//		root.setBottom(myTemplatePanel.asNode());
		root.setTop(mySaveBar.asNode());

		return new Scene(root);
	}

	private void initVars() {
		myGameViewWindow = makeGameViewWindow();
		myObjectInfoPanel = makeObjectPropertyPanel();
		myLevelPanel = makeLevelPanel(myGameViewWindow, myObjectInfoPanel);
		myTemplatePanel = makeTemplatePanel();
		mySaveBar = makeSaveBar();
	}

	private LevelPanel makeLevelPanel(GameViewWindow gameViewWindow, ObjectInfoPanel objectInfoPanel) {
		LevelPanel levelPanel = new LevelPanel(myResources, myGame, root, gameViewWindow, objectInfoPanel);
		return levelPanel;
	}

	private TemplateObjectPanel makeTemplatePanel() {
		TemplateObjectPanel templatePanel = new TemplateObjectPanel(myResources, myGame, root);
		return templatePanel;
	}

	private ObjectInfoPanel makeObjectPropertyPanel() {
		ObjectInfoPanel objectInfoPanel = new ObjectInfoPanel(myResources, myGame, root);
		return objectInfoPanel;
	}

	private GameViewWindow makeGameViewWindow() {
		GameViewWindow gameViewWindow = new GameViewWindow(myResources, myGame, root, 600, 600);
		return gameViewWindow;
	}

	private SaveBar makeSaveBar() {
		SaveBar saveBar = new SaveBar(myResources, myGame, root);
		return saveBar;
	}
}
