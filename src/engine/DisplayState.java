package engine;

import java.util.ArrayList;
import java.util.List;

import data.ImageManager;

public class DisplayState {
	private GameState gameState;
	protected List<ImageElement> activeElements;
	protected List<ImageElement> newElements;
	protected List<ImageElement> removeElements;
	
	private String gameName;
	
//	public DisplayState(String gameName) {
//		this.gameName = gameName;
//		activeElements = new ArrayList<>();
//		newElements = new ArrayList<>();
//		removeElements = new ArrayList<>();
//	}
	
	public DisplayState (String gameName, GameState gameState) {
		this.gameName = gameName;
		activeElements = new ArrayList<>();
		newElements = new ArrayList<>();
		removeElements = new ArrayList<>();
		for (GameElement e : gameState.getElements()) {
			addNewElement(e);
		}
	}
	
	public void update(GameState updatedGameState) {
		for (GameElement e : updatedGameState.getNewElements()) {
			addNewElement(e);
		}
		updatedGameState.getNewElements().clear();
		for (GameElement e : updatedGameState.getRemoveElements()) {
			removeElement(e);
		}
		updatedGameState.getRemoveElements().clear();
	}
	
	public void addNewElement(GameElement element) {
		if (!activeElements.stream().anyMatch(c -> c.getReference() == element)) {
			ImageElement imageElement = new ImageElement(element, new ImageManager(gameName));
			newElements.add(imageElement);
			activeElements.add(imageElement);
		}
	}
	
	protected void removeElement(GameElement element) {
		for (ImageElement i : activeElements) {
			if (i.getReference() == element) {
				removeElements.add(i);
			}
		}
		for (ImageElement i : removeElements) {
			activeElements.remove(i);
		}
		
		//activeElements.stream().filter(c -> c.getReference() == element).map(c -> activeElements.remove(c));
		//activeElements.stream().filter(c -> c.getReference() == element).map(c -> removeElements.add(c));
	}

	protected void updateImageElements(List<Double> mainCharacterLocation) {
		for (ImageElement imageElement : activeElements) {
			imageElement.updateStateWithOffSet(mainCharacterLocation);
		}
	}
}
