package engine;

import java.util.List;
import java.util.Map;

public class GameMetaData {
	List<GameState> levels;
	int currentLevelNumber;
	GameState currentLevel;
	String gameName;
	
	//Player myPlayerObject;
	
	public GameMetaData(List<GameState> levels, int currentLevelNumber, GameState currentLevel, String gameName) {
		this.levels = levels;
		this.currentLevelNumber = currentLevelNumber;
		this.currentLevel = currentLevel;
		this.gameName = gameName;
	}
	
	/*public void setPlayerReferences(Player p) {
		myPlayerObject = p;
	}
	
	public void updateMainCharacterInfo(Map<String, Object> properties) {
		properties.put("Level", currentLevelNumber);
		myPlayerObject.updateHUD(properties);
	}
	
	public void addHighScore(int score) {
		myPlayerObject.addHighScore(score);
	}*/
	
	public String getGameName() {
		return gameName;
	}
	
	public GameState getLevel(int levelNumber) {
		return levels.get(levelNumber);
	}
	
	public GameState getCurrentLevel() {
		return currentLevel;
	}
}
