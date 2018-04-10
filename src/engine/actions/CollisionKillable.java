package engine.actions;

import engine.GameElement;
import engine.behaviors.Killable;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;

public class CollisionKillable implements Action {

	@Override
	public void act(ElementEvent event, GameElement element) {
		CollisionEvent ce = (CollisionEvent) event;
		Killable b = (Killable) element.getBehavior(Killable.class);
		b.loseLife();
	}

}
