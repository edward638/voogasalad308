package engine.actions;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.TimeRoutine;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;

public class TimeSwitchYMotion implements Action{

	@Override
	public void act(ElementEvent event, GameElement element) {
		TimeRoutine tr = (TimeRoutine) element.getBehavior(TimeRoutine.class);
		tr.switchYEveryNSteps();
	}

}
