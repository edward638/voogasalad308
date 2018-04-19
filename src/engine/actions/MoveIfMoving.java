package engine.actions;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.MandatoryBehavior;
import engine.behaviors.Movable;
import engine.behaviors.TrackMainCharacter;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;

public class MoveIfMoving implements Action{

	@Override
	public void act(ElementEvent event, GameElement element) {	
		TrackMainCharacter tmc = (TrackMainCharacter) element.getBehavior(TrackMainCharacter.class);
		tmc.moveIfMoving();
	}
	
}
