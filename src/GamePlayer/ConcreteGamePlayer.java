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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ConcreteGamePlayer implements GamePlayer {

	Scene myScene;
	Stage myStage;
	Button saveButton;
	Button loadButton;
	Button replayButton;
	HUD hud;

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
		hud = new ConcreteHUD();
		
		// front end set up;
		root = new Group();
		myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
		myStage = stage;
		myStage.setScene(myScene);
		hud = new ConcreteHUD();
		root.getChildren().add((Node) hud);

		highScores = new ConcreteHighScores("hi");
		highScores.addScore("bfd", 4);
		highScores.printQ();
		root.getChildren().add(highScores.getScores());
		highScores.addScore("43", 9);
		setupButtons();	
		
	}

	private void setupButtons() {
		loadButton = new LoadButton(970, 310, 235, 60, this, myStage);
		root.getChildren().add(loadButton);
		saveButton = new SaveButton(970, 350, 235, 60, this);
		root.getChildren().add(saveButton);
		replayButton = new ReplayButton(970, 390, 235, 60, this);
		root.getChildren().add(replayButton);


	}

	@Override
	public void playGame(String file) {
		engine = new Engine(file);
		// parse file to get name of game.
		currentGameName = serializer.getGameName(file);
		mostRecentFile = file;
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
