package gamePlayer.buttons;

import engine.EngineInterface;
import gamePlayer.GamePlayer;
import gamePlayer.Username;
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
	private String mostRecentFile;
	private KeyInputDictionary keyBindingMap;
	private EngineInterface engine;
	private VolumeSlider volumeSlider;
	private Username username;

	public ConcreteButtonData(Stage stage, GamePlayer gamePlayer, VolumeSlider volumeSlider, Group root,
			KeyInputDictionary keyInputDictionary, Username username) {
		this.stage = stage;
		this.gamePlayer = gamePlayer;
		this.root = root;
		this.volumeSlider = volumeSlider;
		this.username = username;
		keyBindingMap = keyInputDictionary;
	}

	@Override
	public void playGame(String file, boolean isNewGame) {
		gamePlayer.playGame(file, isNewGame);
	}

	public void setHighScores(HighScores highScores) {
		this.highScores = highScores;
	}

	@Override
	public void clearHighScores() {
		highScores.clear();
	}

	public void setMostRecentFile(String file) {
		mostRecentFile = file;
	}

	@Override
	public Stage getStage() {
		return stage;
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

	@Override
	public void changeUsername(String newName) {
		username.changeName(newName);
	}

	@Override
	public void saveGame() {
		engine.save();
	}

}
