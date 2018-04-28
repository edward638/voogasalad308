package authoring.displayrefactored.popups.behaviorspopup;

import java.util.List;
import java.util.Set;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.displayrefactored.controllers.BehaviorPopupController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PropertyPanel {

	private List<GameObject> myGameObjects;
	private Set<AuthBehavior> myBehaviors;
	private VBox myVBox;
	private BehaviorPopupController myController;

	public PropertyPanel(BehaviorPopupController behaviorPopupController, List<GameObject> gameObjects) {
		myGameObjects = gameObjects;
		myBehaviors = myGameObjects.get(0).getBehaviors();
		myController = behaviorPopupController;
		initialize();
	}
	
	private void initialize() {
		myVBox = new VBox();
		initializeRoot();
	}

	private void initializeRoot() {
		myVBox.setPadding(new Insets(10));
		myVBox.setSpacing(10);
		myVBox.setMinWidth(200);
		myVBox.getChildren().add(new Label("Properties"));
	}

	public Node asNode() {
		return myVBox;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
