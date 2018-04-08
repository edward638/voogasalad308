package authoring.display;

import java.util.ArrayList;
import java.util.ResourceBundle;

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

public class AuthoringDisplay {
	public static final String DEFAULT_RESOURCE_PATH = "authoring.display.resources/";
	public static final String DEFAULT_CSS_PATH = "authoring/display/resources/"; //rename?

	public static final String DEFAULT_LANGUAGE = "English";
	public static final String DEFAULT_STYLE = "myStyle.css";


	private BorderPane root;
	private ResourceBundle myResources; //rename more accurately

	public AuthoringDisplay(Stage stage) {
		loadResources();
		initialize(stage);
	}

	public void loadResources() {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + DEFAULT_LANGUAGE);
	}

	public void initialize(Stage stage) {
		Scene newScene = setUpScene();
//		newScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE1 + DEFAULT_STYLE);
		stage.setScene(newScene);
		stage.show();
	}


	public Scene setUpScene() {
		root = new BorderPane();
		root.setLeft(makeLevelPanel());
		root.setRight(makeObjectPropertyPanel());
		root.setBottom(makeTemplateObjectPanel());
		root.setTop(makeSaveBar());
		root.setCenter(makeGameVisPane());
		return new Scene(root);
	}

	public Node makeLevelPanel() {
		LevelPanel levelPanel = new LevelPanel(myResources);
		return levelPanel.asVBox();
	}

	public Node makeTemplateObjectPanel() {
		return new FlowPane();
	}

	public Node makeObjectPropertyPanel() {
		PropertyPanel propertyPanel = new PropertyPanel(myResources);
		return propertyPanel.asScrollPane();
	}

	public Node makeGameVisPane() {
		return new Pane();
	}
	
	public Node makeSaveBar() {
		SaveBar saveBar = new SaveBar(myResources);
		return saveBar.asHBox();
	}
}
