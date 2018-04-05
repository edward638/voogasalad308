package authoring.display;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AuthoringDisplay {
	public static final String DEFAULT_RESOURCE_PACKAGE = "authoring.display.resources/";
	public static final String DEFAULT_LANGUAGE = "English";

	private BorderPane root;
	private ResourceBundle myResources; //rename more accurately

	public AuthoringDisplay(Stage stage) {
		initialize(stage);
	}

	public void loadResources() {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGE);
	}

	public void initialize(Stage stage) {
		Scene newScene = setUpScene();
		stage.setScene(newScene);
		stage.show();
	}


	public Scene setUpScene() {
		root = new BorderPane();
		root.setLeft(makePlacedObjectPanel());
		root.setRight(makeObjectPropertyPanel());
		root.setBottom(makeTemplateObjectPanel());
		root.setCenter(makeGameVisPane());
		return new Scene(root);
	}

	public Pane makePlacedObjectPanel() {
		Pane placedPanel = new Pane();
		ObservableList<String> items = FXCollections.observableArrayList (
				"Single", "Double", "Suite", "Family App");
		ListView<String> list = new ListView<String>();
		list.setItems(items);
		placedPanel.getChildren().add(list);
		return placedPanel;
	}

	public Node makeTemplateObjectPanel() {
		return new Pane();
	}

	public Node makeObjectPropertyPanel() {
		return new Pane();
	}

	public Node makeGameVisPane() {
		return new Pane();
	}


}
