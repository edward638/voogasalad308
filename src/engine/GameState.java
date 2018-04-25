package engine;

import java.util.ArrayList;
import java.util.List;

public class GameState {
	private List<GameLevel> gameLevels;
	private GameLevel currentGameLevel;
	private final double gameSpeed = 1;
	private String gameName;

	public GameState(String gameName) {
		this.gameName = gameName;
		gameLevels = new ArrayList<>();
	}
	
	protected double getGameSpeed() {
		return gameSpeed;
	}
	
	protected GamePart getCurrentGamePart() {
		return currentGameLevel.getCurrentGamePart();
	}
	
	protected void setCurrentGameLevel(GameLevel gl) {
		currentGameLevel = gl;
	}
	
	protected GameLevel getCurrentGameLevel() {
		return currentGameLevel;
	}
	
	protected boolean containsLevel(String levelID) {
		return gameLevels.stream().anyMatch(gl -> gl.getGameLevelID().equals(levelID));
	}
	
	protected GameLevel getLevel(String levelID) {
		for (GameLevel gl : gameLevels) {
			if (gl.getGameLevelID().equals(levelID))
				return gl;
		}
		System.out.println("ERROR: Game Level Not Found");
		return null;
	}
	
	protected void addLevel(String levelID) {
		gameLevels.add(new GameLevel(levelID));
	}
}
