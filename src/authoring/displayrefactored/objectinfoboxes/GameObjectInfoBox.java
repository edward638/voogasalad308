package authoring.displayrefactored.objectinfoboxes;


import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class GameObjectInfoBox extends ObjectInfoBox {

private HBox buttonsHBox, nameHBox;
private GameObject gameObject;
private List<GameObject> objectInstances;
private ObjectInfoSaver saver;
private Button editBehaviorsButton;
private Button editEventsButton;
private Button duplicateButton;
private Button changeNameButton;
private Button changeImageButton;
private TableView<ObjectCoordinatesInsertion> gameObjectCoordinates;
private ImageView imageView;
private TextField objectName;
private Label instances;
private Label properties;
	
	public GameObjectInfoBox(GameObject gameObject, List<GameObject> objectInstances, ObjectInfoSaver saver) {
		super();
		this.gameObject = gameObject;
		this.objectInstances = objectInstances;
		this.saver = saver;
		instances = new Label("Instances");
		properties = new Label("Game Object Properties");
		objectName = new TextField();
		objectName.setPrefWidth(50);
		objectName.setText(this.gameObject.getName());
		imageView = new ImageView(saver.getImage(gameObject.getImagePath()+".png"));
		initializeFXComponents();
		mapFXActions();
	}
	
	@Override
	public void initializeFXComponents() {
		// TODO Auto-generated method stub
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(200);
		buttonsHBox = new HBox();
		nameHBox = new HBox();
		editBehaviorsButton = new Button(ResourceBundleManager.getAuthoring("EditBehaviors"));
		editEventsButton = new Button(ResourceBundleManager.getAuthoring("EditEvents"));
		duplicateButton = new Button(ResourceBundleManager.getAuthoring("Duplicate"));
		changeNameButton = new Button(ResourceBundleManager.getAuthoring("ChangeName"));
		changeImageButton = new Button(ResourceBundleManager.getAuthoring("ChangeImage"));
		nameHBox.getChildren().addAll(changeNameButton,changeImageButton);
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
		editBehaviorsButton.setOnAction(e -> {
			new BehaviorPopup(objectInstances);
		});
		editEventsButton.setOnAction(e -> {
			new EventsPopupRefactored(objectInstances, saver.getAllGameObjects());
		});

		duplicateButton.setOnAction(e -> {
			saver.duplicateGameObject();
		});
		changeNameButton.setOnAction(e -> {
			for (GameObject object: objectInstances) {
				object.setName(objectName.getText());
			}
			saver.updatePositions();
		});
		changeImageButton.setOnAction(e->{
			try {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose Object Image");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				File image = fileChooser.showOpenDialog(new Stage());

				saver.setImage(new Image(image.toURI().toString()),gameObject.getImagePath());
//				System.out.println("setOnAction");
				
				//put image.getName into SceneBackground
			} catch (Exception exception) {
				//do nothing
				//this just means the user didn't choose an image
		
			}//
			saver.updatePositions();
		});
	}

	@Override
	public void initializeVBox() {
		// TODO Auto-generated method stub
		getVBox().getChildren().clear();
		getVBox().getChildren().addAll(properties, imageView, objectName, nameHBox, buttonsHBox, instances, gameObjectCoordinates);
	}

}
