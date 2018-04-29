package engine.actions;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.EntrancePortal;
import engine.behaviors.Killable;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;
import engine.events.gameevents.GameEvent;

public class TimeDisablePortal implements Action{

	@Override
	public void act(ElementEvent event, GameElement element) {
		TimeEvent te = (TimeEvent) event;
		EntrancePortal ep = (EntrancePortal) element.getBehavior(EntrancePortal.class);
		ep.reduceDisableTime(te.getTime());
	}
}
