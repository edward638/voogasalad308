package gamePlayer;

import data.GameDescriptionProvider;
import engine.Engine;
import engine.GameState;
import gamePlayer.buttons.ClearHighScoresButton;
import gamePlayer.buttons.ConcreteButtonData;
import gamePlayer.buttons.KeyboardBindingButton;
import gamePlayer.buttons.LoadButton;
import gamePlayer.buttons.SaveButton;
import gamePlayer.buttons.ToggleVolumeButton;
import gamePlayer.buttons.NewGameButton;
import gamePlayer.buttons.ReplayButton;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * 
 * @author jeffreyli, calvinma Concrete game player, manages game selection,
 *         loading, saving, HUD and high scores.
 */
public class ConcreteGamePlayer implements GamePlayer {


	private Scene myScene;
	private Stage myStage;
	private Group root;
	private SubScene gameDisplay;

	private GameState gameState;

	private Button saveButton;
	private Button loadButton;
	private Button replayButton;
	private Button newGameButton;
	private Button clearHighScoresButton;
	private Button keyboardBindingButton;
	private Button toggleVolumeButton;
	private ConcreteButtonData buttonData;

	private HUD hud;
	//private Pane gameDisplay;
	private ConcreteHighScores highScores;

	private Engine engine;
	private String currentGameName;
	private GameDescriptionProvider gameDescriptionProvider;
	private String mostRecentFile;
	private KeyInputDictionary keyInputDictionary;

	private static final double SCREEN_HEIGHT = 650;
	private static final double SCREEN_WIDTH = 1250;
	private static final Paint BACKGROUND = Color.ANTIQUEWHITE;
	private static final int buttonXLocation = 970;
	private static final int buttonWidth = 235;
	private static final int buttonHeight = 60;

	private boolean gameSoundsOn;
	private boolean musicOn;
	private int soundLevel;

	public ConcreteGamePlayer(Stage stage) {

		gameDescriptionProvider = new GameDescriptionProvider();

		gameSoundsOn = true;
		musicOn = true;
		soundLevel = 0;

		root = new Group();
		myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
		myStage = stage;
		myStage.setScene(myScene);

		highScores = new ConcreteHighScores("hi");
		root.getChildren().add(highScores.getScores());
		keyInputDictionary = new KeyInputDictionary(engine);

		buttonData = new ConcreteButtonData(stage, this, gameDescriptionProvider, root, keyInputDictionary);
		setupButtons();
		buttonData.setHighScores(highScores);
	}

	/**
	 * initialises buttons on screen
	 */
	private void setupButtons() {
		clearHighScoresButton = new ClearHighScoresButton(buttonXLocation, 310, buttonWidth, buttonHeight, buttonData);
		root.getChildren().add(clearHighScoresButton);
		newGameButton = new NewGameButton(buttonXLocation, 350, buttonWidth, buttonHeight, buttonData);
		root.getChildren().add(newGameButton);
		loadButton = new LoadButton(buttonXLocation, 390, buttonWidth, buttonHeight, buttonData);
		root.getChildren().add(loadButton);
		saveButton = new SaveButton(buttonXLocation, 430, buttonWidth, buttonHeight, buttonData);
		root.getChildren().add(saveButton);
		replayButton = new ReplayButton(buttonXLocation, 470, buttonWidth, buttonHeight, buttonData);
		root.getChildren().add(replayButton);
		keyboardBindingButton = new KeyboardBindingButton(buttonXLocation, 510, buttonWidth, buttonHeight, buttonData);
		root.getChildren().add(keyboardBindingButton);
		toggleVolumeButton = new ToggleVolumeButton(buttonXLocation, 550, buttonWidth, buttonHeight, buttonData);
		root.getChildren().add(toggleVolumeButton);
	}

	@Override
	public void playGame(String file) {
		root.getChildren().remove(gameDisplay);
		root.getChildren().remove((Node) hud);
		root.getChildren().remove(highScores.getScores());
		//engine.close();
		engine = new Engine(file);
		keyInputDictionary.setGame(engine);
		currentGameName = gameDescriptionProvider.getGameName(file);
		buttonData.setCurrentGameName(currentGameName);
		mostRecentFile = file;
		buttonData.setMostRecentFile(mostRecentFile);
		gameDisplay = engine.getDisplay();
		gameDisplay.setWidth(900);
		gameDisplay.setHeight(590);
		gameDisplay.setLayoutX(30);
		gameDisplay.setLayoutY(30);
		
		myScene.setOnKeyPressed(e -> engine.handleKeyInput(e.getCode()));
		//myScene.setOnMouseClicked(e -> engine.handleMouseInput(e.getX(), e.getY())); 
		
		// gameDisplay.setPrefSize(900, 590);
		// gameDisplay.setStyle("-fx-background-color: white;");
		hud = new ConcreteHUD(currentGameName);
		highScores = new ConcreteHighScores(currentGameName);
		//myScene.setOnKeyPressed(e -> keyInputDictionary.handleAction(e.getCode()));
		
		root.getChildren().add(gameDisplay);
		root.getChildren().add((Node) hud);
		root.getChildren().add(highScores.getScores());
		setupButtons();

	}
	@Override
	public Scene getScene() {
		return myScene;
	}

	@Override
	public void toggleMusic() {
		musicOn = !musicOn;

	}

	@Override
	public Boolean getMusicOn() {
		return musicOn;
	}

	public void closeEngine() {
		//engine.close();
		
	}
	
	

}
