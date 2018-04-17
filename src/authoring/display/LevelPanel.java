package authoring.display;

import java.util.ResourceBundle;

import authoring.Game;
import authoring.GameObject;
import authoring.GameScene;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Maddie Wilkinson
 *
 */
public class LevelPanel extends MainWindowComponent {

	private VBox myVBox;
	private ComboBox<GameScene> myLevelDropdown;
	private Button myAddLevelButton;
	private Button myAddGameObjectButton;
	private ListView<GameObject> myLevelObjects;
	
	private GameViewWindow myGameViewWindow;

	public LevelPanel(ResourceBundle resources, Game game, Node root, GameViewWindow gameViewWindow) {
		super(resources, game, root); //pass resources to super constructor
		myGameViewWindow = gameViewWindow;
		System.out.println(myGameViewWindow == null);

		myVBox = new VBox();
		myVBox.getChildren().addAll(makeLevelChooser(), makeObjectList(), makeAddGameObjectButton());
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
			new NewGameObjectWindow(getResources(), getGame(), getRoot(), myLevelObjects, myGameViewWindow);
		});
		return myAddGameObjectButton;
	}

	private ComboBox<GameScene> makeLevelDropdown() {
		myLevelDropdown = new ComboBox<>();
		myLevelDropdown.setPromptText(super.getResources().getString("SelectSceneDropDown")); //make super.getString method?
		myLevelDropdown.getItems().addAll(getGame().getSceneManager().getScenes());
		myLevelDropdown.valueProperty().addListener((o, old, neww) -> {
			getGame().getSceneManager().setCurrentScene(neww);
			myLevelObjects.setItems(FXCollections.observableArrayList(getGame().getSceneManager().getCurrentScene().getMyObjects()));
			
			myGameViewWindow.updateWindow();
			System.out.println("Level Panel tried to call updateWindow");
		});
		return myLevelDropdown;
	}

	private ListView<GameObject> makeObjectList() {
		myLevelObjects = new ListView<GameObject>();
		myLevelObjects.getSelectionModel().selectedItemProperty().addListener(
				(o, old, neww) -> getGame().getSceneManager().getCurrentScene().setCurrentGameObject(neww));
		return myLevelObjects;
	}

	public VBox asVBox() {
		return myVBox;
	}
	
	@Override
	protected Node asNode() {
		return myVBox;
	}

}
