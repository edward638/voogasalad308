package engine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import engine.eventresponses.EventResponse;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.KeyInputEvent;
import engine.events.elementevents.MouseInputEvent;
import engine.events.elementevents.TimeEvent;
import engine.events.gameevents.GameEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;

public class EventManager {
	
	public void processInputEvent(ElementEvent event, GameState gameState) {
		List<ElementEvent> elementEvents = new LinkedList<ElementEvent>();
		elementEvents.add(event);
		gameState = propogateElementEvents(elementEvents, gameState);
		gameState = processCollisionEvents(gameState);
		
	}
	
	public GameState processCollisionEvents(GameState gameState) {
		List<ElementEvent> elementEvents = new LinkedList<ElementEvent>();
		elementEvents.addAll(getCollisionEvents(gameState));
		gameState = propogateElementEvents(elementEvents, gameState);
		updateDisplayState();
		return gameState;
		
		
	}
	
	private GameState propogateElementEvents(List<ElementEvent> elementEvents, GameState gameState) {
		List<GameEvent> gameEvents = elementEvents.stream()
				.map(c -> deliverElementEvent(c, gameState))
				.flatMap(List::stream)
				.collect(Collectors.toList());
		gameState = handleGameEvents(gameState, gameEvents);
		return gameState;
	}
	
	private List<GameEvent> deliverElementEvent(ElementEvent elementEvent, GameState gameState) {
		return gameState.getElements().parallelStream()
				.map(c -> c.processEvent(elementEvent))
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}
	
	public List<ElementEvent> getCollisionEvents(GameState gameState) {
		List<ElementEvent> collisionEvents = new ArrayList<ElementEvent>();
		for (int i = 0; i < gameState.getElements().size(); i++) {
			BasicElementBehavior a = (BasicElementBehavior) gameState.getElements().get(i).getBehavior(BasicElementBehavior.class);
			
			for (int j = i+1; j < gameState.getElements().size(); j++) {
				BasicElementBehavior b = (BasicElementBehavior) gameState.getElements().get(j).getBehavior(BasicElementBehavior.class);
				if (a.getShape().getBoundsInLocal.intersects(b.getShape().getBoundsInLocal)) {
					Shape intersect = Shape.intersect(a.getShape(), b.getShape());
					collisionEvents.add(new CollisionEvent(gameState.getElements().get(i), gameState.getElements().get(j)));
				}
			}
		}
		
		return collisionEvents;
		
	}
	
	public GameState handleGameEvents(GameState gameState, List<GameEvent> gameEvents) {
		gameEvents.stream().map(c-> c.execute(gameState));
		return gameState;
	}


}
