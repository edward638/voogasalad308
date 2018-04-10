package authoring.display;

import java.util.ResourceBundle;

import authoring.Game;
import authoring.GameScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LevelPanel extends AuthoringUIComponent {
	
	private VBox myVBox;

	public LevelPanel(ResourceBundle resources, Game game) {
		super(resources, game); //pass resources to super constructor
		myVBox = new VBox();
		myVBox.getChildren().addAll(makeLevelChooser(), makeObjectList());
	}

	public VBox asVBox() {
		return myVBox;
	}
	
	private HBox makeLevelChooser() {
		HBox levelChooser = new HBox();
		levelChooser.getChildren().addAll(makeAddLevelButton(), makeLevelDropdown());
		return levelChooser;
	}
	
	private Button makeAddLevelButton() {
		return makeButton("AddSceneButton", event -> doNothing());
	}
	
	private ComboBox<String> makeLevelDropdown() {
		ComboBox<String> levelDropdown = new ComboBox<>();
		levelDropdown.setPromptText(super.getResources().getString("SelectSceneDropDown")); //make super.getString method?
		//below line contains dummy objects
		levelDropdown.getItems().addAll("hi", "another level");
		return levelDropdown;
	}
	
	//you can make this a ListView of GameObjects/GameElements and make a toString method so that it displays properly
	private ListView<String> makeObjectList() {
		//this will take GameScene selected from above ComboBox, go to ObjectManager, plug the selected GameScene into the placedObjects Map, 
		//then display the corresponding List<GameObject> in the ListView below
		//below line contains dummy objects
		ObservableList<String> items = FXCollections.observableArrayList ("dummy object 1", "Mario", "Goomba");
		ListView<String> list = new ListView<String>();
		list.setItems(items);
		return list;
	}
	
}
