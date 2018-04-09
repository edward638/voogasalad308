package engine.eventresponses;

import engine.GameElement;
import engine.behaviors.Killable;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;
import engine.events.gameevents.EmptyGameEvent;
import engine.events.gameevents.GameEvent;
import engine.events.gameevents.RemoveGameElementEvent;

public class BasicKillableCollisionResponse extends EventResponse {
	public BasicKillableCollisionResponse() {
		super(CollisionEvent.class);
	}

	@Override
	public GameEvent execute(ElementEvent event, GameElement element) {
		if (!isValidEvent(event)) { 
			return new EmptyGameEvent();
		}
		CollisionEvent ce = (CollisionEvent) event;
		//Get second killing behavior parameter from second object and figure out how much health to remove
		Double health = ce.getCollider.damage();
		Killable b = (Killable) element.getBehavior(Killable.class);
		if (b.reduceHealth(health)) {
			return new RemoveGameElementEvent(element);
		}
		return new EmptyGameEvent();
		
	}
}
