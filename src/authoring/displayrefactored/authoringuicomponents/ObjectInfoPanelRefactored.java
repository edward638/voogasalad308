package authoring.displayrefactored.authoringuicomponents;

import java.util.Observable;
import java.util.Observer;

import authoring.ObjectInfoObservable;
import authoring.displayrefactored.controllers.ObjectInfoPanelController;
import authoring.displayrefactored.objectinfoboxes.GameObjectInfoBox;
import authoring.displayrefactored.objectinfoboxes.LibraryObjectInfoBox;
import data.LibraryObservable;
import data.LibraryObserver;

/**
 * 
 * @author Edward Zhuang
 *
 */
public class ObjectInfoPanelRefactored extends AuthoringUIComponentRefactored implements Observer, LibraryObserver {


	private ObjectInfoPanelController controller;
	private ObjectInfoObservable observable;
	private LibraryObservable libraryObservable;



	public ObjectInfoPanelRefactored(ObjectInfoPanelController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}

	public void setObservable(ObjectInfoObservable observable) {
		this.observable = observable;
	}
	
	public void setLibraryObservable(LibraryObservable libraryObservable) {
		this.libraryObservable = libraryObservable;
	}

	@Override
	protected void generateComponent() {
		// TODO Auto-generated method stub

	}

	private void initializeButtons() {

		editBehaviorsButton = new Button(ResourceBundleManager.getAuthoring("EditBehaviors"));
		editEventsButton = new Button(ResourceBundleManager.getAuthoring("EditEvents"));
		duplicateButton = new Button(ResourceBundleManager.getAuthoring("Duplicate"));

	}

	private void mapButtonActions(List<GameObject> list) {

		editBehaviorsButton.setOnAction(e -> {
			new BehaviorPopup(list);
		});

		editEventsButton.setOnAction(e -> {
			new EventsPopupRefactored(list, controller.getAllGameObjects());
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
		observable = (ObjectInfoObservable) arg0;
		if (observable.getCurrentGameObject() != null) {
		GameObjectInfoBox gameObjectInfoBox = new GameObjectInfoBox(observable.getCurrentGameObject(),observable.getInstances(), controller);	
		gameObjectInfoBox.initializeVBox();
		gameObjectInfoBox.addToBorderPane(getBorderPane());
		} else {
			getBorderPane().getChildren().clear();
		}
		
	}

	@Override
	public void notifyObserver() {
		LibraryObjectInfoBox libraryObjectInfoBox = libraryObservable.getLibraryObjectInfoBox();
		libraryObjectInfoBox.initializeVBox();
		libraryObjectInfoBox.addToBorderPane(getBorderPane());
	}

}
