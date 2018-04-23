package engine.behaviors;

import engine.GameElement;
import engine.events.gameevents.ChangeLevelEvent;
import engine.events.gameevents.RemoveGameElementEvent;

public class CanChangeLevel extends Behavior {
	
	private int toLevel;
	private boolean active;
	public CanChangeLevel(GameElement ge, int toLevel, boolean active) {
		super(ge);
		this.toLevel = toLevel;
		this.active = active;
	}
	
	public void changeLevel() {
		getParent().addGameEvent(new ChangeLevelEvent(toLevel));
	}
	
	
	
	

}
