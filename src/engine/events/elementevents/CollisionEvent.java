package engine.events.elementevents;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;

public class CollisionEvent extends ElementEvent {
	
	
	private GameElement e1;
	private GameElement e2;
	
	public CollisionEvent(GameElement elem1, GameElement elem2) {
		e1 = elem1;
		e2 = elem2;
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
				if (collisionElements.get(0).getClass() == e2.getClass()) {
					return true;
				}
			}
		}
		return false;
	}
}
