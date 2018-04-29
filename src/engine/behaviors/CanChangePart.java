package engine.behaviors;

import engine.GameElement;
import engine.events.gameevents.ChangePartEvent;
import engine.events.gameevents.RemoveGameElementEvent;

public class CanChangePart extends Behavior {
	
	private String toPartID;
	private boolean active;
	
	public CanChangePart(GameElement ge, String toPartID, boolean active) {
		super(ge);
		this.toPartID = toPartID;
		this.active = active;
	}
	
	//public void changeLevel() {
	//	getParent().addGameEvent(new ChangePartEvent(toPartID, 0));
	//}
	
	
	
	

}
