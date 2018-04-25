package engine;

import java.util.ArrayList;
import java.util.List;
import engine.behaviors.MainCharacter;

public class GamePart {
	private List<GameElement> gameElements;
	private List<GameElement> toAdd;
	private List<GameElement> toRemove;
	private final String gamePartID;
	private final String myLevelID;
	
	public GamePart(String gamePartID, String levelID) {
		gameElements = new ArrayList<>();
		toAdd = new ArrayList<>();
		toRemove = new ArrayList<>();
		this.gamePartID = gamePartID;
		this.myLevelID = levelID;
	}
	
	protected boolean hasMainCharacter() {
		for (GameElement ge : gameElements) {
			if (ge.hasBehavior(MainCharacter.class)) {
				return true;
			}
		}
		return false;
	}
	
	public void addGameElement (GameElement ge) {
		gameElements.add(ge);
		toAdd.add(ge);
	}
	
	protected List<GameElement> getToAdd() {
		return toAdd;
	}
	
	protected void clearToAdd() {
		toAdd.clear();
	}
	
	public void removeGameElement (GameElement ge) {
		gameElements.remove(ge);
		toRemove.add(ge);
	}
	
	protected List<GameElement> getToRemove() {
		return toRemove;
	}
	
	protected void clearToRemove() {
		toRemove.clear();
	}
	
	protected String getGamePartID () {
		return gamePartID;
	}
	
	protected String getMyLevelID() {
		return myLevelID;
	}
	
	public List<GameElement> getElements() {
		return gameElements;
	}
}
