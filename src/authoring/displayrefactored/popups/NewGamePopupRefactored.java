package authoring.displayrefactored.popups;

import authoring.Game;
import authoring.displayrefactored.LoadAuthoringInterface;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class NewGamePopupRefactored extends PopupRefactored {

	private static final int xSize = 300;
	private static final int ySize = 200;
	HBox gameName;
	HBox gameDescription;
	VBox vBox;
	Button newGameButton;
	TextField gameNameText;
	TextField gameDescriptionText;
	Game game;
	LoadAuthoringInterface loadAuthorer;
	
	public NewGamePopupRefactored(LoadAuthoringInterface loadAuthoringInterface) {
		super();
		loadAuthorer = loadAuthoringInterface;
		open(xSize, ySize);
		GeneratePopup();
		mapButtons();
	}
	
	protected void GeneratePopup() {
		vBox = new VBox();
		gameName = new HBox();
		gameDescription = new HBox();
		newGameButton = new Button("Create game!");
		
		gameNameText = new TextField();
		gameName.getChildren().addAll(new Label("Name: "), gameNameText);
		gameDescriptionText = new TextField();
		gameDescription.getChildren().addAll(new Label("Description: "), gameDescriptionText);
		
		vBox.getChildren().addAll(gameName, gameDescription, newGameButton);
		
		BorderPane borderPane = getPane();
		borderPane.setCenter(vBox);
		
	};

	protected void mapButtons() {
		// TODO Auto-generated method stub
		newGameButton.setOnAction( e -> 
		{	
			game = new Game(gameNameText.getText());
			game.setGameDescription(gameDescriptionText.getText()); 
			loadAuthorer.loadAuthoringEnvironment(game);
			close();
		});
	}
	
	
	
	
}
