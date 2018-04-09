package engine.eventresponses;

import engine.GameElement;
import engine.behaviors.Movable;
import engine.events.ElementEvent;
import engine.events.TimeEvent;

public class BasicTimeResponse extends EventResponse {
	
	public BasicTimeResponse() {
		super(TimeEvent.class);
	}

	@Override
	public void execute(ElementEvent event, GameElement element) {
		if (!isValidEvent(event)) { 
			return;
		}
		TimeEvent te = (TimeEvent) event;
		Movable b = (Movable) element.getBehavior(Movable.class);
		b.move(te.getTime());
	}

}
