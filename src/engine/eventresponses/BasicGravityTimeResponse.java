package engine.eventresponses;

import engine.GameElement;
import engine.behaviors.Gravity;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;

public class BasicGravityTimeResponse extends EventResponse {
	
	public BasicGravityTimeResponse() {
		super(TimeEvent.class);
	}

	@Override
	public void execute(ElementEvent event, GameElement element) {
		if (!isValidEvent(event)) { 
			return;
		}
		TimeEvent te = (TimeEvent) event;
		Gravity b = (Gravity) element.getBehavior(Gravity.class);
		b.experienceGravity(te.getTime());
	}

}
