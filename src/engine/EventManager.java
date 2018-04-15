package engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import engine.behaviors.MandatoryBehavior;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;
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
//		updateDisplayState();
		return gameState;
	}
	
	/**
	 * Propogate ElementEvents to be executed by all elements
	 * 
	 * @param elementEvents 	List of element events to be propogated
	 * @param gameState			GameState, which contains a list of GameElements
	 * @return					An updated GameState
	 */
	private GameState propogateElementEvents(List<ElementEvent> elementEvents, GameState gameState) {
		List<GameEvent> gameEvents = new ArrayList<>();
		for (GameElement ge: gameState.getElements()) {
			for (ElementEvent ee: elementEvents) {
				gameEvents.addAll(ge.processEvent(ee));
			}
		}
//		List<GameEvent> gameEvents = gameState.getElements().stream()
//				.map(c -> c.processEvent(event))
//				.flatMap(List::stream)
//				.collect(Collectors.toList());
		gameState = handleGameEvents(gameState, gameEvents);
		return gameState;
	}
	
	/*private List<GameEvent> deliverElementEvent(ElementEvent elementEvent, GameState gameState) {
		return gameState.getElements().parallelStream()
				.map(c -> c.processEvent(elementEvent))
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}*/
	
	/**
	 * Finds all collisions between elements within a GameState, and returns these collisions as element events.
	 * 
	 * @param gameState 	
	 * @return				List of ElementEvents containing the collision information
	 */
	public List<ElementEvent> getCollisionEvents(GameState gameState) {
		List<ElementEvent> collisionEvents = new ArrayList<ElementEvent>();
		for (int i = 0; i < gameState.getElements().size(); i++) {
			MandatoryBehavior a = (MandatoryBehavior) gameState.getElements().get(i).getBehavior(MandatoryBehavior.class);
			
			for (int j = i+1; j < gameState.getElements().size(); j++) {
				MandatoryBehavior b = (MandatoryBehavior) gameState.getElements().get(j).getBehavior(MandatoryBehavior.class);
				if (a.getShape().getBoundsInLocal().intersects(b.getShape().getBoundsInLocal())) {
					Shape intersect = Shape.intersect(a.getShape(), b.getShape());
					collisionEvents.add(new CollisionEvent(gameState.getElements().get(i), gameState.getElements().get(j)));
				}
			}
		}
		
		return collisionEvents;
		
	}
	
	
	public GameState handleGameEvents(GameState gameState, List<GameEvent> gameEvents) {
		gameEvents.stream().forEach(c-> c.execute(gameState));
		return gameState;
	}


}
