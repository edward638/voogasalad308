package engine.events.elementevents;

import engine.GameElement;

public class CollisionEvent extends ElementEvent {
	
	private GameElement e1;
	private GameElement e2;
	
	public CollisionEvent(GameElement elem1, GameElement elem2) {
		e1 = elem1;
		e2 = elem2;
	}

	
	@Override
	public String toString() {
		return "Collision Event: " + e1 + " " + e2 + " collided.";
	}

}
