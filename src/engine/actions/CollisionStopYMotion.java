package engine.actions;

import engine.GameElement;
import engine.behaviors.Movable;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;

public class CollisionStopYMotion implements Action{

	@Override
	public void act(ElementEvent e, GameElement ge) {
		CollisionEvent ce = (CollisionEvent) e;
		GameElement other = ce.getOtherElement(ge);
		if (other.hasBehavior(Movable.class)) {
			Movable otherMovable = (Movable) other.getBehavior(Movable.class);
			otherMovable.setYVelocity(0.0);
		}
		
		
	}

}
