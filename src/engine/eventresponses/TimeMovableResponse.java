package engine.eventresponses;

import java.util.ArrayList;
import java.util.List;

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
	public List<GameEvent> execute(ElementEvent event, GameElement element) {
		List<GameEvent> gameEventList = new ArrayList<GameEvent>();
		if (!isValidEvent(event)) { 
			return gameEventList;
		}
		TimeEvent te = (TimeEvent) event;
		Movable b = (Movable) element.getBehavior(Movable.class);
		b.move(te.getTime());
		return gameEventList;
	}

}
