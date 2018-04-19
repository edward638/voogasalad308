package engine.actions;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.Killable;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;
import engine.events.gameevents.GameEvent;

public class TimeKillable implements Action{

	@Override
	public void act(ElementEvent event, GameElement element) {
		TimeEvent te = (TimeEvent) event;
		Killable b = (Killable) element.getBehavior(Killable.class);
		b.decayHealth(te.getTime());
	}
}
