package authoring.display;

import java.util.ResourceBundle;
import java.util.Set;

import authoring.Behavior;
import authoring.Game;
import authoring.GameObject;
import authoring.GameScene;
import authoring.Property;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Maddie Wilkinson
 *
 */
public class NewGameObjectWindow extends NewComponentWindow {

	private ListView<GameObject> myLevelObjects;

	
	public NewGameObjectWindow(ResourceBundle resources, Game game, Node root, ListView<GameObject> levelObjects) {
		super(resources, game, root);
		myLevelObjects = levelObjects;
		setStage(setUpScene());
	}

	@Override
	protected Scene setUpScene() {
		VBox root = new VBox();

		HBox nameObject = new HBox();
		TextField nameText = new TextField();
		nameObject.getChildren().addAll(new Label("Name: "), nameText);

		HBox xPosValues = new HBox();
		TextField xText = new TextField();
		xPosValues.getChildren().addAll(new Label("X Position: "), xText);
		
		HBox yPosValues = new HBox();
		TextField yText = new TextField();
		yPosValues.getChildren().addAll(new Label("Y Position: "), yText);

		Button closeButton = makeButton("Save", event -> {
			saveGameObject(nameText, xText, yText);
		});

		root.getChildren().addAll(nameObject, xPosValues, yPosValues, closeButton);
		return new Scene(root);
	}

	private void saveGameObject(TextField nameText, TextField xText, TextField yText) {
		if(!nameText.getText().isEmpty() && !xText.getText().isEmpty() && !yText.getText().isEmpty()) {
			try {
				String objectName = nameText.getText();
				Double xPos = Double.parseDouble(xText.getText());
				Double yPos = Double.parseDouble(yText.getText());
				GameObject go = makeGameObject(objectName, xPos, yPos);
				myLevelObjects.getItems().add(0, go);
				getStage().close();
				//after slider is implemented, only catch general exception
			} catch(NumberFormatException e) {
				System.out.println("Invalid index input numFormat");
				new Error("Invalid index input"); //this isn't being displayed yet
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Invalid index input OOB");
				//this isn't being displayed yet 
				new Error("Invalid index input"); //eventually to fix this you can get the size of the array and make a slider so they can choose where to put it
			}	
		} else {
			getStage().close();
		}
	}
	
	private GameObject makeGameObject(String name, Double xPos, Double yPos) {
		GameObject newObject = new GameObject("MandatoryBehavior");
		newObject.setName(name);
		newObject.getBehavior("MandatoryBehavior").getProperty("elementName").setValue(name);
		newObject.getBehavior("MandatoryBehavior").getProperty("xPos").setValue(xPos);
		newObject.getBehavior("MandatoryBehavior").getProperty("yPos").setValue(yPos);
		return newObject;
	}
	
}
