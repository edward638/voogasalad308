package gamePlayer;

import java.io.File;
import java.util.ResourceBundle;

import data.GameDescriptionProvider;
import engine.Engine;
import engine.EngineInterface;
import engine.GameState;
import engine.tests.ModelGameState2;
//import engine.tests.ModelGameState2;
import gamePlayer.buttons.ConcreteButtonData;
import gamePlayer.buttons.LoadButton;
import gamePlayer.buttons.SaveButton;
import gamePlayer.buttons.ToggleVolumeButton;
import gamePlayer.highScores.ClearHighScoresButton;
import gamePlayer.highScores.ConcreteHighScores;
import gamePlayer.keyBindings.KeyInputDictionary;
import gamePlayer.keyBindings.KeyboardBindingButton;
import gamePlayer.buttons.NewGameButton;
import gamePlayer.buttons.ReplayButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
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

	private Button saveButton;
	private Button loadButton;
	private Button replayButton;
	private Button newGameButton;
	private Button clearHighScoresButton;
	private Button keyboardBindingButton;
	private Button toggleVolumeButton;
	private ConcreteButtonData buttonData;

	private HUD hud;
	private ConcreteHighScores highScores;

	private EngineInterface engine;
	private String currentGameName;
	private GameDescriptionProvider gameDescriptionProvider;
	private String mostRecentFile;
	private KeyInputDictionary keyInputDictionary;
	private PlayerUpdater concretePlayerUpdater;

	private ResourceBundle resources;

	private static final double SCREEN_HEIGHT = 650;
	private static final double SCREEN_WIDTH = 1250;
	private static final Paint BACKGROUND = Color.ANTIQUEWHITE;
	private static final int BUTTONXLOCATION = 970;
	private static final int BUTTONWIDTH = 235;
	private static final int BUTTONHEIGHT = 60;

	private static final double INITIALSOUNDLEVEL = 0.5;

	private boolean musicOn;
	private double soundLevel;

	public ConcreteGamePlayer(Stage stage) {

		resources = ResourceBundle.getBundle("gamePlayer.resources/ConcreteGP");

		gameDescriptionProvider = new GameDescriptionProvider();

		musicOn = true;
		soundLevel = INITIALSOUNDLEVEL;

		root = new Group();
		myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
		myStage = stage;
		myStage.setScene(myScene);

		highScores = new ConcreteHighScores();
		root.getChildren().add(highScores.getScores());
		keyInputDictionary = new KeyInputDictionary(engine);

		buttonData = new ConcreteButtonData(stage, this, gameDescriptionProvider, root, keyInputDictionary);
		setupButtons();
		setupVolumeSlider();
	}

	/*
	 * DO WE NEED OUR OWN CLASS FOR THIS??? I STARTED A CLASS BUT IDK IF WE REALLY
	 * NEED IT. NOTE: I DID NOT DO THE RESOURCE BUNDLE FOR THIS SHIT YET
	 */
	private void setupVolumeSlider() {
		Slider slider = new Slider(0, 1, INITIALSOUNDLEVEL);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				System.out.println(new_val.doubleValue());
				soundLevel = new_val.doubleValue();
				// engine.setVolume(soundLevel);
			}
		});
		root.getChildren().add(slider);

	}

	/**
	 * initialises buttons on screen
	 */
	private void setupButtons() {
		clearHighScoresButton = new ClearHighScoresButton(BUTTONXLOCATION,
				Integer.parseInt(resources.getString("clearHighScoresButtonY")), BUTTONWIDTH, BUTTONHEIGHT, buttonData);
		root.getChildren().add(clearHighScoresButton);
		newGameButton = new NewGameButton(BUTTONXLOCATION, Integer.parseInt(resources.getString("newGameButtonY")),
				BUTTONWIDTH, BUTTONHEIGHT, buttonData);
		root.getChildren().add(newGameButton);
		loadButton = new LoadButton(BUTTONXLOCATION, Integer.parseInt(resources.getString("loadButtonY")), BUTTONWIDTH,
				BUTTONHEIGHT, buttonData);
		root.getChildren().add(loadButton);
		saveButton = new SaveButton(BUTTONXLOCATION, Integer.parseInt(resources.getString("saveButtonY")), BUTTONWIDTH,
				BUTTONHEIGHT, buttonData);
		root.getChildren().add(saveButton);
		replayButton = new ReplayButton(BUTTONXLOCATION, Integer.parseInt(resources.getString("replayButtonY")),
				BUTTONWIDTH, BUTTONHEIGHT, buttonData);
		root.getChildren().add(replayButton);
		keyboardBindingButton = new KeyboardBindingButton(BUTTONXLOCATION,
				Integer.parseInt(resources.getString("keybordBindingButtonY")), BUTTONWIDTH, BUTTONHEIGHT, buttonData);
		root.getChildren().add(keyboardBindingButton);
		toggleVolumeButton = new ToggleVolumeButton(BUTTONXLOCATION,
				Integer.parseInt(resources.getString("toggleVolumeButtonY")), BUTTONWIDTH, BUTTONHEIGHT, buttonData);
		root.getChildren().add(toggleVolumeButton);
	}

	@Override
	public void playGame(String file) {
		root.getChildren().remove(gameDisplay);
		root.getChildren().remove((Node) hud);
		root.getChildren().remove(highScores.getScores());

		if (engine != null) {
			engine.close();
		}
		// engine = new Engine(file);

		engine = new Engine(new ModelGameState2().getState());
		keyInputDictionary.setGame(engine);
		currentGameName = gameDescriptionProvider.getGameName(file);
		hud = new ConcreteHUD(currentGameName);
		highScores = new ConcreteHighScores(file);
		buttonData.setHighScores(highScores);

		// set everything into gamemetadata and then pass only metadata into engine

		concretePlayerUpdater = new ConcretePlayerUpdater(hud, highScores, userName);

		engine = new Engine(file, concretePlayerUpdater);
		keyInputDictionary.setGame(engine);

		buttonData.setCurrentGameName(currentGameName);
		mostRecentFile = file;
		buttonData.setMostRecentFile(mostRecentFile);
		gameDisplay = engine.getDisplay();
		gameDisplay.setWidth(Integer.parseInt(resources.getString("gameDisplayWidth")));
		gameDisplay.setHeight(Integer.parseInt(resources.getString("gameDisplayHeight")));
		gameDisplay.setLayoutX(Integer.parseInt(resources.getString("gameDisplayX")));
		gameDisplay.setLayoutY(Integer.parseInt(resources.getString("gameDisplayY")));

		myScene.setOnKeyPressed(e -> keyInputDictionary.handleAction(e.getCode()));

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
		engine.close();
	}

}
