package engine.actions;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.Killable;
import engine.behaviors.MandatoryBehavior;
import engine.behaviors.Movable;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;

public class CollisionGround implements Action {

	@Override
	public void act(ElementEvent event, GameElement element) {
		CollisionEvent ce = (CollisionEvent) event;
		Movable b2 = (Movable) element.getBehavior(Movable.class);
		MandatoryBehavior b3 = (MandatoryBehavior) element.getBehavior(MandatoryBehavior.class);
		if (b2.getXVelocity()>0) {
			b2.setXVelocity(b2.getXVelocity()-20);
		}
		if (b2.getXVelocity()<0) {
			b2.setXVelocity(b2.getXVelocity()+20);
		}
		b2.setYVelocity(20.0);
		b3.setPosition(b3.getX(), b3.getY()-1);
	}

}
