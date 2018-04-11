package gamePlayer;

import java.io.IOException;
import java.text.SimpleDateFormat;

import data.Serializer;
import data.GameDescriptionProvider;
import engine.Engine;
import engine.GameState;
import gamePlayer.buttons.ClearHighScoresButton;
import gamePlayer.buttons.ConcreteButtonData;
import gamePlayer.buttons.LoadButton;
import gamePlayer.buttons.SaveButton;
import gamePlayer.buttons.NewGameButton;
import gamePlayer.buttons.ReplayButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author jeffreyli, calvinma Concrete game player, manages game selection,
 *         loading, saving, HUD and high scores.
 */
public class ConcreteGamePlayer implements GamePlayer {

	Scene myScene;
	Stage myStage;
	Button saveButton;
	Button loadButton;
	Button replayButton;
	Button newGameButton;
	Button clearHighScoresButton;
	HUD hud;
	Pane gameDisplay;

	Engine engine;
	GameState gameState;
	String currentGameName;
	GameDescriptionProvider gameDescriptionProvider;
	String mostRecentFile;

	private ConcreteButtonData buttonData;

	ConcreteHighScores highScores;

	private final static double SCREEN_HEIGHT = 650;
	private final static double SCREEN_WIDTH = 1250;
	private Group root;
	private final static Paint BACKGROUND = Color.ANTIQUEWHITE;

	public ConcreteGamePlayer(Stage stage) {

		// back end set up.
		gameDescriptionProvider = new GameDescriptionProvider();

		// front end set up;
		root = new Group();
		myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
		myStage = stage;
		myStage.setScene(myScene);

		highScores = new ConcreteHighScores("hi");

		root.getChildren().add(highScores.getScores());

		buttonData = new ConcreteButtonData(stage, this, gameDescriptionProvider, root);

		setupButtons();
		buttonData.setHighScores(highScores);

	}

	/**
	 * initialises buttons on screen
	 */
	private void setupButtons() {
		clearHighScoresButton = new ClearHighScoresButton(970, 310, 235, 60, buttonData);
		root.getChildren().add(clearHighScoresButton);
		newGameButton = new NewGameButton(970, 350, 235, 60, buttonData);
		root.getChildren().add(newGameButton);
		loadButton = new LoadButton(970, 390, 235, 60, buttonData);
		root.getChildren().add(loadButton);
		saveButton = new SaveButton(970, 430, 235, 60, buttonData);
		root.getChildren().add(saveButton);
		replayButton = new ReplayButton(970, 470, 235, 60, buttonData);
		root.getChildren().add(replayButton);

	}

	@Override
	public void playGame(String file) {
		engine = new Engine(file);
		currentGameName = gameDescriptionProvider.getGameName(file);
		buttonData.setCurrentGameName(currentGameName);
		mostRecentFile = file;
		buttonData.setMostRecentFile(mostRecentFile);
		gameDisplay = engine.getDisplay();
		gameDisplay.setLayoutX(30);
		gameDisplay.setLayoutY(30);
		gameDisplay.setPrefSize(900, 590);
		gameDisplay.setStyle("-fx-background-color: white;");
		hud = new ConcreteHUD(currentGameName);

		myScene.setOnKeyPressed(e -> engine.handleKeyInput(e.getCode()));
		//myScene.setOnMouseClicked(e -> engine.handleMouseInput(e.getX(), e.getY())); 
		
		root.getChildren().add(gameDisplay);
		root.getChildren().add((Node) hud);

	}

	/**
	 * created for purposes of demonstration, simulates what front end will look
	 * like once game engine is running
	 * 
	 * @param file
	 */
	public void DummyPlayGame(String file) {
		engine = new Engine(file);
		// currentGameName = serializer.getGameName(file);
		currentGameName = "Super Mario Smash Bros";
		mostRecentFile = file;

		// gameDisplay = engine.getDisplay();
		//
		Pane canvas = new Pane();

		gameDisplay = canvas;
		gameDisplay.setLayoutX(30);
		gameDisplay.setLayoutY(30);
		gameDisplay.setPrefSize(900, 590);

		Image image = new Image(
				getClass().getClassLoader().getResourceAsStream("testGameFolder/testMario/dummy-mario.png"));
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitHeight(590);
		imageView.setFitWidth(900);
		canvas.getChildren().add(imageView);

		hud = new ConcreteHUD(currentGameName);
		root.getChildren().add(gameDisplay);
		root.getChildren().add((Node) hud);
	}

	@Override
	public Scene getScene() {
		return myScene;
	}

}
