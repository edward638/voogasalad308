package engine;

import java.util.ArrayList;
import java.util.List;

import engine.behaviors.MandatoryBehavior;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;
import javafx.scene.shape.Shape;

public class EventManager2 {
	
	private GameState gameState;
	private Engine engine;
	
	public EventManager2 (GameState state, Engine engine) {
		gameState = state;
		this.engine = engine;
	}
	
	public void processElementEvent(ElementEvent ee) {
	
		List<GameEvent> gameEvents = new ArrayList<>();
		for (GameElement ge: gameState.getElements()) {
			gameEvents.addAll(ge.processEvent(ee));
		}
		
		handleCollisions();
		gameEvents.stream().forEach(event -> processGameEvent(event));
		
	}
	
	private void processGameEvent(GameEvent gameEvent) {
		gameEvent.execute(gameState, engine);
	}
	
	/*private void handleCollisions() {
		for (GameElement g1: gameState.getElements()) {
			for (GameElement g2:gameState.getElements()) {
				if (!(g1 == g2)) {
					MandatoryBehavior g1Mand = (MandatoryBehavior) g1.getBehavior(MandatoryBehavior.class);
					MandatoryBehavior g2Mand = (MandatoryBehavior) g2.getBehavior(MandatoryBehavior.class);
					if (Shape.intersect(g1Mand.getShape(), g2Mand.getShape()) != null) {
						g1.processEvent(new CollisionEvent(g1, g2));
						g2.processEvent(new CollisionEvent(g1, g2));
					}
				}
			}
		}
	}*/
	
	public void handleCollisions() {
		for (int i = 0; i < gameState.getElements().size(); i++) {
			MandatoryBehavior a = (MandatoryBehavior) gameState.getElements().get(i).getBehavior(MandatoryBehavior.class);
			
			for (int j = i+1; j < gameState.getElements().size(); j++) {
				MandatoryBehavior b = (MandatoryBehavior) gameState.getElements().get(j).getBehavior(MandatoryBehavior.class);
				if (a.getShape().getBoundsInLocal().intersects(b.getShape().getBoundsInLocal())) {
					Shape intersect = Shape.intersect(a.getShape(), b.getShape());
					GameElement g1 = gameState.getElements().get(i);
					GameElement g2 = gameState.getElements().get(j);
					g1.processEvent(new CollisionEvent(g1, g2));
					g2.processEvent(new CollisionEvent(g1, g2));
				}
			}
		}
	}
	
	public GameState getCurrentState() {
		return gameState;
	}
}
