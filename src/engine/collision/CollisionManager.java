package engine.collision;

import javafx.geometry.Bounds;
import engine.GameElement;
import engine.GameState;
import javafx.geometry.Point2D;
import engine.behaviors.MandatoryBehavior;
import engine.events.elementevents.CollisionEvent;
import javafx.scene.shape.Shape;

public class CollisionManager {
	
	public void handleCollisions(GameState gameState) {
		for (int i = 0; i < gameState.getElements().size(); i++) {
			MandatoryBehavior a = (MandatoryBehavior) gameState.getElements().get(i).getBehavior(MandatoryBehavior.class);
			
			for (int j = i+1; j < gameState.getElements().size(); j++) {
				MandatoryBehavior b = (MandatoryBehavior) gameState.getElements().get(j).getBehavior(MandatoryBehavior.class);
				if (a.getShape().getBoundsInLocal().intersects(b.getShape().getBoundsInLocal())) {
					Shape intersect = Shape.intersect(a.getShape(), b.getShape());
					//findCollisionDirection(a.getShape(), intersect);
					//findCollisionDirection(a.getShape(), intersect);
					GameElement g1 = gameState.getElements().get(i);
					GameElement g2 = gameState.getElements().get(j);
					g1.processEvent(new CollisionEvent(g1, g2));
					g2.processEvent(new CollisionEvent(g1, g2));
				}
			}
		}
	}
	
	private int findCollisionDirection(Shape element, Shape intersect) {
		Point2D intersectCenter = getCenter(intersect);
		Point2D elementCenter = getCenter(element);
		Point2D collisionVector = intersectCenter.subtract(getCenter(element));
		Point2D boundsVector = elementCenter.subtract(-element.getBoundsInLocal().getMinX(), -element.getBoundsInLocal().getMinY());
		Point2D referenceXVector = new Point2D(-1,0);
		double referenceAngle = referenceXVector.angle(boundsVector);
		double collisionAngle = collisionVector.angle(boundsVector);
		while (collisionAngle > 0) {
			
		}
		return 0;
	}
	
	private Point2D getCenter(Shape s) {
		Bounds b = s.getBoundsInLocal();
		return new Point2D((b.getMinX() + b.getMaxX())/2, (b.getMinY() + b.getMaxY())/2);
	}
}
