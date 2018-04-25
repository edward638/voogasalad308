package authoring.displayrefactored.authoringuicomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoring.GameObject;
import authoring.ObjectInfoObservable;
import authoring.ObjectInfoObserver;
import authoring.displayrefactored.controllers.ObjectInfoPanelController;
import authoring.displayrefactored.popups.EventsPopupRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ObjectInfoPanelRefactored extends AuthoringUIComponentRefactored implements Observer {

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
		System.out.println("generate component");
		
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
//			new EventsPopupRefactored(list, controller.getAllGameObjects());
		});

		duplicateButton.setOnAction(e -> {
			controller.duplicateGameObject();
		});
	}

	private void initializeVBox() {
		myVBox.getChildren().clear();
		mapButtonActions(gameObjects);

		myVBox.getChildren().addAll(properties, imageView, objectName, buttonsHBox, instances, gameObjectCoordinates);

	}


	private void updateInfo(List<GameObject> list, GameObject current, String imageName) {

		gameObjects = list;

		objectName.setText("Name: " + current.getName());
		imageView = new ImageView(controller.getImage(imageName));
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(200);

		gameObjectCoordinates = new TableView<>();

		setupTableColumns();

		List<ObjectCoordinatesInsertion> insertions = new ArrayList<>();

		for (GameObject gameObject : list) {
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
			((ObjectCoordinatesInsertion) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setXpos(Double.parseDouble(t.getNewValue()));
			System.out.println("setupXposCol() " + Double.parseDouble(t.getNewValue()));
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
			System.out.println("setupYposCol() " + Double.parseDouble(t.getNewValue()));
			controller.updatePositions();
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		observable = (ObjectInfoObservable) arg0;
		updateInfo(observable.getInstances(), observable.getCurrentGameObject(), observable.getCurrentImageName());
		initializeVBox();
		editEventsButton.setOnAction(e->{
			new EventsPopupRefactored(gameObjects, observable.getMyObjects());
		});
	}

}
