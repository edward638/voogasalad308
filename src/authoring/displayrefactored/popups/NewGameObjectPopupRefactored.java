package authoring.displayrefactored.popups;


import java.io.File;

import authoring.display.buttonevents.ChooseImageEvent;
import authoring.displayrefactored.controllers.LevelPanelController;
import data.propertiesFiles.ResourceBundleManager;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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

public class NewGameObjectPopupRefactored extends PopupRefactored{

	private static final int xSize = 400;
	private static final int ySize = 600;
	private static final int DEFAULT_SPACING = 20;
	private LevelPanelController controller;
	private VBox myVBox;
	TextField nameText;
	TextField xText;
	TextField yText;
	TextField imageText;
	Button chooseImageButton;
	Button saveButton;
	Image image;
	
	public NewGameObjectPopupRefactored(LevelPanelController controller) {
		// TODO Auto-generated constructor stub
		super();
		this.controller = controller;
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

		HBox xPosValues = new HBox(DEFAULT_SPACING);
		xText = new TextField();
		xPosValues.getChildren().addAll(new Label("X Position: "), xText);

		HBox yPosValues = new HBox(DEFAULT_SPACING);
		yText = new TextField();
		yPosValues.getChildren().addAll(new Label("Y Position: "), yText);

		HBox imageInfo = new HBox(DEFAULT_SPACING);
		imageText = new TextField();
		
	
		chooseImageButton = new Button(ResourceBundleManager.getAuthoring("ChooseImageButton"));
		
		imageInfo.getChildren().addAll(chooseImageButton, new Label("Image name: "), imageText);

		saveButton = new Button(ResourceBundleManager.getAuthoring("Save"));

		myVBox.getChildren().addAll(nameObject, xPosValues, yPosValues, imageInfo, saveButton);
		
		BorderPane borderPane = getPane();
		borderPane.setCenter(borderPane);
		
	}

	@Override
	protected void mapButtons() {
		// TODO Auto-generated method stub
		chooseImageButton.setOnAction(e-> {
			try {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose Object Image");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				File imageFile = fileChooser.showOpenDialog(new Stage());
				image = new Image(imageFile.toURI().toString());
				//put image.getName into SceneBackground
			} catch (Exception exception) {
				//do nothing
				//this just means the user didn't choose an image
		
			}//
		});
		
		saveButton.setOnAction(e->{
			controller.addGameObject(nameText.getText(), Double.parseDouble(xText.getText()), Double.parseDouble(yText.getText()), imageText.getText(), image);
		});
				
		
	}

}
