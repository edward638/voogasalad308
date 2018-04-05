package engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import engine.events.ElementEvent;
import engine.events.GameEvent;
import javafx.scene.shape.Shape;

public class GameState {
	private HashSet<GameElement> 
	private HashSet<GameElement> elements;

	public List<ElementEvent> detectCollisions() {
		LinkedList<ElementEvent> events = new LinkedList<ElementEvent>();
		for (GameElement e1 : elements) {
			for (GameElement e2 : elements) {
				if (e1 != e2) {
					Shape intersect = Shape.intersect(e1.getImageView(), e2.getImageView());
					if (intersect.getBoundsInLocal().getWidth() != -1) {
						ElementEvent collision = new CollisionEvent(e1, e2);
						events.add(collision);
					}
				}
			}
		}

			
	}
	
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

}
