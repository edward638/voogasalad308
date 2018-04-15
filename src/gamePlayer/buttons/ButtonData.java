package gamePlayer.buttons;

import data.GameDescriptionProvider;
import data.Serializer;
import engine.GameState;
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
	// public GameDescriptionProvider getGameDescriptionProvider();
}
