package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import engine.behaviors.Killable;
import engine.behaviors.MainCharacter;
import engine.behaviors.MandatoryBehavior;

public class GameState{
	private List<GameElement> elements;
	private List<GameElement> newElements;
	private List<GameElement> removeElements;
	private double gameSpeed;
	private double gameTime;
	private GameMetaData metaData;
	
	protected String gameName = "enginetestmario";
	
	public GameState(GameMetaData metaData) {
		//Talk to game data about reading info from file
		gameSpeed = 1;
		gameTime = 0;
		elements = new ArrayList<>();
		newElements = new ArrayList<>();
		removeElements = new ArrayList<>();
		this.metaData = metaData;
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
		if (!elements.contains(gameElement)) {
			elements.add(gameElement);
		}
		if (!newElements.contains(gameElement)) {
			newElements.add(gameElement);
		}
		
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
		if (elements.size()==0) {
			elements.addAll(newState.getElements());
			return;
		}
		List<GameElement> oldMainCharacters = new ArrayList<GameElement>();
		oldMainCharacters.addAll(updateMainCharacters(getMainCharacters(), newState.getMainCharacters()));
		ArrayList<GameElement> toRemove = new ArrayList<GameElement>();
		for (GameElement e: elements) {
			if (newState.getElements().contains(e) || e.hasBehavior(MainCharacter.class)) {
				if (e.hasBehavior(MainCharacter.class)) {
					for (GameElement mc: newState.getMainCharacters()) {
						if (mc.getIdentifier().equals(e.getIdentifier())) {
							e.setPosition(mc.getPosition());
						}
					}
				}
			}
			else {
				toRemove.add(e);
			}
		}
		elements.removeAll(toRemove);
		removeElements.addAll(toRemove);
		for (GameElement e: newState.getElements()) {
			if (!elements.contains(e) && !e.hasBehavior(MainCharacter.class)) {
				addGameElement(e);
			}
		}
	}
	
	public void resetGame(GameState newState) {
//		ArrayList<GameElement> toRemove = new ArrayList<GameElement>();
//		toRemove.addAll(elements);
//		for (GameElement e: toRemove) {
//			removeGameElement(e);
//		}
//		for (GameElement e: newState.getElements()) {
//			addGameElement(e);
//		}
		ArrayList<GameElement> toRemove = new ArrayList<GameElement>();
		for (GameElement e: elements) {
			if (newState.getElements().contains(e) || e.hasBehavior(MainCharacter.class)) {
				if (e.hasBehavior(MainCharacter.class)) {
					for (GameElement mc: newState.getMainCharacters()) {
						if (mc.getIdentifier().equals(e.getIdentifier())) {	
							e.setPosition(mc.getPosition());
						}
					}
				}
			}
			else {
				toRemove.add(e);
			}
		}
		elements.removeAll(toRemove);
		removeElements.addAll(toRemove);
		for (GameElement e: newState.getElements()) {
			if (!elements.contains(e) && !e.hasBehavior(MainCharacter.class)) {
				addGameElement(e);
			}
		}
	}
	
	public List<GameElement> getMainCharacters() {
		System.out.println("Total amt of elements: "  + elements.size());
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
	
	private void replaceMainCharacters(List<GameElement> newMainCharacters) {
		removeElements.addAll(getMainCharacters());
		elements.removeAll(getMainCharacters());
		elements.addAll(newMainCharacters);
		newElements.addAll(newMainCharacters);
	}

	
	public void removeAllElements() {
		removeElements.addAll(elements);
		elements.removeAll(elements);	
	}
	
	public void addAllElements(List<GameElement> elementsToAdd) {
		for (GameElement e: elementsToAdd) {
			newElements.add(e);
			elements.add(e);
			
		}
	}
	
	
	
	

}
