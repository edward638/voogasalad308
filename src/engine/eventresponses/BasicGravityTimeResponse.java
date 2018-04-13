package engine.eventresponses;

import java.util.ArrayList;
import java.util.List;

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
	public List<GameEvent> execute(ElementEvent event, GameElement element) {
		List<GameEvent> gameEventList = new ArrayList<GameEvent>();
		if (!isValidEvent(event)) { 
			return gameEventList;
		}
		TimeEvent te = (TimeEvent) event;
		Gravity b = (Gravity) element.getBehavior(Gravity.class);
		b.experienceGravity(te.getTime());
		return gameEventList;
	}

}
