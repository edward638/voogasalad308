package engine.behaviors;

import engine.GameElement;
import engine.GamePart;
import engine.GameState;
import engine.actions.CollisionStopXMotion;
import engine.actions.CollisionStopYMotion;
import engine.events.elementevents.CollisionEvent;

public class BlockLike extends Behavior {

	public BlockLike(GameElement ge) {
		super(ge);
	}
	
	@Override
	protected void addDefaultBehavior() {
		getParent().addEventResponse(new CollisionEvent(getParent(), CollisionEvent.VERTICALS, new GameElement(MandatoryBehavior.REFER_ALL_ELEMENTS), CollisionEvent.VERTICALS), new CollisionStopYMotion());
		getParent().addEventResponse(new CollisionEvent(getParent(), CollisionEvent.SIDES, new GameElement(MandatoryBehavior.REFER_ALL_ELEMENTS), CollisionEvent.SIDES), new CollisionStopXMotion());
	}

}
