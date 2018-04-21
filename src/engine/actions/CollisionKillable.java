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

public class CollisionKillable implements Action {

	@Override
	public void act(ElementEvent event, GameElement element) {
		Killable b = (Killable) element.getBehavior(Killable.class);
		b.loseLife();
	}

}