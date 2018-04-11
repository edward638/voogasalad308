package gamePlayer.buttons;

import Data.Serializer;
import engine.GameState;
import gamePlayer.GamePlayer;
import gamePlayer.HighScores;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ConcreteButtonData implements ButtonData{
	private Stage stage;
	private GamePlayer gamePlayer;
	private Serializer serializer;
	private Group root;

	private HighScores highScores;
	private String currentGameName;
	private GameState gameState;
	private String mostRecentFile;

	public ConcreteButtonData(Stage stage, GamePlayer gamePlayer, Serializer serializer, Group root) {
		this.stage = stage;
		this.gamePlayer = gamePlayer;
		this.serializer = serializer;
		this.root = root;
	}
	
	@Override
	public void playGame(String file) {
		gamePlayer.DummyPlayGame(file);
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
	public Serializer getSerializer() {
		return serializer;
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

}
