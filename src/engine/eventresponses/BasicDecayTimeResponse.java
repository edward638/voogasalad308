package engine.eventresponses;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.Killable;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;
import engine.events.gameevents.EmptyGameEvent;
import engine.events.gameevents.GameEvent;
import engine.events.gameevents.RemoveGameElementEvent;

/**
 * @author Gouttham Allows a GameElement to Decay over time.
 *
 */
public class BasicDecayTimeResponse extends EventResponse{
	public BasicDecayTimeResponse() {
		super(TimeEvent.class);
	}

	@Override
	public List<GameEvent> execute(ElementEvent event, GameElement element) {
		List<GameEvent> gameEventList = new ArrayList<GameEvent>();
		if (!isValidEvent(event)) { 
			return gameEventList;
		}
		TimeEvent te = (TimeEvent) event;
		Killable b = (Killable) element.getBehavior(Killable.class);
		if (b.decayHealth(te.getTime())) {
			gameEventList.add(new RemoveGameElementEvent(element));
			return gameEventList;
		}
		return gameEventList;
	}
}
