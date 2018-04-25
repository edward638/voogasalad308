package engine.actions;

import engine.GameElement;
import engine.behaviors.Portal;
import engine.events.elementevents.ElementEvent;

public class ChangeLevel implements Action{

	@Override
	public void act(ElementEvent e, GameElement ge) {
		Portal p = (Portal) ge.getBehavior(Portal.class);
		p.changeLevel();
		
	}
	
}
