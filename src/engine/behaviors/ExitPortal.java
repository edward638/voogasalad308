package engine.behaviors;

import engine.GameElement;

public class ExitPortal extends Behavior{
	private int portalID;
	public ExitPortal(GameElement ge, int portalID) {
		super(ge);
		this.portalID = portalID;
	}
	
	public int getPortalID() {
		return portalID; 
	}

}
