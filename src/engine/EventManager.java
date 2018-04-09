package engine;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import engine.eventresponses.EventResponse;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.KeyInputEvent;
import engine.events.elementevents.MouseInputEvent;
import engine.events.elementevents.TimeEvent;
import engine.events.gameevents.GameEvent;
import javafx.scene.input.KeyCode;

public class EventManager {
	
	private DisplayState displayState;
	public EventManager(DisplayState displayState) {
		this.displayState = displayState;
	}
	
	public void processInputEvent(ElementEvent event, GameState gameState) {
		List<ElementEvent> elementEvents = new LinkedList<ElementEvent>();
		elementEvents.add(event);
		gameState = updateGameState(elementEvents, gameState);
		gameState = processCollisionEvents(gameState);
		
	}
	
	public GameState processCollisionEvents(GameState gameState) {
		List<ElementEvent> elementEvents = new LinkedList<ElementEvent>();
		elementEvents.addAll(getCollisionEvent(gameState));
		gameState = updateGameState(elementEvents, gameState);
		updateDisplayState();
		return gameState;
		
		
	}
	
	private GameState updateGameState(List<ElementEvent> elementEvents, GameState gameState) {
		List<GameEvent> gameEvents = elementEvents.stream()
				.map(gameState::updateElements)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		gameState = handleGameEvents(gameState, gameEvents);
		return gameState;
	}
	
	private void updateDisplayState() {
		displayState.updateImageElements();
	}

	
	public List<ElementEvent> getCollisionEvent(GameState gameState) {
		List<ElementEvent> collisionEvents = displayState.detectCollisions();
		return collisionEvents;
		
	}
	
	public GameState handleGameEvents(GameState gameState, List<GameEvent> gameEvents) {
		gameEvents.stream().map(c-> c.execute(gameState));
		return gameState;
	}


}
