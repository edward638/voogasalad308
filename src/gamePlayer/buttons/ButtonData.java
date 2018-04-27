package gamePlayer.buttons;

import engine.GameState;
import gamePlayer.keyBindings.KeyInputDictionary;
import javafx.scene.Node;
import javafx.stage.Stage;

public interface ButtonData {
	public void playGame(String file);
	public Stage getStage();
	public String getMostRecentFile();
	public String getCurrentGameName();
	public GameState getGameState();
	public void clearHighScores();
	public void addToRoot(Node node);
	public KeyInputDictionary getKeyBindings();
	public void toggleVolume();
	public void removeFromRoot(Node node);
	public void resumeGame();
	public void pauseGame();
	Boolean getVolumeStatus();
}
