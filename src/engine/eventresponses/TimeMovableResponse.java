package engine.eventresponses;

import engine.GameElement;
import engine.behaviors.Movable;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;
import engine.events.gameevents.EmptyGameEvent;
import engine.events.gameevents.GameEvent;

public class TimeMovableResponse extends EventResponse {
	
	public TimeMovableResponse() {
		super(TimeEvent.class);
	}

	@Override
	public GameEvent execute(ElementEvent event, GameElement element) {
		if (!isValidEvent(event)) { 
			return new EmptyGameEvent();
		}
		TimeEvent te = (TimeEvent) event;
		Movable b = (Movable) element.getBehavior(Movable.class);
		b.move(te.getTime());
		return new EmptyGameEvent();
	}

}
