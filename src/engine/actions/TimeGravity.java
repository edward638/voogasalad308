package engine.actions;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.Gravity;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;
import engine.events.gameevents.GameEvent;

public class TimeGravity implements Action{

	@Override
	public void act(ElementEvent event, GameElement element) {
		TimeEvent te = (TimeEvent) event;
		Gravity b = (Gravity) element.getBehavior(Gravity.class);
		b.experienceGravity(te.getTime());
	}
	
}
