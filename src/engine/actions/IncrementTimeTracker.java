package engine.actions;

import engine.GameElement;
import engine.behaviors.TimeTracker;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;

public class IncrementTimeTracker implements Action{

	@Override
	public void act(ElementEvent e, GameElement ge) {
		TimeEvent te = (TimeEvent) e;
		TimeTracker tt = (TimeTracker) ge.getBehavior(TimeTracker.class);
		tt.setTimePassed(tt.getTimePassed() + te.getTime());
	}
	
}
