package engine.actions;

import engine.GameElement;
import engine.behaviors.TimeRoutine2;
import engine.events.elementevents.ElementEvent;

public class TimeCreateGameElement implements Action {

	@Override
	public void act(ElementEvent e, GameElement ge) {
		TimeRoutine2 tr = (TimeRoutine2) ge.getBehavior(TimeRoutine2.class);
		tr.createGameElementEveryNSteps();
	}
}
