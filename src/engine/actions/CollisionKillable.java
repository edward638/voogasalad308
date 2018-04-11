package engine.actions;

import engine.GameElement;
import engine.behaviors.Killable;
import engine.behaviors.MandatoryBehavior;
import engine.behaviors.Movable;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;

public class CollisionKillable implements Action {

	@Override
	public void act(ElementEvent event, GameElement element) {
		CollisionEvent ce = (CollisionEvent) event;
//		Killable b = (Killable) element.getBehavior(Killable.class);
		Movable b2 = (Movable) element.getBehavior(Movable.class);
		MandatoryBehavior b3 = (MandatoryBehavior) element.getBehavior(MandatoryBehavior.class);
//		b.loseLife();
		b2.setXVelocity(0.0);
		b2.setYVelocity(300.0);
		b3.setPosition(b3.getX(), b3.getY()-1);
		
	}

}
