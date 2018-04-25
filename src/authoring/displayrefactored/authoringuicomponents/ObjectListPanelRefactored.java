package authoring.displayrefactored.authoringuicomponents;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import authoring.GameObject;
import authoring.displayrefactored.controllers.ObjectInfoPanelController;
import authoring.displayrefactored.popups.NewGameObjectPopupRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ObjectListPanelRefactored extends AuthoringUIComponentRefactored implements Observer {

	private HBox myHBox;
	private VBox myVBox;
	private Button myAddGameObjectButton;
	private Button myAddSceneBackgroundImageButton;
	private ListView<GameObject> myLevelObjects;
	private Button myDeleteObjectButton;
	private ObjectInfoPanelController controller;
	
	public ObjectListPanelRefactored(ObjectInfoPanelController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		initializeButtons();
		initializeListViews();
		setActions();
		myVBox = new VBox();
		myHBox = new HBox();

		myHBox.getChildren().addAll(myAddGameObjectButton, myAddSceneBackgroundImageButton);
		myVBox.getChildren().addAll(myLevelObjects, myHBox, myDeleteObjectButton);
		borderPane.setCenter(myVBox);
	}
	
	private void initializeButtons() {
		myAddGameObjectButton = new Button(ResourceBundleManager.getAuthoring("AddGameObjectButton"));
		myAddSceneBackgroundImageButton = new Button(ResourceBundleManager.getAuthoring("AddSceneBackgroundImageButton"));
		myDeleteObjectButton = new Button(ResourceBundleManager.getAuthoring("AddDeleteObjectButton"));
	}
	
	private void initializeListViews() {
		myLevelObjects = new ListView<>();
	}
	
	private void setActions() {
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
//				System.out.println("setOnAction");
				
				//put image.getName into SceneBackground
			} catch (Exception exception) {
				//do nothing
				//this just means the user didn't choose an image
		
			}//
		});
		myDeleteObjectButton.setOnAction(e -> {
			int index = myLevelObjects.getSelectionModel().getSelectedIndex();
			controller.deleteGameObject(index);
		});
		myLevelObjects.setOnMouseClicked(e->{
			controller.setCurrentGameObject(myLevelObjects.getSelectionModel().getSelectedItem());
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
