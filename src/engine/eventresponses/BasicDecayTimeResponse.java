package engine.eventresponses;

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
	public GameEvent execute(ElementEvent event, GameElement element) {
		if (!isValidEvent(event)) { 
			return new EmptyGameEvent();
		}
		TimeEvent te = (TimeEvent) event;
		Killable b = (Killable) element.getBehavior(Killable.class);
		if (b.decayHealth(te.getTime())) {
			return new RemoveGameElementEvent(element);
		}
		return new EmptyGameEvent();
	}
}
