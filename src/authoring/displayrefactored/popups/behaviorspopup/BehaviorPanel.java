package authoring.displayrefactored.popups.behaviorspopup;

import java.util.List;
import java.util.Set;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.displayrefactored.controllers.BehaviorPopupController;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
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
	}

	public Node asNode() {
		return myVBox;
	}

	public AuthBehavior getCurrBehavior() {
		// TODO Auto-generated method stub
		return null;
	}	
}
