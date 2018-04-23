package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import engine.behaviors.MainCharacter;
import engine.behaviors.MandatoryBehavior;

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
		System.out.println(newState.toString());
		elements = newState.getElements();
//		List<GameElement> oldMainCharacters = getMainCharacters(elements);
//		elements.removeAll(elements);
//		List<GameElement> newMainCharacters = updateMainCharacters(oldMainCharacters,getMainCharacters(newState.getElements()));
//		elements.addAll(newState.getElements());
//		elements = replaceMainCharacters(elements, newMainCharacters);		
	}
	
	private List<GameElement> getMainCharacters(List<GameElement> elements) {
		return elements.stream()
				.filter(e -> e.hasBehavior(MainCharacter.class))
				.collect(Collectors.toList());
	}
	
	private List<GameElement> updateMainCharacters(List<GameElement> oldMainCharacters, List<GameElement> newMainCharacters) {
		for (GameElement omc: oldMainCharacters) {
			for (GameElement nmc: newMainCharacters) {
				omc.setPosition(nmc.getPosition());
			}
		}
		return oldMainCharacters;
	}
	
	private List<GameElement> replaceMainCharacters(List<GameElement> tempNewState, List<GameElement> newMainCharacters) {
		List<GameElement> newState = tempNewState.stream().filter(e -> !e.hasBehavior(MainCharacter.class)).collect(Collectors.toList());
		newState.addAll(newMainCharacters);	
		return newState;
	}

	
	public void removeAllElements() {
		removeElements.addAll(elements);
		elements.removeAll(elements);
	}
	
	
	
	

}
