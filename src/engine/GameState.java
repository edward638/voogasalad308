package engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import engine.events.ElementEvent;
import engine.events.GameEvent;
import javafx.scene.shape.Shape;

public class GameState implements Iterable<GameElement>{
	private HashSet<GameElement> 
	private HashSet<GameElement> elements;
	
	public void addGameElement(GameElement gameElement) {
		elements.add(gameElement);
	}
	
	public void removeGameElement(GameElement gameElement) {
		elements.remove(gameElement);
	}
	
	public List<Map<String, Integer>> getDisplayStates() {
		return null;
		
	}
	
	public List<GameEvent> updateElements(ElementEvent elementEvent) {
		LinkedList<GameEvent> gameEvents = new LinkedList<GameEvent>();
		for (GameElement e1 : elements) {
			gameEvents.addAll(e1.processEvent(elementEvent));
		}
		return null;
	}

	@Override
	public Iterator<GameElement> iterator() {
		return elements.iterator();
	}

}
