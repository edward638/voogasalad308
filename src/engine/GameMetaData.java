package engine;

import java.util.List;

public class GameMetaData {
	List<GameState> levels;
	int levelNumber;
	GameState currentLevel;
	public GameMetaData(List<GameState> levels, int levelNumber, GameState currentLevel) {
		this.levels = levels;
		this.levelNumber = levelNumber;
		this.currentLevel = currentLevel;
	}
	
	public GameState getCurrentLevel() {
		return currentLevel;
	}
	
	
	public String getGameName() {
		return null;
	}
	
	public GameState getLevel(int levelnum) {
		return levels.get(levelnum);
	}
}
