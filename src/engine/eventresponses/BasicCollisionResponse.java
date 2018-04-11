package engine.eventresponses;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.MandatoryBehavior;
import engine.behaviors.Movable;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;

public class BasicCollisionResponse extends EventResponse {
	public BasicCollisionResponse() {
		super(CollisionEvent.class);
	}

	@Override
	public List<GameEvent> execute(ElementEvent event, GameElement element) {
		List<GameEvent> gameEventList = new ArrayList<GameEvent>();
		if (!isValidEvent(event)) { 
			return gameEventList;
		}
		Movable m = (Movable) element.getBehavior(Movable.class);
		MandatoryBehavior mb = (MandatoryBehavior) element.getBehavior(MandatoryBehavior.class);
		m.setYVelocity(0.0);
		mb.setPosition(mb.getX(), mb.getY()+100);
		return gameEventList;
	}
}
