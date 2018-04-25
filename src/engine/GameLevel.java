package engine;

import java.util.ArrayList;
import java.util.List;

public class GameLevel {
	private List<GamePart> gameParts;
	private final String gameLevelID;
	private GamePart currentGamePart;
	//private int levelTime;
	
	public GameLevel(String levelID) {
		this.gameLevelID = levelID;
		gameParts = new ArrayList<>();
	}
	
	protected GamePart getCurrentGamePart() {
		return currentGamePart;
	}
	
	protected void setCurrentGamePart(GamePart gp) {
		currentGamePart = gp;
	}
	
	protected void addGamePart(GamePart toAdd) {
		gameParts.add(toAdd);
	}

	protected String getGameLevelID() {
		return gameLevelID;
	}
}
