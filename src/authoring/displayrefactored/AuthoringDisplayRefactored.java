package authoring.displayrefactored;

import authoring.Game;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AuthoringDisplayRefactored {
	
	private static final String SAVE_GAME = "Save Game";
	private static final String LOAD_GAME = "Load Game";
	private static final String NEW_GAME = "New Game";
	private Pane root;
	private HBox buttonBox;
	private Button newGameButton;
	private Button loadGameButton;
	private Button saveGameButton;
	
	public AuthoringDisplayRefactored(Stage stage) {
		initialize(stage);
		initializeButtonBox();
	}

	private void initialize(Stage stage) {
		// TODO Auto-generated method stub
		root = new Pane();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	private void initializeButtonBox() {
		buttonBox = new HBox();
		newGameButton = new Button(NEW_GAME);
		loadGameButton = new Button(LOAD_GAME);
		saveGameButton = new Button(SAVE_GAME);
		buttonBox.getChildren().addAll(newGameButton,loadGameButton,saveGameButton);
		setButtonActions();
	}

	private void setButtonActions() {
		// TODO Auto-generated method stub
		newGameButton.setOnAction(e -> {
			
		});
	}

}
