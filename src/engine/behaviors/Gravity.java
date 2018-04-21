package engine.behaviors;
import engine.GameElement;
import engine.actions.TimeGravity;
import engine.events.elementevents.TimeEvent;

/**
 * @author Gouttham 
 * The Game Elements which acquires this property have the ability to inherit event responses that allow it to respond to gravity. 
 *
 */
public class Gravity extends Behavior{
	public static final double GRAVITATIONAL_FORCE = 9.8*10;
	private Movable b;
	
	public Gravity(GameElement ge) {
		super(ge);
		b = (Movable) ge.getBehavior(Movable.class);
	}
	
	public void experienceGravity(Double time) {
		b.setYVelocity(b.getYVelocity()+(GRAVITATIONAL_FORCE*time));
	}

	public void reverseGravity(Double time) {
		b.setYVelocity(b.getYVelocity()-(GRAVITATIONAL_FORCE*time));
	}
	
	@Override
	protected void addDefaultBehavior() {
		this.getParent().addEventResponse(new TimeEvent(0.0), new TimeGravity());
	}

}
