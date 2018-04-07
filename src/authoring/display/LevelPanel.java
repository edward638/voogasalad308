package authoring.display;

import java.util.ResourceBundle;

import authoring.GameScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LevelPanel extends AuthoringUIComponent {
	
	private VBox myVBox;

	public LevelPanel(ResourceBundle resources) {
		super(resources); //pass resources to super constructor
		myVBox = new VBox();
		HBox levelChooser = new HBox();
		levelChooser.getChildren().add(makeButton("AddSceneButton", event -> doNothing()));
		
		ComboBox<String> levels = new ComboBox<>();
		levels.setPromptText(super.getResources().getString("SelectSceneDropDown")); //make super.getString method?
		//below line contains dummy objects
		levels.getItems().addAll("hi", "another level");
		levelChooser.getChildren().add(levels);
	

		//this will take GameScene selected from above ComboBox, go to ObjectManager, plug the selected GameScene into the placedObjects Map, 
		//then display the corresponding List<GameObject> in the ListView below
		//below line contains dummy objects
		ObservableList<String> items = FXCollections.observableArrayList ("dummy object 1", "Mario", "Goomba");
		ListView<String> list = new ListView<String>();
		list.setItems(items);
		
		myVBox.getChildren().addAll(levelChooser, list);
	}

	public VBox asVBox() {
		return myVBox;
	}
}
