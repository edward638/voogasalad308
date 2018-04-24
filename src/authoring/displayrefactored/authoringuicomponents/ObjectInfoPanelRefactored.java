package authoring.displayrefactored.authoringuicomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoring.GameObject;
import authoring.ObjectInfoObservable;
import authoring.ObjectInfoObserver;
import authoring.Property;
import authoring.displayrefactored.controllers.ObjectInfoPanelController;
import data.propertiesFiles.ResourceBundleManager;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ObjectInfoPanelRefactored extends AuthoringUIComponentRefactored implements ObjectInfoObserver {

	private ScrollPane myScrollPane;
	private VBox myVBox;
	private HBox buttonsHBox;
	private TableView<ObjectCoordinatesInsertion> gameObjectCoordinates;
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

	public void setObservable(ObjectInfoObservable observable) {
		this.observable = observable;
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
		buttonsHBox.getChildren().addAll(editBehaviorsButton, editEventsButton, duplicateButton);
		myScrollPane = new ScrollPane();

		myVBox = new VBox(DEFAULT_SPACING);
		myVBox.setAlignment(Pos.CENTER);

		properties = new Label("Properties");

		// initializeVBox();

		// myScrollPane.setPrefWidth(PANE_PREF_WIDTH);
		// myScrollPane.setContent(myVBox);
		// myScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

		borderPane.setCenter(myVBox);

	}

	private void initializeButtons() {

		editBehaviorsButton = new Button(ResourceBundleManager.getAuthoring("EditBehaviors"));
		editEventsButton = new Button(ResourceBundleManager.getAuthoring("EditEvents"));
		duplicateButton = new Button(ResourceBundleManager.getAuthoring("Duplicate"));

	}

	private void mapButtonActions(List<GameObject> list) {

		editBehaviorsButton.setOnAction(e -> {

		});

		editEventsButton.setOnAction(e -> {

		});

		duplicateButton.setOnAction(e -> {

		});
	}

	private void initializeVBox() {
		myVBox.getChildren().clear();
		mapButtonActions(gameObjects);

		myVBox.getChildren().addAll(properties, imageView, objectName, buttonsHBox, instances, gameObjectCoordinates);

	}

	public void notifyOfChanges() {
		updateInfo(observable.getInstances(), observable.getCurrentGameObject(), observable.getCurrentImage());

		initializeVBox();
	}

	private void updateInfo(List<GameObject> list, GameObject current, Image image) {

		gameObjects = list;

		objectName.setText("Name: " + current.getName() + "   Image: "
				+ current.getMandatoryBehavior().getProperty("imagePath").getValue());

		imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(100);

		gameObjectCoordinates = new TableView<>();

		setupTableColumns();

		List<ObjectCoordinatesInsertion> insertions = new ArrayList<>();

		for (GameObject gameObject : list) {

			insertions.add(new ObjectCoordinatesInsertion(gameObject,
					(Double) gameObject.getMandatoryBehavior().getProperty("xPos").getValue(),
					(Double) gameObject.getMandatoryBehavior().getProperty("yPos").getValue()));

		}

		ObservableList<ObjectCoordinatesInsertion> observableInsertions = FXCollections.observableArrayList(insertions);

//		System.out.println(observableInsertions);

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
			((ObjectCoordinatesInsertion) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setXpos(Double.parseDouble(t.getNewValue()));
			controller.updatePositions();
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
			((ObjectCoordinatesInsertion) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setYpos(Double.parseDouble(t.getNewValue()));
			controller.updatePositions();
		});
	}

}
