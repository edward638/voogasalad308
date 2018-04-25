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

	private HBox levelChooser;
	private ComboBox<GameScene> myLevelDropdown;
	
	private Button myAddLevelButton;
	private LevelPanelController controller;
	private LevelsObservable levelsObservable = null;
	
	public LevelPanelRefactored(LevelPanelController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		
	}
	
	private void initializeButtons() {
		myAddLevelButton = new Button(ResourceBundleManager.getAuthoring("AddSceneButton"));
	}
	
	private void initializeComboBoxes() {
		myLevelDropdown = new ComboBox<>();
		myLevelDropdown.setPromptText(ResourceBundleManager.getAuthoring("SelectSceneDropDown"));
		
	}
	
	private void setActions() {
		myAddLevelButton.setOnAction(e -> {
			NewLevelPopupRefactored popupRefactored = new NewLevelPopupRefactored(controller);
		});
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		initializeButtons();
		initializeComboBoxes();
		setActions();
		levelChooser = new HBox();
		levelChooser.getChildren().addAll(myAddLevelButton, myLevelDropdown);
		borderPane.setCenter(levelChooser);
	}

	@Override
	public void update(Observable o, Object arg) {
		levelsObservable = (LevelsObservable) o;
		updateLevelDropdown1(levelsObservable.getScenes());
		updateLevelComboBox(levelsObservable.getCurrentSceneName());
	}
	
	private void updateLevelComboBox(String currentSceneName) {
		myLevelDropdown.setPromptText(currentSceneName);
		
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
