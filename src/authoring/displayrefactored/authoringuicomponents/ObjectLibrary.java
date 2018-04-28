package authoring.displayrefactored.authoringuicomponents;

import authoring.GameObject;
import authoring.displayrefactored.popups.NewLibraryObjectPopupRefactored;
import data.GameObjectManager;
import data.propertiesFiles.ResourceBundleManager;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ObjectLibrary extends AuthoringUIComponentRefactored {

	private static final String NPC = "npc";
	private static final String BLOCK = "block";
	private static final String PLAYER = "player";
	private Button addToGameButton;
	private Button addToLibrary;
	private ListView<GameObject> objectList;
	private ComboBox<String> objectType;
	private GameObjectManager manager;
	private HBox topHBox;
	private HBox bottomHBox;
	private VBox vBox;
	private GameObject currentObject;
	
	public ObjectLibrary(GameObjectManager manager) {
		this.manager = manager;
		GenerateComponent();
	}
	
	public void initializeFXComponents() {
		addToGameButton = new Button(ResourceBundleManager.getAuthoring("AddLevel"));
		addToLibrary = new Button(ResourceBundleManager.getAuthoring("AddLibrary"));
		bottomHBox = new HBox();
		bottomHBox.getChildren().addAll(addToGameButton,addToLibrary);
		objectList = new ListView<>();
		objectType = new ComboBox<>();
		objectType.getItems().addAll(PLAYER,BLOCK,NPC);
		vBox = new VBox();
		vBox.getChildren().addAll(objectType,objectList,bottomHBox);
	}

	@Override
	protected void GenerateComponent() {
		BorderPane borderPane = getBorderPane();
		initializeFXComponents();
		mapFXActions();
		borderPane.setCenter(vBox);
	}
	
	public void updateObjectList(List<GameObject> list) {
		objectList.getItems().clear();
		objectList.getItems().addAll(FXCollections.observableArrayList(list));
	}
	
	private void mapFXActions() {
		addToGameButton.setOnAction(e->{
			manager.addObjectToGame(currentObject);
		});
		addToLibrary.setOnAction(e->{
			NewLibraryObjectPopupRefactored popupRefactored = new NewLibraryObjectPopupRefactored(manager);
		});
		objectList.setOnMouseClicked(e->{
			currentObject = objectList.getSelectionModel().getSelectedItem();
		});
		objectType.valueProperty().addListener((o,old,neww)->{
			manager.changeObjectType(neww);
		});
	}
	

}

