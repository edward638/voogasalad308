package authoring.displayrefactored.authoringuicomponents;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import authoring.GameObject;
import authoring.GameScene;
import authoring.displayrefactored.controllers.LevelPanelController;
import data.propertiesFiles.ResourceBundleManager;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class LevelPanelRefactored extends AuthoringUIComponentRefactored implements Observer{

	private VBox myVBox;
	private HBox myHBox;
	private HBox levelChooser;
	private ComboBox<GameScene> myLevelDropdown;
	private ComboBox<String> myPanelSelector; 
	private Button myAddLevelButton;
	private Button myAddGameObjectButton;
	private Button myAddSceneBackgroundImageButton;
	private ListView<GameObject> myLevelObjects;
	private LevelPanelController controller;
	
	public LevelPanelRefactored(LevelPanelController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		
	}
	
	private void initializeButtons() {
		myAddLevelButton = new Button(ResourceBundleManager.getAuthoring("AddSceneButton"));
		myAddGameObjectButton = new Button(ResourceBundleManager.getAuthoring("AddGameObjectButton"));
		myAddSceneBackgroundImageButton = new Button(ResourceBundleManager.getAuthoring("AddSceneBackgroundImageButton"));
	}
	
	private void initializeComboBoxes() {
		myLevelDropdown = new ComboBox<>();
		myLevelDropdown.setPromptText(ResourceBundleManager.getAuthoring("SelectSceneDropDown"));
		myPanelSelector = new ComboBox<>();
		myPanelSelector.setPromptText(ResourceBundleManager.getAuthoring("ChoosePanel"));
	}
	
	private void initializeListViews() {
		myLevelObjects = new ListView<>();
	}
	
	private void setActions() {
		myAddLevelButton.setOnAction(e -> {
			
		});
		myAddGameObjectButton.setOnAction(e -> {
			
		});
		myAddSceneBackgroundImageButton.setOnAction(e -> {
			try {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose Object Image");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				File image = fileChooser.showOpenDialog(new Stage());
				controller.addBackgroundImage(new Image(image.toURI().toString()));
				//put image.getName into SceneBackground
			} catch (Exception exception) {
				//do nothing
				//this just means the user didn't choose an image
		
			}//
		});
		
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		initializeButtons();
		initializeComboBoxes();
		initializeListViews();
		setActions();
		
		levelChooser = new HBox();
		myVBox = new VBox();
		myHBox = new HBox();
		levelChooser.getChildren().addAll(myAddLevelButton, myLevelDropdown, myPanelSelector);
		myHBox.getChildren().addAll(myAddGameObjectButton, myAddSceneBackgroundImageButton);
		myVBox.getChildren().addAll(levelChooser, myLevelObjects, myHBox);
		borderPane.setCenter(myVBox);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	

}
