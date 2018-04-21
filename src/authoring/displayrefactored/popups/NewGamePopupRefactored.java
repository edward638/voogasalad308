package authoring.displayrefactored.popups;

import java.io.File;
import java.util.ResourceBundle;

import authoring.display.NewComponentWindow;
import javafx.scene.Node;
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

public class NewGamePopupRefactored extends PopupRefactored {

	HBox gameName;
	HBox gameDescription;
	VBox vBox;
	
	public NewGamePopupRefactored() {
		super();
	}
	
	protected void GeneratePopup() {
		vBox = new VBox();
		gameName = new HBox();
		gameDescription = new HBox();
		
		BorderPane borderPane = getPane();
		
	};

	@Override
	protected Scene setUpScene() {
		VBox root = new VBox();
		
		HBox gameName = new HBox();
		TextField gameNameText = new TextField();
		gameName.getChildren().addAll(new Label("Name: "), gameNameText);
		
		HBox gameDescription = new HBox();
		TextField gameDescriptionText = new TextField();
		gameDescription.getChildren().addAll(new Label("Description: "), gameDescriptionText);
		
		Button newGameButton = makeButton("newGameButton", event -> 
		{	
			Game newGame = new Game(gameNameText.getText());
			newGame.setGameDescription(gameDescriptionText.getText()); 
		});
			
		return new Scene(root);
		
	}
	
}
