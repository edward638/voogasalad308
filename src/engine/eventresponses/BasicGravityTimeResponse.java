package engine.eventresponses;

import engine.GameElement;
import engine.behaviors.Gravity;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;
import engine.events.gameevents.GameEvent;

public class BasicGravityTimeResponse extends EventResponse {
	
	public BasicGravityTimeResponse() {
		super(TimeEvent.class);
	}

	@Override
	public GameEvent execute(ElementEvent event, GameElement element) {
		if (!isValidEvent(event)) { 
			return null;
		}
		TimeEvent te = (TimeEvent) event;
		Gravity b = (Gravity) element.getBehavior(Gravity.class);
		b.experienceGravity(te.getTime());
		return null;
	}

}
