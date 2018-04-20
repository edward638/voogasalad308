package engine.collision;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.GameState;
import engine.behaviors.MandatoryBehavior;
import engine.events.elementevents.CollisionEvent;
import engine.events.gameevents.GameEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CollisionManager {
	
	public List<GameEvent> handleCollisions(GameState gameState) {
		List<GameEvent> returnEvents = new ArrayList<>();
		for (int i = 0; i < gameState.getElements().size(); i++) {
			MandatoryBehavior a = (MandatoryBehavior) gameState.getElements().get(i).getBehavior(MandatoryBehavior.class);
			
			for (int j = i+1; j < gameState.getElements().size(); j++) {
				MandatoryBehavior b = (MandatoryBehavior) gameState.getElements().get(j).getBehavior(MandatoryBehavior.class);
				if (a.getShape().getBoundsInLocal().intersects(b.getShape().getBoundsInLocal())) {
					Shape intersect = Shape.intersect(a.getShape(), b.getShape());
					if (((Path) intersect).getElements().size() != 0) {
						GameElement g1 = gameState.getElements().get(i);
						GameElement g2 = gameState.getElements().get(j);
						CollisionEvent collision = new CollisionEvent(g1, g2);
						System.out.println(g1);
						System.out.println(g2);
						returnEvents.addAll(g1.processEvent(collision));
						returnEvents.addAll(g2.processEvent(collision));
					}
				}
			}
		}
		return returnEvents;
	}
	
}
