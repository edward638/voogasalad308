package engine;

import java.util.ArrayList;
import java.util.List;

import engine.behaviors.MandatoryBehavior;
import engine.collision.CollisionManager;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;
import javafx.scene.shape.Shape;

public class EventManager2 {
	
	private GameState gameState;
	private Engine engine;
	private CollisionManager collisionManager;
	
	public EventManager2 (GameState state, Engine eng) {
		gameState = state;
		engine = eng;
		collisionManager = new CollisionManager();
	}
	
	public void processElementEvent(ElementEvent ee) {
	
		List<GameEvent> gameEvents = new ArrayList<>();
		for (GameElement ge: gameState.getElements()) {
			gameEvents.addAll(ge.processEvent(ee));
		}
		
		collisionManager.handleCollisions(gameState);
		//handleCollisions();
		gameEvents.stream().forEach(event -> processGameEvent(event));
		
	}
	
	private void processGameEvent(GameEvent gameEvent) {
		gameEvent.execute(gameState, engine);
	}

	
	public GameState getCurrentState() {
		return gameState;
	}
}
