package gamePlayer.buttons;

import engine.EngineInterface;
import engine.GameState;
import gamePlayer.GamePlayer;
import gamePlayer.VolumeSlider;
import gamePlayer.highScores.HighScores;
import gamePlayer.keyBindings.KeyInputDictionary;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ConcreteButtonData implements ButtonData {
	private Stage stage;
	private GamePlayer gamePlayer;
	private Group root;

	private HighScores highScores;
	private String currentGameName;
	private GameState gameState;
	private String mostRecentFile;
	private KeyInputDictionary keyBindingMap;
	private EngineInterface engine;
	private VolumeSlider volumeSlider;

	public ConcreteButtonData(Stage stage, GamePlayer gamePlayer, VolumeSlider volumeSlider,
			Group root, KeyInputDictionary keyInputDictionary) {
		this.stage = stage;
		this.gamePlayer = gamePlayer;
		this.root = root;
		this.volumeSlider = volumeSlider;
		keyBindingMap = keyInputDictionary;
	}

	@Override
	public void playGame(String file) {
		gamePlayer.playGame(file);
	}

	public void setCurrentGameName(String name) {
		this.currentGameName = name;
	}

	public void setHighScores(HighScores highScores) {
		this.highScores = highScores;
	}

	@Override
	public void clearHighScores() {
		highScores.clear();
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void setMostRecentFile(String file) {
		mostRecentFile = file;
	}

	@Override
	public Stage getStage() {
		return stage;
	}

	@Override
	public GameState getGameState() {
		return gameState;
	}

	@Override
	public String getCurrentGameName() {
		return currentGameName;
	}

	@Override
	public String getMostRecentFile() {
		return mostRecentFile;
	}

	@Override
	public void addToRoot(Node node) {
		root.getChildren().add(node);
	}

	@Override
	public KeyInputDictionary getKeyBindings() {
		return keyBindingMap;
	}

	@Override
	public void removeFromRoot(Node node) {
		root.getChildren().remove(node);

	}

	@Override
	public void toggleVolume() {
		volumeSlider.toggleMusic();
	}

	@Override
	public Boolean getVolumeStatus() {
		return volumeSlider.getMusicOn();
	}

	public void addEngine(EngineInterface engine) {
		this.engine = engine;
	}

	@Override
	public void resumeGame() {
		if (engine != null)
			engine.play();
	}

	@Override
	public void pauseGame() {
		if (engine != null)
			engine.pause();
	}

}
