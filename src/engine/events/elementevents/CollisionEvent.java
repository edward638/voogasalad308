package engine.events.elementevents;

import engine.GameElement;

public class CollisionEvent extends ElementEvent {
	
	private GameElement e1;
	private GameElement e2;
	
	public Collision(Double t) {
		timeElapsed = t;
	}
	
	public Double getTime() {
		return timeElapsed;
	}
	
	@Override
	public String toString() {
		return "Time Event: " + timeElapsed + " seconds elapsed";
	}

}
