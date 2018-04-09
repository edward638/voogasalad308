package engine.events.elementevents;

public abstract class ElementEvent {
	public abstract String toString();
	public abstract boolean matchesEvent(ElementEvent other);
}
