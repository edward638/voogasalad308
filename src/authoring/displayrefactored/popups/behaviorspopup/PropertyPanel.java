package authoring.displayrefactored.popups.behaviorspopup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.Property;
import authoring.displayrefactored.controllers.BehaviorPopupController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PropertyPanel {

	private List<GameObject> myGameObjects;
	//	private Set<Property> myProperties;
	private VBox myVBox;
	//	private List<TextField> myTextFields;
	private Map<Property, TextField> myPropTexts;
	private BehaviorPopupController myController;
	private AuthBehavior currBehavior;

	public PropertyPanel(BehaviorPopupController behaviorPopupController, List<GameObject> gameObjects) {
		myGameObjects = gameObjects;
		myController = behaviorPopupController;
		myPropTexts = new HashMap<>();
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
		myVBox.getChildren().add(makeSaveButton());
		currBehavior = myController.getCurrBehavior();
		if(currBehavior != null) {
			for(Property property : myController.getCurrBehavior().getProperties()) {
				System.out.println(property.getValueType().getName());
				myVBox.getChildren().add(makePropertyFields(property));
			}
		}
	}

	private Node makePropertyFields(Property property) {
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10));
		hBox.getChildren().add(new Label(property.getName()));
		TextField propField = null;
		if(myPropTexts.containsKey(property)) {
			propField = myPropTexts.get(property);
			propField.setText(property.getValue().toString());
		} else {
			propField = new TextField();
			myPropTexts.put(property, propField);
		}
		hBox.getChildren().add(propField);
		return hBox;
	}

	private Button makeSaveButton() {
		Button saveButton = new Button();
		saveButton.setText("Save");
		saveButton.setOnAction(e -> saveProperties());
		return saveButton;
	}

	private void saveProperties() {
		for(Property p : myPropTexts.keySet()) {
			for(GameObject g : myGameObjects) {
				Property prop = g.getBehavior(currBehavior.getName()).getProperty(p.getName());
				String value = myPropTexts.get(p).getText();
				if (value != null && prop != null) {
					switch(prop.getValueType().getSimpleName()) {
					case "Double":
						System.out.println("it's a double!");
						prop.setValue(Double.parseDouble(value));
						break;
					case "String":
						System.out.println("it's a string!");
						prop.setValue(value);
						break;
					case "Integer":
						System.out.println("it's a integer!");
						prop.setValue(Integer.parseInt(value));
						break;
					case "Boolean":
						System.out.println("it's a boolean!");
						prop.setValue(Boolean.parseBoolean(value));
						break;
					default: 
						System.out.println("ripperoni");
					}
					System.out.println("property value set");
				}
			}
		}
	}

	public void refresh() {
		myVBox.getChildren().clear();
		initializeRoot();
	}

	public Node asNode() {
		return myVBox;
	}
}
