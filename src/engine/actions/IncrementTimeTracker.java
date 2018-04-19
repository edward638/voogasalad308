package engine.actions;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.TimeTracker;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;
import engine.events.gameevents.GameEvent;

public class IncrementTimeTracker implements Action{

	@Override
	public void act(ElementEvent e, GameElement ge) {
		ArrayList<GameEvent> events = new ArrayList<GameEvent>();
		TimeEvent te = (TimeEvent) e;
		TimeTracker tt = (TimeTracker) ge.getBehavior(TimeTracker.class);
		tt.setTimePassed(tt.getTimePassed() + te.getTime());
	}
	
}
