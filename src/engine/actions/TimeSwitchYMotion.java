package engine.actions;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.TimeRoutine2;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;

public class TimeSwitchYMotion implements Action{

	@Override
	public void act(ElementEvent event, GameElement element) {
		TimeRoutine2 tr = (TimeRoutine2) element.getBehavior(TimeRoutine2.class);
		tr.switchYEveryNSteps();
	}

}
