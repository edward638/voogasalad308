package authoring.displayrefactored.popups.behaviorspopup;

import java.util.List;
import java.util.Set;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.Property;
import authoring.displayrefactored.controllers.BehaviorPopupController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
		myVBox.setMinWidth(450);
		myVBox.setMinHeight(450);
		myVBox.getChildren().add(new Label("Properties"));
		AuthBehavior currBehavior = myController.getCurrBehavior();
		if(currBehavior != null) {
			for(Property property : myController.getCurrBehavior().getProperties()) {
				System.out.println(property.getValueType().getName());
				myVBox.getChildren().add(makePropertyFields(property));
			}
		}
	}
	
	private Node makePropertyFields(Property property) {
		HBox hBox = new HBox();
		hBox.getChildren().add(new Label(property.getName()));
		TextField propField = new TextField();
		hBox.getChildren().add(propField);
		return hBox;
	}

	public void refresh() {
		myVBox.getChildren().clear();
		initializeRoot();
	}
	
	public Node asNode() {
		return myVBox;
	}
}
