package engine.eventresponses;

import engine.GameElement;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;

public abstract class EventResponse {
	
	private Class<?> respondingTo;
	
	public EventResponse(Class<?> responseTo) {
		respondingTo = responseTo;
	}

	public Class<?> getEventRespondingToType() {
		return respondingTo;
	}
	
	protected boolean isValidEvent(ElementEvent event) {
		return event.getClass() == respondingTo;
	}
	
	public abstract GameEvent execute (ElementEvent event, GameElement element);
}
