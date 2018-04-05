package engine;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import engine.eventresponses.EventResponse;
import engine.events.ElementEvent;
import engine.events.GameEvent;

public class EventManager {
	
	private DisplayState displayState;
	
	public EventManager(DisplayState displayState) {
		this.displayState = displayState;
	}
	
	public void step(GameState gameState) {
		List<GameEvent> gameEvents = new LinkedList<GameEvent>();
		List<ElementEvent> elementEvents = new LinkedList<ElementEvent>();
		
		elementEvents.addAll(getKeyEvent(gameState));
		elementEvents.addAll(getMouseEvent(gameState));
		elementEvents.addAll(getTimeEvent(gameState));
		elementEvents.addAll(getCollisionEvent(gameState));
		
		for (ElementEvent elementEvent : elementEvents) {
			gameEvents.addAll(gameState.updateElements(elementEvent));
		}
		
		updateDisplayState(gameState);
		
		/*List<GameEvent> gameEvents = collisionEvents.stream()
		.map(Object::gameState.updateElements)
		.collect(Collectors.toList());*/
		
	}
	
	private void updateDisplayState(GameState gameState) {
		for (GameElement gameElement : gameState) {
			Map<String, Object> elementProperties = gameElement.reportProperties();
			elementProperties.get()
		}
	}

	public List<ElementEvent> getKeyEvent(GameState gameState) {
		return null;
		
	}
	
	public List<ElementEvent> getMouseEvent(GameState gameState) {
		return null;
		
	}
	
	public List<ElementEvent> getTimeEvent(GameState gameState) {
		return null;
		
	}
	
	public List<ElementEvent> getCollisionEvent(GameState gameState) {
		List<ElementEvent> collisionEvents = gameState.detectCollisions();
		return collisionEvents;
		
	}
	
	public void handleElementEvents(GameState gameState, GameEvent gameEvent) {
		
	}


}
