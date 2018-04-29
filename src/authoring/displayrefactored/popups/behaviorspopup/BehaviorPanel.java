package authoring.displayrefactored.popups.behaviorspopup;

import java.util.List;
import java.util.Set;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.displayrefactored.controllers.BehaviorPopupController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class BehaviorPanel {
	private List<GameObject> myGameObjects;
	private Set<AuthBehavior> myBehaviors;
	private Set<AuthBehavior> notMyBehaviors;
	
	private ListView<AuthBehavior> myBehaviorList;
	private ComboBox<AuthBehavior> notMyBehaviorList;

	private VBox myVBox;
	
	public BehaviorPanel(BehaviorPopupController behaviorPopupController, List<GameObject> gameObjects) {
		myGameObjects = gameObjects;
		myBehaviors = myGameObjects.get(0).getBehaviors();
		myVBox = new VBox();
		initialize();
	}
	
	private void initialize() {
		myVBox.setPadding(new Insets(10));
		myVBox.setSpacing(10);
		myVBox.setMinWidth(200);
		myVBox.getChildren().add(new Label("Behavior"));
	}
	
	public void refresh() {
		myVBox.getChildren().clear();
		initialize();
	}

	public Node asNode() {
		return myVBox;
	}

	public AuthBehavior getCurrBehavior() {
		return null;
	}	
}
