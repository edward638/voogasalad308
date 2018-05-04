package authoring.display.objectinfoboxes;

import java.util.ArrayList;
import java.util.List;

import authoring.GameObject;
import authoring.display.popups.BehaviorPopup;
import authoring.display.popups.EventsPopup;
import data.GameObjectManager;
import data.LibraryObjectSaver;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class LibraryObjectInfoBox extends ObjectInfoBox {

	private HBox buttonsHBox;
	private Button editBehaviorsButton;
	private Button editEventsButton;
	private Button saveEditsButton;
	private ImageView imageView;
	private GameObject gameObject;
	private List<GameObject> dummyList;
	private List<GameObject> libraryObjects;
	private Label objectName;
	private Label properties;
	private LibraryObjectSaver saver;
	
	public LibraryObjectInfoBox(GameObject gameObject, List<GameObject> library, LibraryObjectSaver saver, Image image) {
		super();
		this.gameObject = gameObject;
		this.libraryObjects = library;
		this.saver = saver;
		this.imageView = new ImageView(image);
		dummyList = new ArrayList<>();
		dummyList.add(this.gameObject);
		properties = new Label("Library Object Properties");
		objectName = new Label(this.gameObject.getName());
		initializeFXComponents();
		mapFXActions();
	}
	
	@Override
	public void mapFXActions() {
		editBehaviorsButton.setOnAction(e -> {
			new BehaviorPopup(dummyList);
		});

		editEventsButton.setOnAction(e -> {
			new EventsPopup(dummyList, libraryObjects);
		});
		saveEditsButton.setOnAction(e->{
			saver.saveUpdatedLibraryObject(gameObject);
		});
	}

	@Override
	public void initializeVBox() {
		// TODO Auto-generated method stub
		getVBox().getChildren().clear();
		getVBox().getChildren().addAll(properties, imageView, objectName, buttonsHBox);
	}

	@Override
	public void initializeFXComponents() {
		// TODO Auto-generated method stub
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(200);
		buttonsHBox = new HBox();
		editBehaviorsButton = new Button(ResourceBundleManager.getAuthoring("EditBehaviors"));
		editEventsButton = new Button(ResourceBundleManager.getAuthoring("EditEvents"));
		saveEditsButton = new Button(ResourceBundleManager.getAuthoring("Save"));
		buttonsHBox.getChildren().addAll(editBehaviorsButton,editEventsButton,saveEditsButton);
	}

}
