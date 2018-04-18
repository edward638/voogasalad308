package engine.actions;

import engine.GameElement;
import engine.behaviors.TimeRoutine;
import engine.events.elementevents.ElementEvent;

public class TimeSwitchYMotion implements Action{

	@Override
	public void act(ElementEvent event, GameElement element) {
		TimeRoutine tr = (TimeRoutine) element.getBehavior(TimeRoutine.class);
		tr.switchYEveryNSteps();
	}

}
