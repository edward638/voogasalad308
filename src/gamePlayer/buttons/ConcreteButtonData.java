package gamePlayer.buttons;

import data.GameDescriptionProvider;
import data.Serializer;
import engine.GameState;
import gamePlayer.GamePlayer;
import gamePlayer.HighScores;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ConcreteButtonData implements ButtonData {
	private Stage stage;
	private GamePlayer gamePlayer;
	private GameDescriptionProvider gameDescriptionProvider;
	private Group root;

	private HighScores highScores;
	private String currentGameName;
	private GameState gameState;
	private String mostRecentFile;

	public ConcreteButtonData(Stage stage, GamePlayer gamePlayer, GameDescriptionProvider gameDescriptionProvider,
			Group root) {
		this.stage = stage;
		this.gamePlayer = gamePlayer;
		this.gameDescriptionProvider = gameDescriptionProvider;
		this.root = root;
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

	// @Override
	// public GameDescriptionProvider getGameDescriptionProvider() {
	// return gameDescriptionProvider;
	// }
	
	
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
