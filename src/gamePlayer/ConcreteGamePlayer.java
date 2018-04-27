package gamePlayer;

import java.util.Optional;
import java.util.ResourceBundle;

import data.GameDescriptionProvider;
import engine.Engine;
import engine.EngineInterface;
import engine.tests.ModelGameState2;
import gamePlayer.buttons.ConcreteButtonData;
import gamePlayer.buttons.LoadButton;
import gamePlayer.buttons.SaveButton;
import gamePlayer.buttons.ToggleVolumeButton;
import gamePlayer.highScores.ClearHighScoresButton;
import gamePlayer.highScores.ConcreteHighScores;
import gamePlayer.keyBindings.KeyInputDictionary;
import gamePlayer.keyBindings.KeyboardBindingButton;
import gamePlayer.buttons.NewGameButton;
import gamePlayer.buttons.PauseButton;
import gamePlayer.buttons.ReplayButton;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
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
	private Button pauseButton;
	private ConcreteButtonData buttonData;

	private HUD hud;
	private ConcreteHighScores highScores;
	private VolumeSlider volumeSlider;

	private EngineInterface engine;
	private String currentGameName;
	private GameDescriptionProvider gameDescriptionProvider;
	private String mostRecentFile;
	private KeyInputDictionary keyInputDictionary;
	private PlayerUpdater concretePlayerUpdater;

	private String userName;

	private ResourceBundle resources;

	private static final double SCREEN_HEIGHT = 650;
	private static final double SCREEN_WIDTH = 1250;
	private static final Paint BACKGROUND = Color.ANTIQUEWHITE;
	private static final int BUTTONXLOCATION = 970;
	private static final int BUTTONWIDTH = 235;
	private static final int BUTTONHEIGHT = 60;

	public ConcreteGamePlayer(Stage stage) {

		resources = ResourceBundle.getBundle("gamePlayer.resources/ConcreteGP");

		gameDescriptionProvider = new GameDescriptionProvider();

		root = new Group();
		myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
		myStage = stage;
		myStage.setScene(myScene);

		highScores = new ConcreteHighScores();
		root.getChildren().add(highScores.getScores());
		keyInputDictionary = new KeyInputDictionary(engine);

		volumeSlider = new VolumeSlider(buttonData, engine);
		buttonData = new ConcreteButtonData(stage, this, volumeSlider, root, keyInputDictionary);

		initialiseGUIElements();
		addGuiElementsToRoot();
		setupUserNameInput();
		setupUsernameText();

	}

	/**
	 * initialises buttons on screen
	 */
	private void initialiseGUIElements() {
		clearHighScoresButton = new ClearHighScoresButton(BUTTONXLOCATION,
				Integer.parseInt(resources.getString("clearHighScoresButtonY")), BUTTONWIDTH, BUTTONHEIGHT, buttonData);

		newGameButton = new NewGameButton(BUTTONXLOCATION, Integer.parseInt(resources.getString("newGameButtonY")), 110,
				BUTTONHEIGHT, buttonData);

		loadButton = new LoadButton(1095, Integer.parseInt(resources.getString("loadButtonY")), 110, BUTTONHEIGHT,
				buttonData);

		toggleVolumeButton = new ToggleVolumeButton(BUTTONXLOCATION,
				Integer.parseInt(resources.getString("toggleVolumeButtonY")), BUTTONWIDTH, BUTTONHEIGHT, buttonData);

		saveButton = new SaveButton(BUTTONXLOCATION, Integer.parseInt(resources.getString("saveButtonY")), BUTTONWIDTH,
				BUTTONHEIGHT, buttonData);

		replayButton = new ReplayButton(BUTTONXLOCATION, Integer.parseInt(resources.getString("replayButtonY")),
				BUTTONWIDTH, BUTTONHEIGHT, buttonData);

		keyboardBindingButton = new KeyboardBindingButton(BUTTONXLOCATION,
				Integer.parseInt(resources.getString("keybordBindingButtonY")), BUTTONWIDTH, BUTTONHEIGHT, buttonData);

		pauseButton = new PauseButton(BUTTONXLOCATION, Integer.parseInt(resources.getString("pauseButtonY")),
				BUTTONWIDTH, BUTTONHEIGHT, buttonData);

	}

	private void addGuiElementsToRoot() {
		root.getChildren().add(clearHighScoresButton);
		root.getChildren().add(newGameButton);
		root.getChildren().add(loadButton);
		root.getChildren().add(toggleVolumeButton);
		root.getChildren().add(saveButton);
		root.getChildren().add(replayButton);
		root.getChildren().add(keyboardBindingButton);
		root.getChildren().add(pauseButton);
		root.getChildren().add(volumeSlider.getVolumeText());
		root.getChildren().add(volumeSlider.getSlider());
	}

	@Override
	public void playGame(String file) {
		root.getChildren().remove(gameDisplay);
		root.getChildren().remove((Node) hud);
		root.getChildren().remove(highScores.getScores());

		if (engine != null) {
			engine.close();
		}
		engine = new Engine(new ModelGameState2().getState());
		buttonData.addEngine(engine);
		keyInputDictionary.setGame(engine);
		volumeSlider.setEngine(engine);
		currentGameName = gameDescriptionProvider.getGameName(file);
		hud = new ConcreteHUD(currentGameName);
		highScores = new ConcreteHighScores(file);
		buttonData.setHighScores(highScores);

		// set everything into gamemetadata and then pass only metadata into engine

		// concretePlayerUpdater = new ConcretePlayerUpdater(hud, highScores, userName);
		//
		// engine = new Engine(file, concretePlayerUpdater);
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

		root.getChildren().remove(pauseButton);
		pauseButton = new PauseButton(BUTTONXLOCATION, Integer.parseInt(resources.getString("pauseButtonY")),
				BUTTONWIDTH, BUTTONHEIGHT, buttonData);
		root.getChildren().add(pauseButton);
	}

	private void setupUsernameText() {
		Label nameText = new Label("User: " + userName);
		nameText.setLayoutX(970);
		nameText.setLayoutY(20);
		nameText.setFont(Font.font("Verdana", 20));
		root.getChildren().add(nameText);

	}

	private void setupUserNameInput() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Username");
		dialog.setHeaderText("Welcome to VoogaSalad!");
		dialog.setContentText("Please enter your username:");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> userName = result.get());
	}

	@Override
	public Scene getScene() {
		return myScene;
	}

	public void closeEngine() {
		if (engine == null) {
			myStage.close();
		} else {
			engine.close();
		}
	}

}
