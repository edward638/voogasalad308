package engine.behaviors;

import engine.GameElement;
import engine.actions.CollisionKiller;
import engine.events.elementevents.CollisionEvent;

public class BulletLike extends Killer {

	public BulletLike(GameElement ge) {
		super(ge);
		// TODO Auto-generated constructor stub
	}
	
	public BulletLike(GameElement ge, Double damage) {
		super(ge, damage);
	}
	
	@Override
	protected void addDefaultBehavior() {
		getParent().addEventResponse(new CollisionEvent(getParent(), CollisionEvent.ALL_SIDES,  (new GameElement(MandatoryBehavior.REFER_ALL_ELEMENTS)), 
				CollisionEvent.ALL_SIDES), new CollisionKiller());
		
	}

}
