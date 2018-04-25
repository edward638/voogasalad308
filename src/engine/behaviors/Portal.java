package engine.behaviors;

import java.util.List;

import engine.events.gameevents.ChangeLevelEvent;
import engine.events.gameevents.ResetLevelEvent;

public class Portal {
	private boolean active;
	private Integer levelToChange;
	private List<Integer> resetLevels;
	public Portal (boolean active, int levelToChange, List<Integer> resetLevels) {
		this.active = active;
		this.levelToChange = levelToChange;
		this.resetLevels = resetLevels;
	}
	
	public void runPortal() {
		if (active) {
			changeLevel();
			resetLevels();
		}
	}
	
	public void changeLevel() {
		new ChangeLevelEvent(levelToChange);
	}
	
	public void resetLevels() {
		for (int levelToReset : resetLevels) {
			new ResetLevelEvent(levelToReset);
		}
	}
}
