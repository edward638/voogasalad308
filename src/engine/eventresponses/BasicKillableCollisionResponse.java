package engine.eventresponses;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.Killable;
import engine.behaviors.Killer;
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
	public List<GameEvent> execute(ElementEvent event, GameElement element1) {
		List<GameEvent> gameEventList = new ArrayList<GameEvent>();
		CollisionEvent ce = (CollisionEvent) event;
		gameEventList.addAll(damageEachOther(element1, ce.getCollidedWith(element1)));
		if (!isValidEvent(event)) { 
			return gameEventList;
		}
		
		return gameEventList;
		
	}
	
	private List<GameEvent> damageEachOther(GameElement g1, GameElement g2) {
		List<GameEvent> gameEventList = new ArrayList<GameEvent>();
		gameEventList.add(doDamage(g1, g2));
		gameEventList.add(doDamage(g2, g1));
		return gameEventList;
	}
	
	private GameEvent doDamage(GameElement g1, GameElement g2) {
		Double damagePower = ((Killer) g1.getBehavior(Killer.class)).getDamagePower();
		Killable target = (Killable) g2.getBehavior(Killable.class);
		if (target.reduceHealth(damagePower)) {
			return new RemoveGameElementEvent(g2);
		}
		return new EmptyGameEvent();
	}
	

}
