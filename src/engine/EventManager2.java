package engine;

import java.util.ArrayList;
import java.util.List;

import engine.collision.CollisionManager;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;

public class EventManager2 {
	
	private GamePart gamePart;
	private CollisionManager collisionManager;
	
	public EventManager2 (GamePart part) {
		gamePart = part;
		collisionManager = new CollisionManager();
	}
	
	public void processElementEvent(ElementEvent ee) {
	
		List<GameEvent> gameEvents = new ArrayList<>();
		for (GameElement ge: gamePart.getElements()) {
			gameEvents.addAll(ge.processEvent(ee));
		}
		gameEvents.addAll(collisionManager.handleCollisions(gamePart));
		gameEvents.stream().forEach(event -> processGameEvent(event));
		
	}
	
	private void processGameEvent(GameEvent gameEvent) {
		System.out.println("GameEvent processed");
		gameEvent.execute(gamePart);
	}

	
	public GamePart getCurrentPart() {
		return gamePart;
	}
}