package authoring.display;

import java.util.ResourceBundle;

import authoring.Game;
import authoring.GameObject;
import authoring.GameScene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LevelPanel extends AuthoringUIComponent {

	private VBox myVBox;
	private ComboBox<GameScene> myLevelDropdown;
	private Button myAddLevelButton;
	private Button myAddGameObjectButton;
	private ListView<GameObject> myLevelObjects;

	public LevelPanel(ResourceBundle resources, Game game, Node root) {
		super(resources, game, root); //pass resources to super constructor

		myVBox = new VBox();
		myVBox.getChildren().addAll(makeLevelChooser(), makeObjectList(), makeAddGameObjectButton());
	}

	public VBox asVBox() {
		return myVBox;
	}

	private HBox makeLevelChooser() {
		HBox levelChooser = new HBox();
		makeLevelDropdown();
		makeAddLevelButton();
		
		levelChooser.getChildren().addAll(myAddLevelButton, myLevelDropdown);
		return levelChooser;
	}

	private Button makeAddLevelButton() {
		myAddLevelButton =  makeButton("AddSceneButton", event -> { 
			new NewLevelWindow(getResources(), getGame(), getRoot(), myLevelDropdown);
		});
		return myAddLevelButton;
	}
	
	private Button makeAddGameObjectButton() {
		myAddGameObjectButton =  makeButton("AddGameObjectButton", event -> { 
			new NewLevelWindow(getResources(), getGame(), getRoot(), myLevelDropdown);
		});
		return myAddGameObjectButton;
	}

	private ComboBox<GameScene> makeLevelDropdown() {
		myLevelDropdown = new ComboBox<>();
		myLevelDropdown.setPromptText(super.getResources().getString("SelectSceneDropDown")); //make super.getString method?
		//below line contains dummy objects
		myLevelDropdown.getItems().addAll(getGame().getSceneManager().getScenes());
		myLevelDropdown.valueProperty().addListener((o, old, neww) -> {
			getGame().getSceneManager().setCurrentScene(neww);
			myLevelObjects.setItems(FXCollections.observableArrayList(getGame().getSceneManager().getCurrentScene().getMyObjects()));
		});
		return myLevelDropdown;
	}

	//you can make this a ListView of GameObjects/GameElements and make a toString method so that it displays properly
	private ListView<GameObject> makeObjectList() {
		//this will take GameScene selected from above ComboBox, go to ObjectManager, plug the selected GameScene into the placedObjects Map, 
		//then display the corresponding List<GameObject> in the ListView below
		//below line contains dummy objects
		//		ObservableList<String> items = FXCollections.observableArrayList ("dummy object 1", "Mario", "Goomba");
		myLevelObjects = new ListView<GameObject>();
		myLevelObjects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GameObject>() {
		    @Override
		    public void changed(ObservableValue<? extends GameObject> observable, GameObject oldValue, GameObject newValue) {
		       
		    }
		});
		
		return myLevelObjects;
	}

}
