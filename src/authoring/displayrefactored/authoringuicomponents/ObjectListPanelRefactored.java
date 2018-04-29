package authoring.displayrefactored.authoringuicomponents;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import authoring.GameObject;
import authoring.ObjectInfoObservable;
import authoring.displayrefactored.controllers.ObjectInfoPanelController;
import authoring.displayrefactored.popups.NewGameObjectPopupRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


/**
 * 
 * @author Edward Zhuang
 *
 */
public class ObjectListPanelRefactored extends AuthoringUIComponentRefactored implements Observer {

	private HBox upperHBox;
	private HBox lowerHBox;
	private VBox myVBox;
	private Button myAddSceneBackgroundImageButton;
	private ListView<GameObject> myLevelObjects;
	private Button myDeleteObjectButton;
	private Button undoActionButton;
	private ObjectInfoPanelController controller;
	private ObjectInfoObservable observable = null;
	
	public ObjectListPanelRefactored(ObjectInfoPanelController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}
	
	@Override
	protected void generateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		initializeButtons();
		initializeListViews();
		setActions();
		myVBox = new VBox();
		upperHBox = new HBox();
		lowerHBox = new HBox();
		upperHBox.getChildren().addAll(myAddSceneBackgroundImageButton);
		lowerHBox.getChildren().addAll(myDeleteObjectButton,undoActionButton);
		myVBox.getChildren().addAll(myLevelObjects, upperHBox, lowerHBox);
		borderPane.setCenter(myVBox);
	}
	
	private void initializeButtons() {
		myAddSceneBackgroundImageButton = new Button(ResourceBundleManager.getAuthoring("AddSceneBackgroundImageButton"));
		myDeleteObjectButton = new Button(ResourceBundleManager.getAuthoring("AddDeleteObjectButton"));
		undoActionButton = new Button(ResourceBundleManager.getAuthoring("AddUndoActionButton"));
	}
	
	private void initializeListViews() {
		myLevelObjects = new ListView<>();
		myLevelObjects.setMaxHeight(300);
	}
	
	private void setActions() {
//		myAddGameObjectButton.setOnAction(e -> {
//			NewGameObjectPopupRefactored popupRefactored = new NewGameObjectPopupRefactored(controller);
//		});
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
		undoActionButton.setOnMouseClicked(e->{
			controller.restorePreviousGameScene();
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		observable = (ObjectInfoObservable) arg0;
		updateLevelObjects(observable.getMyObjects());
	}
	
	private void updateLevelObjects(List<GameObject> list) {
		
		Set<GameObject> set = new HashSet<>(list);
		
		myLevelObjects.getItems().clear();
		myLevelObjects.getItems().addAll(set);
		myLevelObjects.getSelectionModel().select(observable.getCurrentGameObject());
	}

}
