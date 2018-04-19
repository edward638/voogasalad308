package engine.actions;

import engine.GameElement;
import engine.behaviors.TimeRoutine;
import engine.events.elementevents.ElementEvent;

public class TimeCreateGameElement implements Action {

	@Override
	public void act(ElementEvent e, GameElement ge) {
		TimeRoutine tr = (TimeRoutine) ge.getBehavior(TimeRoutine.class);
		tr.createGameElementEveryNSteps();
	}
}
