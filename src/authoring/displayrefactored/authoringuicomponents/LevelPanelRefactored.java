package authoring.displayrefactored.authoringuicomponents;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoring.GameObject;
import authoring.GameScene;
import authoring.LevelsObservable;
import authoring.displayrefactored.controllers.LevelPanelController;
import authoring.displayrefactored.popups.LevelSizePopupRefactored;
import authoring.displayrefactored.popups.NewGameObjectPopupRefactored;
import authoring.displayrefactored.popups.NewLevelPopupRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class LevelPanelRefactored extends AuthoringUIComponentRefactored implements Observer{

	private VBox myVBox;
	private HBox myHBox;
	private HBox levelChooser;
	private ComboBox<GameScene> myLevelDropdown;
	private ComboBox<String> myPanelSelector; 
	private Button myAddLevelButton;
	private Button myAddGameObjectButton;
	private Button myAddSceneBackgroundImageButton;
	private Button myLevelSizeButton;
	private ListView<GameObject> myLevelObjects;
	private LevelPanelController controller;
	private LevelsObservable levelsObservable = null;
	
	public LevelPanelRefactored(LevelPanelController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		
	}
	
	private void initializeButtons() {
		myAddLevelButton = new Button(ResourceBundleManager.getAuthoring("AddSceneButton"));
		myAddGameObjectButton = new Button(ResourceBundleManager.getAuthoring("AddGameObjectButton"));
		myAddSceneBackgroundImageButton = new Button(ResourceBundleManager.getAuthoring("AddSceneBackgroundImageButton"));
		myLevelSizeButton = new Button(ResourceBundleManager.getAuthoring("EditLevelSize"));
	}
	
	private void initializeComboBoxes() {
		myLevelDropdown = new ComboBox<>();
		myLevelDropdown.setPromptText(ResourceBundleManager.getAuthoring("SelectSceneDropDown"));
		myPanelSelector = new ComboBox<>();
		myPanelSelector.setPromptText(ResourceBundleManager.getAuthoring("ChoosePanel"));
		myPanelSelector.getItems().add("Background");
		myPanelSelector.getItems().add("Foreground");
		myPanelSelector.valueProperty().addListener((o, old, neww) -> {

			controller.switchPanes(neww);
			
		});
	}
	
	
	private void initializeListViews() {
		myLevelObjects = new ListView<>();
		myLevelObjects.setOnMouseClicked(e->{
			controller.setCurrentGameObject(myLevelObjects.getSelectionModel().getSelectedItem());
		});
	}
	
	
	private void setActions() {
		myAddLevelButton.setOnAction(e -> {
			NewLevelPopupRefactored popupRefactored = new NewLevelPopupRefactored(controller);
		});
		myAddGameObjectButton.setOnAction(e -> {
			NewGameObjectPopupRefactored popupRefactored = new NewGameObjectPopupRefactored(controller);
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
		myLevelSizeButton.setOnAction(e -> {
			new LevelSizePopupRefactored(controller);
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
		myVBox.getChildren().addAll(levelChooser, myLevelObjects, myHBox, myLevelSizeButton);
		borderPane.setCenter(myVBox);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		levelsObservable = (LevelsObservable) o;
		updateLevelObjects(levelsObservable.getGameObjects());
		updateLevelDropdown1(levelsObservable.getScenes());
	}
	
	private void updateLevelObjects(List<GameObject> list) {
//		System.out.println("There should be " + list.size() + " objects in this list.");
		
		myLevelObjects.getItems().clear();
		myLevelObjects.getItems().addAll(FXCollections.observableArrayList(list));
	}
	
	private void updateLevelDropdown1(List<GameScene> list) {
		myLevelDropdown.getItems().clear();
		myLevelDropdown.getItems().addAll(list);
	
	}

	public void updateLevelDropdown(int i, GameScene scene) {
		// TODO Auto-generated method stub
		myLevelDropdown.getItems().add(i, scene);
		
	}
	
	

}
