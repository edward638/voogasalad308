package engine.eventresponses;

import engine.GameElement;
import engine.events.ElementEvent;

public abstract class EventResponse {
	public abstract void execute (ElementEvent event, GameElement element);
}
