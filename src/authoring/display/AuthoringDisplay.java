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
	private Node myLevelPanel;
	private GameViewWindow myGameViewWindow;
	private Game myGame;
	private TemplateObjectPanel templatePanel;

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
//		root.setLeft(makeLevelPanel());
		root.setLeft(myLevelPanel);
//		root.setRight(templatePanel.asPane());
		root.setRight(makeTemplateObjectPanel());
		root.setTop(makeSaveBar());
//		root.setCenter(makeGameVisWindow());
		root.setCenter(myGameViewWindow.asPane());

		return new Scene(root);
	}

	private void initVars() {
		myGameViewWindow = makeGameVisWindow();
		System.out.println(myGameViewWindow == null);
		myLevelPanel = makeLevelPanel();
	}

	public Node makeLevelPanel() {
		LevelPanel levelPanel = new LevelPanel(myResources, myGame, root, myGameViewWindow);
		return levelPanel.asVBox();
	}

	public Node makeTemplateObjectPanel() {
		templatePanel = new TemplateObjectPanel(myResources, myGame, root);
		return templatePanel.asPane();
	}

	public Node makeObjectPropertyPanel() {
		PropertyPanel propertyPanel = new PropertyPanel(myResources, myGame, root);
		return propertyPanel.asScrollPane();
	}

	public GameViewWindow makeGameVisWindow() {
//		GameViewWindow gameViewWindow = new GameViewWindow(myResources, myGame, root);
		myGameViewWindow = new GameViewWindow(myResources, myGame, root, 1000, 1000);
		return myGameViewWindow;
//		return myGameViewWindow.asPane();
//		return new Pane();
	}
	
	public Node makeSaveBar() {
		SaveBar saveBar = new SaveBar(myResources, myGame, root);
		return saveBar.asHBox();
	}
}
