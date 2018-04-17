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
						returnEvents.addAll(g1.processEvent(collision));
						returnEvents.addAll(g2.processEvent(collision));
					}
				}
			}
		}
		return returnEvents;
	}
	
	/**
	 * Returns an integer representing the side of the collision
	 * 
	 * @param element 	shape of the element
	 * @param intersect	shape of the collision intersection
	 * @return -1 if no collision, otherwise from the left side and traveling cw around a 
	 * rectangle, 0, 1, 2, 3
	 */
	private Integer findCollisionDirection(Shape element, Shape intersect) {
		Point2D intersectCenter = getCenter(intersect);
		Point2D elementCenter = getCenter(element);
		/*Point2D collisionVector = intersectCenter.subtract(getCenter(element));
		Point2D boundsVector = elementCenter.subtract(-element.getBoundsInLocal().getMinX(), -element.getBoundsInLocal().getMinY());
		Point2D referenceXVector = new Point2D(-1,0);
		double referenceAngle = referenceXVector.angle(boundsVector);
		double collisionAngle = collisionVector.angle(boundsVector);
		while (collisionAngle > 0) {
			
		}
		return 0;*/
		double dx = elementCenter.getX() - intersectCenter.getX();
		double dy = elementCenter.getY() - intersectCenter.getY();
		if (Math.abs(dx/element.getBoundsInLocal().getWidth()) > Math.abs(dy/element.getBoundsInLocal().getWidth())) {
			return (int) Math.signum(dx) + 1;
		}
		else {
			return (int) Math.signum(dy) + 2;
		}
	}
	
	private Point2D getCenter(Shape s) {
		Bounds b = s.getBoundsInLocal();
		return new Point2D((b.getMinX() + b.getMaxX())/2, (b.getMinY() + b.getMaxY())/2);
	}
}
