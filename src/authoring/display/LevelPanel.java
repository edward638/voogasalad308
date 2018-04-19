package authoring.display;

import java.io.File;
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
public class LevelPanel extends AuthoringUIComponent {

	private VBox myVBox;
	private HBox myHBox;
	private ComboBox<GameScene> myLevelDropdown;
	private ComboBox<String> myPanelSelectorComboBox; 
	private Button myAddLevelButton;
	private Button myAddGameObjectButton;
	private Button myAddSceneBackgroundImageButton;
	private ListView<GameObject> myLevelObjects;
	
	private GameViewWindow myGameViewWindow;

	public LevelPanel(ResourceBundle resources, Game game, Node root, GameViewWindow gameViewWindow) {
		super(resources, game, root); //pass resources to super constructor
		myGameViewWindow = gameViewWindow;
		System.out.println(myGameViewWindow == null);

		myVBox = new VBox();
		myHBox = new HBox();
		myHBox.getChildren().addAll(makeAddGameObjectButton(), makeAddSceneBackgroundImageButton());
		myVBox.getChildren().addAll(makeLevelChooser(), makeObjectList(), myHBox);
	}

	public VBox asVBox() {
		return myVBox;
	}

	private HBox makeLevelChooser() {
		HBox levelChooser = new HBox();
		makeLevelDropdown();
		makeAddLevelButton();
		makePanelSelectorComboBox();
		levelChooser.getChildren().addAll(myAddLevelButton, myLevelDropdown, myPanelSelectorComboBox); //TODO: maybe move myPanelSelectorComboBox 
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
	
	private Button makeAddSceneBackgroundImageButton() {
		myAddSceneBackgroundImageButton = makeButton("AddSceneBackgroundImageButton", event -> {
			try {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose Object Image");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				File image = fileChooser.showOpenDialog(new Stage());
				getGame().getSceneManager().getCurrentScene().getSceneBackground().addImage(new Image(image.toURI().toString()));
				//put image.getName into SceneBackground
			} catch (Exception e) {
				//do nothing
				//this just means the user didn't choose an image
				//which is a perfectly fine thing for them to do
			}
		});
				
		return myAddSceneBackgroundImageButton;
	}

	private ComboBox<GameScene> makeLevelDropdown() {
		myLevelDropdown = new ComboBox<>();
		myLevelDropdown.setPromptText(super.getResources().getString("SelectSceneDropDown")); //make super.getString method?
		//below line contains dummy objects
		myLevelDropdown.getItems().addAll(getGame().getSceneManager().getScenes());
		myLevelDropdown.valueProperty().addListener((o, old, neww) -> {
			getGame().getSceneManager().setCurrentScene(neww);
			myLevelObjects.setItems(FXCollections.observableArrayList(getGame().getSceneManager().getCurrentScene().getMyObjects()));
			
			myGameViewWindow.updateWindow();
			System.out.println("Level Panel tried to call updateWindow");
		});
		return myLevelDropdown;
	}
	
	private ComboBox<String> makePanelSelectorComboBox(){
		myPanelSelectorComboBox = new ComboBox<>();
		myPanelSelectorComboBox.setPromptText(super.getResources().getString("ChoosePanel"));
		myPanelSelectorComboBox.getItems().add("Background");
		myPanelSelectorComboBox.getItems().add("Foreground");
		myPanelSelectorComboBox.valueProperty().addListener((o, old, neww) -> {
			myGameViewWindow.switchPanes(neww);
		});
		return myPanelSelectorComboBox; 
		
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
