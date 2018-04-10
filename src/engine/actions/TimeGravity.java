package engine.actions;

import engine.GameElement;
import engine.behaviors.Gravity;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;

public class TimeGravity implements Action{

	@Override
	public void act(ElementEvent event, GameElement element) {
		// TODO Auto-generated method stub
		TimeEvent te = (TimeEvent) event;
		Gravity b = (Gravity) element.getBehavior(Gravity.class);
		b.experienceGravity(te.getTime());
	}
	
}
