package authoring.display;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Set;

import authoring.Behavior;
import authoring.Game;
import authoring.GameObject;
import authoring.GameScene;
import authoring.Property;
import data.GameObjectManager;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Maddie Wilkinson
 *
 */
public class NewGameObjectWindow extends PopupWindow {

	private ListView<GameObject> myLevelObjects;
	private GameViewWindow myGameViewWindow;

	public NewGameObjectWindow(ResourceBundle resources, Game game, Node root, ListView<GameObject> levelObjects, GameViewWindow gameViewWindow) {
		super(resources, game, root);
		myLevelObjects = levelObjects;
		myGameViewWindow = gameViewWindow;
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

		HBox imageInfo = new HBox();
		TextField imageText = new TextField();
		
		Button chooseImageButton = makeButton("ChooseImageButton", event -> 
		{	
			if(!imageText.getText().isEmpty()) {
				try {
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Choose Object Image");
					fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
					File image = fileChooser.showOpenDialog(new Stage());
					getGame().getImageManager().storeImage(imageText.getText(), new Image(image.toURI().toString()));
					//put image.getName into a property of the GameObject
				} catch (Exception e) {
					//do nothing
					//this just means the user didn't choose an image
					//which is a perfectly fine thing for them to do
				}
			}});
		
		imageInfo.getChildren().addAll(new Label("Image name: "), imageText, chooseImageButton);

		Button saveButton = makeButton("Save", event -> {
			saveGameObject(nameText, xText, yText, imageText);
		});

		root.getChildren().addAll(nameObject, xPosValues, yPosValues, imageInfo, saveButton);
		return new Scene(root);
	}

	private void saveGameObject(TextField nameText, TextField xText, TextField yText, TextField imageText) {
		if(!nameText.getText().isEmpty() && !xText.getText().isEmpty() && !yText.getText().isEmpty()) {
			try {
				String objectName = nameText.getText();
				Double xPos = Double.parseDouble(xText.getText());
				Double yPos = Double.parseDouble(yText.getText());
				String imageName = imageText.getText();

				GameObject go = makeGameObject(objectName, xPos, yPos, imageName);
				getGame().getSceneManager().getCurrentScene().getMyObjects().add(go);
				myLevelObjects.getItems().add(0, go);
				System.out.println(myGameViewWindow == null);
				myGameViewWindow.updateWindow();
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

	private GameObject makeGameObject(String name, Double xPos, Double yPos, String imageName) {
		GameObject newObject = new GameObject("MandatoryBehavior");
		newObject.setName(name);
		newObject.getBehavior("MandatoryBehavior").getProperty("elementName").setValue(name);
		newObject.getBehavior("MandatoryBehavior").getProperty("xPos").setValue(xPos);
		newObject.getBehavior("MandatoryBehavior").getProperty("yPos").setValue(yPos);
		newObject.getBehavior("MandatoryBehavior").getProperty("imagePath").setValue(imageName);

		/**
		 * This is only temporary!! to see if we can add game objects to the template.
		 */
		GameObjectManager manager = new GameObjectManager();
		try {
			manager.saveCustomGameObject(newObject, imageName);
		} catch (IOException e) {
			System.out.println("rip");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newObject;
	}
}
