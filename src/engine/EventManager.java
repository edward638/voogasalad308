package engine;

import java.util.List;

import engine.events.ElementEvent;
import engine.events.GameEvent;

public class EventManager {
	
	
	public void step(GameState gameState) {
		sendKeyEvent(gameState);
		sendMouseEvent(gameState);
		sendTimeEvent(gameState);
		sendCollisionEvent(gameState);
		
		
		
		ElementEvent timeStepEvent = new TimeStepEvent();
		
		List<GameEvent> gameEvents = gameState.updateElements(timeStepEvent);
	}
	
	public List<ElementEvent> sendKeyEvent(GameState gameState) {
		return null;
		
	}
	
	public List<ElementEvent> sendMouseEvent(GameState gameState) {
		return null;
		
	}
	
	public List<ElementEvent> sendTimeEvent(GameState gameState) {
		return null;
		
	}
	
	public List<ElementEvent> sendCollisionEvent(GameState gameState) {
		List<ElementEvent> collisionEvents = gameState.detectCollisions();
		for (ElementEvent collision : collisionEvents)
			gameState.updateElements(collision);
		return null;
		
	}
	
	public void handleElementEvents(GameState gameState, GameEvent gameEvent) {
		
	}


}
