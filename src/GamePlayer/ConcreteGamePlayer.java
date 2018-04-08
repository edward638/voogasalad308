package GamePlayer;

import java.io.IOException;
import java.text.SimpleDateFormat;

import Data.Serializer;
import engine.Engine;
import engine.GameState;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ConcreteGamePlayer implements GamePlayer {

	Scene myScene;
	Stage myStage;
	Button saveButton;
	Button loadButton;
	Button replayButton;
	Button newGameButton;
	HUD hud;
	Pane gameDisplay;

	Engine engine;
	GameState gameState;
	String currentGameName;
	Serializer serializer;
	String mostRecentFile;

	ConcreteHighScores highScores;

	private final static double SCREEN_HEIGHT = 650;
	private final static double SCREEN_WIDTH = 1250;
	private Group root;
	private final static Paint BACKGROUND = Color.ANTIQUEWHITE;

	public ConcreteGamePlayer(Stage stage) {

		// back end set up.
		Serializer serializer = new Serializer();	
		
		// front end set up;
		root = new Group();
		myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
		myStage = stage;
		myStage.setScene(myScene);

		highScores = new ConcreteHighScores("hi");
		highScores.printQ();
		root.getChildren().add(highScores.getScores());

		setupButtons();

	}

	private void setupButtons() {
		newGameButton = new newGameButton(970, 310, 235, 60, this, myStage);
		root.getChildren().add(newGameButton);
		loadButton = new LoadButton(970, 350, 235, 60, this, myStage);
		root.getChildren().add(loadButton);
		saveButton = new SaveButton(970, 390, 235, 60, this);
		root.getChildren().add(saveButton);
		replayButton = new ReplayButton(970, 430, 235, 60, this);
		root.getChildren().add(replayButton);

	}

	@Override
	public void playGame(String file) {
		engine = new Engine(file);
		currentGameName = serializer.getGameName(file);
		mostRecentFile = file;
		gameDisplay = engine.getDisplay();
		hud = new ConcreteHUD(currentGameName);

		root.getChildren().add(gameDisplay);
		root.getChildren().add((Node) hud);

	}

	public void DummyPlayGame(String file) {
		engine = new Engine(file);
		// currentGameName = serializer.getGameName(file);
		currentGameName = "Super Mario Smash Bros";
		mostRecentFile = file;

		// gameDisplay = engine.getDisplay();
		Pane canvas = new Pane();
		canvas.setLayoutX(30);
		canvas.setLayoutY(30);
		canvas.setPrefSize(900, 590);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("images/dummy-mario.png"));
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitHeight(590);
		imageView.setFitWidth(900);
		canvas.getChildren().add(imageView);
		gameDisplay = canvas;
		
		hud = new ConcreteHUD(currentGameName);
		root.getChildren().add(gameDisplay);
		root.getChildren().add((Node) hud);

	}

	// are we meant to deal with the exception?
	@Override
	public void saveGame() throws IOException {
		String timeStamp = new SimpleDateFormat("MM/dd HH:mm").format(new java.util.Date());
		serializer.gameEngineToXML(currentGameName + " " + "MM/dd HH:mm", gameState.getDisplayStates());
	}

	@Override
	public Scene getScene() {
		return myScene;
	}

	public String getMostRecentFile() {
		return mostRecentFile;
	}

}
