package engine.behaviors;

import java.util.List;

import engine.GameElement;
import engine.events.gameevents.ChangePartEvent;
import engine.events.gameevents.ResetLevelEvent;

public class Portal extends Behavior{
	private boolean active;
	private String partToChange;
	private List<Integer> resetLevels;
	public Portal (GameElement ge, boolean active, String partToChange, List<Integer> resetLevels) {
		super(ge);
		this.active = active;
		this.partToChange = partToChange;
		this.resetLevels = resetLevels;
	}
	
	public void runPortal() {
		if (active) {
			changeLevel();
			resetLevels();
		}
	}
	
	public void changeLevel() {
		getParent().addGameEvent(new ChangePartEvent(partToChange));
	}
	
	public void resetLevels() {
		for (int levelToReset : resetLevels) {
			getParent().addGameEvent(new ResetLevelEvent(levelToReset));
		}
	}
}
