package authoring.displayrefactored.popups;

import java.io.File;
import java.io.IOException;

import authoring.displayrefactored.controllers.ObjectInfoPanelController;
import data.GameObjectManager;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class NewLibraryObjectPopupRefactored extends PopupRefactored {

	private static final int xSize = 400;
	private static final int ySize = 600;
	private static final int DEFAULT_SPACING = 50;
	private GameObjectManager manager;
	private VBox myVBox;
	TextField nameText;
	TextField imageText;
	Button chooseImageButton;
	Button saveButton;
	Image image;
	
	//TODO: make interface
	public NewLibraryObjectPopupRefactored(GameObjectManager manager) {
		// TODO Auto-generated constructor stub
		super();
		this.manager = manager;
		open(xSize, ySize);
		generatePopup();
		mapButtons();
	}
	
	@Override
	protected void generatePopup() {
		// TODO Auto-generated method stub
		myVBox = new VBox(DEFAULT_SPACING);
		image = null;
		HBox nameObject = new HBox(DEFAULT_SPACING);
		nameText = new TextField();
		nameObject.getChildren().addAll(new Label("Name: "), nameText);
		chooseImageButton = new Button(ResourceBundleManager.getAuthoring("ChooseImageButton"));

		saveButton = new Button(ResourceBundleManager.getAuthoring("Save"));

		myVBox.getChildren().addAll(nameObject, chooseImageButton, saveButton);

		BorderPane borderPane = getPane();
		borderPane.setCenter(myVBox);
	}

	@Override
	protected void mapButtons() {
		// TODO Auto-generated method stub
		chooseImageButton.setOnAction(e -> {
			try {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose Object Image");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				File imageFile = fileChooser.showOpenDialog(new Stage());
				image = new Image(imageFile.toURI().toString());
				// put image.getName into SceneBackground
			} catch (Exception exception) {
				// do nothing
				// this just means the user didn't choose an image

			} //
		});

		saveButton.setOnAction(e -> {
			// System.out.println("ADDING TO GAMEOBJECT NAME: " + nameText.getText());
			if (manager.checkUniqueName(nameText.getText())) {
				try {
					manager.saveCustomGameObject(nameText.getText(), image);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				close();

			} else {
				
				new ErrorBox("Duplicate Object Name", "Please Change the Name of Your Object");

			}
		});
	}
	
}
