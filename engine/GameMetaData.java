package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameMetaData {
	public GameState activeGameState;
	public static final int STARTING_LEVEL = 0;
	private List<GameState> levels;
	private GameState currentLevel;
	private int currentLevelNumber;
	private String gameName;

	public GameMetaData(List<GameState> levels, GameState currentLevel, int currentLevelNumber) {
		this.levels = levels;
		this.currentLevel = currentLevel;
		this.currentLevelNumber = currentLevelNumber;
	}
	
	
	
	
	public GameState getLevel(int level) {
		return levels.get(level);
	}
	
	
	
	/**
	 * for game player
	 * 
	 * @return
	 */
	public GameState getCurrentLevel() {
		return currentLevel;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	
	
}
