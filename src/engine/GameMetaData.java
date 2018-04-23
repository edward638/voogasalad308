package engine;

import java.util.List;

public class GameMetaData {
	List<GameState> levels;
	int levelNumber;
	GameState currentLevel;
	String gameName;
	public GameMetaData(List<GameState> levels, int levelNumber, GameState currentLevel, String gameName) {
		this.levels = levels;
		this.levelNumber = levelNumber;
		this.currentLevel = currentLevel;
		this.gameName = gameName;
	}
	
	public GameState getCurrentLevel() {
		return currentLevel;
	}
	
	
	public String getGameName() {
		return gameName;
	}
	
	public GameState getLevel(int levelnum) {
		return levels.get(levelnum);
	}
}
