package engine.behaviors;

import engine.GameElement;
import engine.events.elementevents.CollisionEvent;

public class Killer extends Behavior{
	private Double damagePower;
	
	public Killer(GameElement ge, Double damagePower) {
		super(ge);
		this.damagePower = damagePower;
	}
	
	public Double getDamagePower() {
		return damagePower;
	}
	
	protected void addDefaultBehavior() {
		getParent().addEventResponse(
				new CollisionEvent(getParent(), CollisionEvent.ALL_SIDES,  new GameElement(), CollisionEvent.ALL_SIDES),
				(e, a) -> {
					
				});
	}
}
