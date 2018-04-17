package engine.behaviors;
import java.util.Arrays;
import java.util.List;

import authoring.groovy.GroovyField;
import authoring.groovy.GroovyMethod;
import engine.GameElement;

/**
 * @author Gouttham 
 * The Game Elements which acquires this property have the ability to inherit event responses that allow it to respond to gravity. 
 *
 */
public class Gravity extends Behavior{
	@GroovyField
	public static final double GRAVITATIONAL_FORCE = -9.8;
	private Movable b;
	
	public Gravity(GameElement ge, Double vel, List<Double> dir) {
		super(ge);
		b = (Movable) ge.getBehavior(Movable.class);
	}
	
	public Gravity(GameElement ge) {
		this(ge, 5.0, Arrays.asList(0.0, 1.0));
	}
	
	@GroovyMethod
	public void experienceGravity(Double time) {
		b.setYVelocity(b.getYVelocity()-(GRAVITATIONAL_FORCE*time));
	}

	public void reverseGravity(Double time) {
		b.setYVelocity(b.getYVelocity()+(GRAVITATIONAL_FORCE*time));
	}

}
