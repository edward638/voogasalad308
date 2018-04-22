package authoring.displayrefactored;

import authoring.Game;
import authoring.displayrefactored.controllers.AuthoringEnvironmentRefactored;
import authoring.displayrefactored.popups.NewGamePopupRefactored;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AuthoringDisplayRefactored implements LoadAuthoringInterface {
	
	private static final int GUI_LAYOUT_X = 20;
	private static final int GUI_LAYOUT_Y = 40;
	private static final String SAVE_GAME = "Save Game";
	private static final String LOAD_GAME = "Load Game";
	private static final String NEW_GAME = "New Game";
	private static final String NAME = "Authoring Environment";
	private static final int WIDTH = 1500;
	private static final int HEIGHT = 1000;
	private Pane root;
	private HBox buttonBox;
	private Button newGameButton;
	private Button loadGameButton;
	private Button saveGameButton;
	private AuthoringEnvironmentRefactored authoringEnvironmentRefactored;
	
	public AuthoringDisplayRefactored(Stage stage) {
		initialize(stage);
		initializeButtonBox();
	}

	private void initialize(Stage stage) {
		// TODO Auto-generated method stub
		root = new Pane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.setTitle(NAME);
		stage.show();
	}
	
	private void initializeButtonBox() {
		buttonBox = new HBox();
		newGameButton = new Button(NEW_GAME);
		loadGameButton = new Button(LOAD_GAME);
		saveGameButton = new Button(SAVE_GAME);
		buttonBox.getChildren().addAll(newGameButton,loadGameButton,saveGameButton);
		setButtonActions();
		root.getChildren().add(buttonBox);
	}

	private void setButtonActions() {
		// TODO Auto-generated method stub
		newGameButton.setOnAction(e -> {
			NewGamePopupRefactored popup = new NewGamePopupRefactored(this);
		});
	}
	
	public void loadAuthoringEnvironment(Game game) {
		authoringEnvironmentRefactored = new AuthoringEnvironmentRefactored(game);
		Pane GUIPane = authoringEnvironmentRefactored.getGUI();
		GUIPane.setLayoutX(GUI_LAYOUT_X);
		GUIPane.setLayoutY(GUI_LAYOUT_Y);
		root.getChildren().add(GUIPane);
	}

}
