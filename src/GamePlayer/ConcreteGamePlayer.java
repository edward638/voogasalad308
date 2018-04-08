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

	private final static double SCREEN_HEIGHT = 300;
	private final static double SCREEN_WIDTH = 500;// 915;
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
		setupButtons();	
		highScores = new ConcreteHighScores("hi");
		highScores.addScore("bfd", 4);
		highScores.printQ();
		root.getChildren().add(highScores.getScores());
		highScores.addScore("43", 9);
		hud = new ConcreteHUD();
		root.getChildren().add((Node) hud);
		
	}

	private void setupButtons() {
		saveButton = new SaveButton(30, 30, 30, 30, this);
		root.getChildren().add(saveButton);

		loadButton = new LoadButton(60, 60, 60, 60, this, myStage);
		root.getChildren().add(loadButton);
		replayButton = new ReplayButton(60, 60, 60, 60, this);
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
	
	public static void main(String[] args) {
		String timeStamp = new SimpleDateFormat("MM/dd HH:mm").format(new java.util.Date());
		System.out.println("Mario " + timeStamp);
	}
}
