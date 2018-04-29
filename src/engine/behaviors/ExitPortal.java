package engine.behaviors;

import authoring.groovy.GroovyMethod;
import engine.GameElement;

public class ExitPortal extends Behavior{
	private int portalID;
	
	public ExitPortal(GameElement ge, int portalID) {
		super(ge);
		this.portalID = portalID;
	}
	
	public ExitPortal(GameElement ge) {
		this(ge, 1);
	}
	
	@GroovyMethod
	public int getPortalID() {
		return portalID; 
	}
	
	@Override
	protected void addDefaultBehavior() {
		
	}

}
