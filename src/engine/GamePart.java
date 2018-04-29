package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import engine.behaviors.MainCharacter;

public class GamePart {
	private List<GameElement> gameElements;
	private final String gamePartID;
	private final String myLevelID;
	
	public GamePart(String gamePartID, String levelID) {
		gameElements = new ArrayList<>();
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
	}
	
	public void removeGameElement (GameElement ge) {
		gameElements.remove(ge);
	}
	
	public String getGamePartID () {
		return gamePartID;
	}
	
	public String getMyLevelID() {
		return myLevelID;
	}
	
	public List<GameElement> getElements() {
		return gameElements;
	}
	
	public GameElement getMainCharacter() {
		return gameElements.stream()
				.filter(e -> e.hasBehavior(MainCharacter.class))
				.collect(Collectors.toList()).get(0);
	}
	
	public String toString() {
		return "GamePart ID: " + gamePartID + ", Level ID: " + myLevelID;
	}
}
