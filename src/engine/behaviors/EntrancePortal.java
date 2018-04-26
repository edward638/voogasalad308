package engine.behaviors;

import java.util.List;

import engine.GameElement;
import engine.events.gameevents.ChangePartEvent;
import engine.events.gameevents.ResetLevelEvent;

public class EntrancePortal extends Behavior{
	private boolean active;
	private String partToChange;
	private List<String> resetLevels;
	private int portalID;
	public EntrancePortal (GameElement ge, boolean active, String partToChange, List<String> resetLevels, int portalID) {
		super(ge);
		this.active = active;
		this.partToChange = partToChange;
		this.resetLevels = resetLevels;
		this.portalID = portalID;
	}
	
	public void runPortal() {
		if (active) {
			changeLevel();
			resetLevels();
		}
	}
	
	public void changeLevel() {
		getParent().addGameEvent(new ChangePartEvent(partToChange, portalID));
	}
	
	public void resetLevels() {
		for (String levelToReset : resetLevels) {
			getParent().addGameEvent(new ResetLevelEvent(levelToReset));
		}
	}
}
