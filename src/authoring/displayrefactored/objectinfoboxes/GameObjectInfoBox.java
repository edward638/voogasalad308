package authoring.displayrefactored.objectinfoboxes;

import java.util.ArrayList;
import java.util.List;

import authoring.GameObject;
import authoring.displayrefactored.authoringuicomponents.ObjectCoordinatesInsertion;
import authoring.displayrefactored.controllers.ObjectInfoSaver;
import authoring.displayrefactored.popups.BehaviorPopup;
import authoring.displayrefactored.popups.EventsPopupRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameObjectInfoBox extends ObjectInfoBox {

private HBox buttonsHBox;
private GameObject gameObject;
private List<GameObject> objectInstances;
private ObjectInfoSaver saver;
private Button editBehaviorsButton;
private Button editEventsButton;
private Button duplicateButton;
private TableView<ObjectCoordinatesInsertion> gameObjectCoordinates;
private ImageView imageView;
private Label objectName;
private Label instances;
private Label properties;
	
	public GameObjectInfoBox(GameObject gameObject, List<GameObject> objectInstances, ObjectInfoSaver saver) {
		super();
		this.gameObject = gameObject;
		this.objectInstances = objectInstances;
		this.saver = saver;
		instances = new Label("Instances");
		properties = new Label("Game Object Properties");
		objectName = new Label("Name: " + gameObject.getName());
		imageView = new ImageView(saver.getImage(gameObject.getName()+"image.png")); //"image.png"
		initializeFXComponents();
		mapFXActions();
	}
	
	@Override
	public void initializeFXComponents() {
		// TODO Auto-generated method stub
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(200);
		buttonsHBox = new HBox();
		editBehaviorsButton = new Button(ResourceBundleManager.getAuthoring("EditBehaviors"));
		editEventsButton = new Button(ResourceBundleManager.getAuthoring("EditEvents"));
		duplicateButton = new Button(ResourceBundleManager.getAuthoring("Duplicate"));
		buttonsHBox.getChildren().addAll(editBehaviorsButton,editEventsButton,duplicateButton);
		gameObjectCoordinates = new TableView<>();
		setupTableColumns();
		List<ObjectCoordinatesInsertion> insertions = new ArrayList<>();
		for (GameObject gameObject : objectInstances) {
			insertions.add(new ObjectCoordinatesInsertion(gameObject,
					(Double) gameObject.getMandatoryBehavior().getProperty("xPos").getValue(),
					(Double) gameObject.getMandatoryBehavior().getProperty("yPos").getValue()));
		}
		ObservableList<ObjectCoordinatesInsertion> observableInsertions = FXCollections.observableArrayList(insertions);
		gameObjectCoordinates.setItems(observableInsertions);
	}

	private void setupTableColumns() {
		gameObjectCoordinates.setEditable(true);
		TableColumn<ObjectCoordinatesInsertion, String> xposCol = new TableColumn("X");
		setupXposCol(xposCol);
		TableColumn<ObjectCoordinatesInsertion, String> yposCol = new TableColumn("Y");
		setupYposCol(yposCol);
		gameObjectCoordinates.getColumns().addAll(xposCol, yposCol);
	}

	private void setupXposCol(TableColumn<ObjectCoordinatesInsertion, String> xposCol) {
		xposCol.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getXpos()));
		xposCol.setMinWidth(125); // set this col width
		xposCol.setMaxWidth(125); // set this col width
		xposCol.setResizable(false);
		xposCol.setEditable(true);
		xposCol.setCellFactory(TextFieldTableCell.<ObjectCoordinatesInsertion>forTableColumn());
		xposCol.setOnEditCommit((CellEditEvent<ObjectCoordinatesInsertion, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow())
					.setXpos(Double.parseDouble(t.getNewValue()));
			System.out.println("setupXposCol() " + Double.parseDouble(t.getNewValue()));
			saver.updatePositions();
		});

	}

	private void setupYposCol(TableColumn<ObjectCoordinatesInsertion, String> yposCol) {
		yposCol.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getYpos()));
		yposCol.setMinWidth(125); // set this col width
		yposCol.setMaxWidth(125); // set this col width
		yposCol.setResizable(false);
		yposCol.setEditable(true);
		yposCol.setCellFactory(TextFieldTableCell.<ObjectCoordinatesInsertion>forTableColumn());
		yposCol.setOnEditCommit((CellEditEvent<ObjectCoordinatesInsertion, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow())
					.setYpos(Double.parseDouble(t.getNewValue()));
			System.out.println("setupYposCol() " + Double.parseDouble(t.getNewValue()));
			saver.updatePositions();
		});
	}
	
	@Override
	public void mapFXActions() {
		// TODO Auto-generated method stub
		editBehaviorsButton.setOnAction(e -> {
			new BehaviorPopup(objectInstances);
		});

		editEventsButton.setOnAction(e -> {
			new EventsPopupRefactored(objectInstances, saver.getAllGameObjects());
		});

		duplicateButton.setOnAction(e -> {
			saver.duplicateGameObject();
		});
	}

	@Override
	public void initializeVBox() {
		// TODO Auto-generated method stub
		getVBox().getChildren().clear();
		getVBox().getChildren().addAll(properties, imageView, objectName, buttonsHBox, instances, gameObjectCoordinates);
	}

}
