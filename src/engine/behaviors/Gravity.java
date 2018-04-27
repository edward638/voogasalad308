package engine.behaviors;
import authoring.groovy.GroovyMethod;
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
	
	public Gravity(GameElement ge) {
		super(ge);
	}
	
	@GroovyMethod
	public void experienceGravity(Double time) {
		Movable b = (Movable) getParent().getBehavior(Movable.class);
		b.setYVelocity(((Movable) getParent().getBehavior(Movable.class)).getYVelocity()+(GRAVITATIONAL_FORCE*time));
	}

	public void reverseGravity(Double time) {
		Movable b = ((Movable) getParent().getBehavior(Movable.class));
		(b).setYVelocity(b.getYVelocity()-(GRAVITATIONAL_FORCE*time));
	}
	
	@Override
	protected void addDefaultBehavior() {
		this.getParent().addEventResponse(new TimeEvent(0.0), new TimeGravity());
	}

}
