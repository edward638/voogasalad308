package engine.events.elementevents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.GameElement;
import engine.behaviors.MandatoryBehavior;
import javafx.scene.shape.Shape;

public class CollisionEvent extends ElementEvent {
	
	private GameElement e1;
	private GameElement e2;
	private List<String> e1side;
	private List<String> e2side;
	
	public CollisionEvent(GameElement elem1, List<String> sides1, GameElement elem2, List<String> sides2) {
		e1 = elem1;
		e2 = elem2;
		e1side.addAll(sides1);
		e2side.addAll(sides2);
	}
	
	public CollisionEvent(GameElement elem1, GameElement elem2) {
		e1 = elem1;
		e2 = elem2;
		e1side = new ArrayList<String>();
		e2side = new ArrayList<String>();
		processCollisionSides(elem1, elem2);
	}
	
	public GameElement getOtherElement(GameElement notThisOne) {
		if (e1.equals(notThisOne)) {
			return e2;
		}
		return e1;
	}
	
	private void processCollisionSides(GameElement e1, GameElement e2) {
		MandatoryBehavior mand1 = (MandatoryBehavior) e1.getBehavior(MandatoryBehavior.class);
		MandatoryBehavior mand2 = (MandatoryBehavior) e2.getBehavior(MandatoryBehavior.class);
		Shape s1 = mand1.getShape();
		Shape s2 = mand2.getShape();
		Shape intersection = Shape.intersect(s1, s2);
		System.out.println(s1);
		System.out.println(intersection);
		e1side.add(getCollisionSide(s1, intersection));
		e2side.add(getCollisionSide(s2, intersection));
	}
	
	private String getCollisionSide(Shape elementShape, Shape intersection) {
		List<Double> centerElement = getCenter(elementShape);
		List<Double> centerIntersect = getCenter(intersection);
		List<Double> diffVector = Arrays.asList(centerIntersect.get(0) - centerElement.get(0), centerIntersect.get(1) - centerElement.get(1));
		if (Math.abs(diffVector.get(1)) > Math.abs(diffVector.get(0))) {
			if (diffVector.get(1) < 0) {
				return "bottom";
			} else {
				return "top";
			} 
		} 
		else {
			if (diffVector.get(0) < 0) {
				return "right";
			} else {
				return "left";
			}
		}
	}
	
	private List<Double> getCenter(Shape s) {
		List<Double> ret = new ArrayList<Double>();
		ret.add((s.getBoundsInLocal().getMaxX() + s.getBoundsInLocal().getMinX())/2);
		ret.add((s.getBoundsInLocal().getMaxY() + s.getBoundsInLocal().getMinY())/2);
		return ret;
	}

	public List<GameElement> getCollidedElements() {
		List<GameElement> ret = new ArrayList<>();
		ret.add(e1); ret.add(e2);
		return ret;
	}
	
	@Override
	public String toString() {
		return "Collision Event: " + e1 + " " + e2 + " collided.";
	}
	
	public boolean matchesEvent(ElementEvent other) {
		if (other instanceof CollisionEvent) {
			CollisionEvent otherCollision = (CollisionEvent) other;
			List<GameElement> collisionElements = otherCollision.getCollidedElements();
			if (collisionElements.contains(e1)) {
				collisionElements.remove(e1);
				GameElement incomingOne = e1;
				GameElement incomingTwo = collisionElements.get(0);
				
				if (incomingOne.getIdentifier().equals(incomingTwo.getIdentifier()) && 
						e1side.contains(otherCollision.e1side) &&
						e2side.contains(otherCollision.e2side)) {
					return true;
				}
				return true;
			}
		}
		return false;
	}
	
	public GameElement getCollidedWith(GameElement me) {
		if (!e1.equals(me)) {
			return e1;
		}
		return e2;
	}
}
