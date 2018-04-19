package engine.actions;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.MovableCharacter;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;

public class Jump implements Action{

	@Override
	public void act(ElementEvent e, GameElement ge) {
		System.out.println(1);
		ArrayList<GameEvent> events = new ArrayList<GameEvent>();
		MovableCharacter mov = (MovableCharacter) ge.getBehavior(MovableCharacter.class);
		mov.jump();
	}
	
}
