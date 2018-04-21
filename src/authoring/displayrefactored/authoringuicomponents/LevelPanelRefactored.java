package authoring.displayrefactored.authoringuicomponents;

import java.util.Observable;
import java.util.Observer;

import authoring.GameObject;
import authoring.GameScene;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LevelPanelRefactored extends AuthoringUIComponentRefactored implements Observer{

	Pane pane;
	private VBox myVBox;
	private HBox myHBox;
	private ComboBox<GameScene> myLevelDropdown;
	private ComboBox<String> myPanelSelectorComboBox; 
	private Button myAddLevelButton;
	private Button myAddGameObjectButton;
	private Button myAddSceneBackgroundImageButton;
	private ListView<GameObject> myLevelObjects;
	
	public LevelPanelRefactored() {
		// TODO Auto-generated constructor stub
		pane = new Pane();
		initializeButtons();
	}
	
	private void initializeButtons() {
		myAddLevelButton = new Button(ResourceBundleManager.getAuthoring("AddSceneButton"));
		myAddGameObjectButton = new Button(ResourceBundleManager.getAuthoring("AddGameObjectButton"));
		myAddSceneBackgroundImageButton = new Button(ResourceBundleManager.getAuthoring("AddSceneBackgroundImageButton"));
	}
	
	private void initializeComboBoxes() {
		
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		borderPane.setCenter(pane);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
