package authoring.display;

import java.util.ArrayList;
import java.util.ResourceBundle;

import authoring.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		root.setCenter(makeGameViewWindow());
		root.setLeft(makeLevelPanel());
		root.setBottom(makeTemplatePanel());
		root.setTop(makeSaveBar());

		return new Scene(root);
	}

	private void initVars() {
	}

	public Node makeLevelPanel() {
		myLevelPanel = new LevelPanel(myResources, myGame, root, myGameViewWindow);
		return myLevelPanel.asNode();
	}

	public Node makeTemplatePanel() {
		myTemplatePanel = new TemplateObjectPanel(myResources, myGame, root);
		return myTemplatePanel.asNode();
	}

	public Node makeObjectPropertyPanel() {
		myPropertyPanel = new PropertyPanel(myResources, myGame, root);
		return myPropertyPanel.asNode();
	}

	public Node makeGameViewWindow() {
		myGameViewWindow = new GameViewWindow(myResources, myGame, root, 20, 20);
		return myGameViewWindow.asNode();
	}
	
	public Node makeSaveBar() {
		mySaveBar = new SaveBar(myResources, myGame, root);
		return mySaveBar.asNode();
	}
}
