package authoring.displayrefactored.authoringuicomponents;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoring.GameObject;
import authoring.ObjectInfoObservable;
import authoring.Property;
import authoring.displayrefactored.controllers.ObjectInfoPanelController;
import data.propertiesFiles.ResourceBundleManager;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ObjectInfoPanelRefactored extends AuthoringUIComponentRefactored implements Observer {

	private ScrollPane myScrollPane;
	private VBox myVBox;
	private HBox buttonsHBox;
	private TableView<Property> gameObjectCoordinates;
	private List<GameObject> gameObjects;
	private ObjectInfoPanelController controller;
	private ObjectInfoObservable observable;
	private Button editBehaviorsButton;
	private Button editEventsButton;
	private Button duplicateButton;
	private ImageView imageView;
	private Label objectName;
	private Label instances;
	private Label properties;
	
	private static final double PANE_PREF_WIDTH = 250;
	private static final double DEFAULT_SPACING = 5;
	
	public ObjectInfoPanelRefactored(ObjectInfoPanelController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		initializeButtons();
	
		gameObjectCoordinates = new TableView<>();

		objectName = new Label();
		instances = new Label("Instances");
		
		buttonsHBox = new HBox();
		buttonsHBox.getChildren().addAll(editBehaviorsButton,editEventsButton,duplicateButton);
		myScrollPane = new ScrollPane();
		
		
		myVBox = new VBox(DEFAULT_SPACING);
		myVBox.setAlignment(Pos.CENTER);
		
		properties = new Label("Properties");
		properties.setStyle("-fx-border-color: black");
//		initializeVBox();
		
//		myScrollPane.setPrefWidth(PANE_PREF_WIDTH);
//		myScrollPane.setContent(myVBox);
//		myScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		
		borderPane.setCenter(myVBox);	
		borderPane.setTop(properties);
	}
	
	
	
	private void initializeButtons() {
		
		editBehaviorsButton = new Button(ResourceBundleManager.getAuthoring("EditBehaviors"));
		editEventsButton = new Button(ResourceBundleManager.getAuthoring("EditEvents"));
		duplicateButton = new Button(ResourceBundleManager.getAuthoring("Duplicate"));
		
	}
	
	private void mapButtonActions(List<GameObject> list) {
		
		editBehaviorsButton.setOnAction(e->{
			
		});
		
		editEventsButton.setOnAction(e->{
			
		});
		
		duplicateButton.setOnAction(e->{
			
		});
	}

	private void initializeVBox() {
		myVBox.getChildren().clear();
		mapButtonActions(gameObjects);
		myVBox.getChildren().addAll(imageView,objectName,buttonsHBox,instances,gameObjectCoordinates);
		
	}

	
		
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		observable = (ObjectInfoObservable) o;
		updateInfo(observable.getInstances(), observable.getCurrentGameObject(), observable.getCurrentImage());

		initializeVBox();
	}
	
	private void updateInfo(List<GameObject> list, GameObject current, Image image) {
		
		gameObjects = list;
		
		objectName.setText("Name: " + current.getName() + "   Image: " + current.getMandatoryBehavior().getProperty("imagePath").getValue());
		
		imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(100);
		
		for (GameObject gameObject: list) {
			
			
		}
		
	}

}
