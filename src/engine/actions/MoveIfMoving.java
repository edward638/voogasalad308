package engine.actions;

import engine.GameElement;
import engine.behaviors.MandatoryBehavior;
import engine.behaviors.Movable;
import engine.behaviors.TrackMainCharacter;
import engine.events.elementevents.ElementEvent;

public class MoveIfMoving implements Action{

	@Override
	public void act(ElementEvent event, GameElement element) {	
		TrackMainCharacter tmc = (TrackMainCharacter) element.getBehavior(TrackMainCharacter.class);
		tmc.moveIfMoving();
	}
	
}
