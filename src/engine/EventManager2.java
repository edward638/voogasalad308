package engine;

import java.util.ArrayList;
import java.util.List;

import engine.collision.CollisionManager;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;

public class EventManager2 {
	
	private GameState gameSate;
	private CollisionManager collisionManager;
	
	public EventManager2 (GameState state) {
		gameSate = state;
		collisionManager = new CollisionManager();
	}
	
	public void processElementEvent(ElementEvent ee) {
	
		List<GameEvent> gameEvents = new ArrayList<>();
		for (GameElement ge: gameSate.getCurrentGamePart().getElements()) {
			gameEvents.addAll(ge.processEvent(ee));
		}
		gameEvents.addAll(collisionManager.handleCollisions(gameSate.getCurrentGamePart()));
		gameEvents.stream().forEach(event -> processGameEvent(event));
		
	}
	
	private void processGameEvent(GameEvent gameEvent) {
		//System.out.println("GameEvent processed");
		gameEvent.execute(gameSate);
	}

	
	public GamePart getCurrentPart() {
		return gameSate.getCurrentGamePart();
	}
}