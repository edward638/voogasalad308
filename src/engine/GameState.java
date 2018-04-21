package engine;

import java.util.ArrayList;
import java.util.List;

public class GameState{
	private List<GameElement> elements;
	private List<GameElement> newElements;
	private List<GameElement> removeElements;
	private double gameSpeed;
	private double gameTime;
	private GameMetaData metaData;
	
	public GameState() {
		//Talk to game data about reading info from file
		gameSpeed = 1;
		gameTime = 0;
		elements = new ArrayList<>();
		newElements = new ArrayList<>();
		removeElements = new ArrayList<>();
	}

	public void incrementGameTime(double timeElapsed) {
		gameTime+=timeElapsed;
	}
	
	public double getGameTime() {
		return gameTime;
	}
	
	protected double getGameSpeed() {
		return gameSpeed;
	}

	public void addGameElement(GameElement gameElement) {
		elements.add(gameElement);
		newElements.add(gameElement);
	}
	
	public void removeGameElement(GameElement gameElement) {
		elements.remove(gameElement);
		removeElements.add(gameElement);
	}
	
	public List<GameElement> getElements() {
		return elements;
	}
	
	public List<GameElement> getNewElements() {
		return newElements;
	}
	
	public List<GameElement> getRemoveElements() {
		return removeElements;
	}
	
	public GameMetaData getGameMetaData() {
		return metaData;
	}

	/**
	 * Used for level changes
	 * set the given game state to the new game state
	 * @param level
	 */
	public void setState(GameState newState) {
		elements = newState.getElements();
		
	}

}
