package engine.collision;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.GamePart;
import engine.behaviors.MandatoryBehavior;
import engine.events.elementevents.CollisionEvent;
import engine.events.gameevents.GameEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CollisionManager {
	
	public List<GameEvent> handleCollisions(GamePart gamePart) {
		List<GameEvent> returnEvents = new ArrayList<>();
		for (int i = 0; i < gamePart.getElements().size(); i++) {
			MandatoryBehavior a = (MandatoryBehavior) gamePart.getElements().get(i).getBehavior(MandatoryBehavior.class);
			
			for (int j = i+1; j < gamePart.getElements().size(); j++) {
				MandatoryBehavior b = (MandatoryBehavior) gamePart.getElements().get(j).getBehavior(MandatoryBehavior.class);
				if (a.getShape().getBoundsInLocal().intersects(b.getShape().getBoundsInLocal())) {
					Shape intersect = Shape.intersect(a.getShape(), b.getShape());
					if (((Path) intersect).getElements().size() != 0) {
						GameElement g1 = gamePart.getElements().get(i);
						GameElement g2 = gamePart.getElements().get(j);
						CollisionEvent collision = new CollisionEvent(g1, g2);
						returnEvents.addAll(g1.processEvent(collision));
						returnEvents.addAll(g2.processEvent(collision));
					}
				}
			}
		}
		return returnEvents;
	}
	
}
