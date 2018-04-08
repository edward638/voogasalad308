package engine;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import engine.eventresponses.EventResponse;
import engine.events.ElementEvent;
import engine.events.GameEvent;
import engine.events.TimeEvent;

public class EventManager {
	
	private DisplayState displayState;
	
	public EventManager(DisplayState displayState) {
		this.displayState = displayState;
	}
	
	public void step(GameState gameState) {
		//List<GameEvent> gameEvents = new LinkedList<GameEvent>();
		List<ElementEvent> elementEvents = new LinkedList<ElementEvent>();
		
		elementEvents.addAll(getKeyEvent(gameState));
		elementEvents.addAll(getMouseEvent(gameState));
		elementEvents.addAll(getTimeEvent(gameState));
		elementEvents.addAll(getCollisionEvent(gameState));
		
		gameState.updateElements(elementEvents);
		
		/*for (ElementEvent elementEvent : elementEvents) {
			gameEvents.addAll(gameState.updateElements(elementEvent));
		}*/
		
		updateDisplayState();
		
		List<List<GameEvent>> gameEvents = elementEvents.stream()
				.map(gameState::updateElements)
				.collect(Collectors.toList());
		
	}
	
	private void updateDisplayState() {
		displayState.updateImageElements();
	}

	public List<ElementEvent> getKeyEvent(GameState gameState) {
		
		return null;
		
	}
	
	public List<ElementEvent> getMouseEvent(GameState gameState) {
		return null;
		
	}
	
	/**
	 * @param gameState 
	 * @returns a list of timeEvents which are sent to every object which represent the incrementing/speed of the level. 
	 */
	public List<ElementEvent> getTimeEvent(GameState gameState) {
		List<ElementEvent> timeEvents = new LinkedList<ElementEvent>();
		double stepRate = Double.parseDouble(gameState.getGameProperties().get(gameSpeed));
		for (GameElement gameElement : gameState) {
			timeEvents.add(new TimeEvent(stepRate));
		}
		return timeEvents;
		// or return new TimeEvent(stepRate); ...depends on our code design. 
	}
	
	public List<ElementEvent> getCollisionEvent(GameState gameState) {
		List<ElementEvent> collisionEvents = displayState.detectCollisions();
		return collisionEvents;
		
	}
	
	public void handleElementEvents(GameState gameState, GameEvent gameEvent) {
		
	}


}
