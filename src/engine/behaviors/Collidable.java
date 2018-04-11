package engine.behaviors;

import java.util.Arrays;
import java.util.List;

import engine.GameElement;

public class Collidable extends Behavior{
	public static final double GRAVITATIONAL_FORCE = -9.8;
	private Movable b;
	
	public Collidable(GameElement ge) {
		super(ge);
		b = (Movable) ge.getBehavior(Movable.class);
	}
	
	public Collidable(GameElement ge) {
		this(ge, 5.0, Arrays.asList(0.0, 1.0));
	}
	
	public void experienceGravity(Double time) {
		b.setYVelocity(b.getYVelocity()-(GRAVITATIONAL_FORCE*time));
	}
	
	public void reverseGravity(Double time) {
		b.setYVelocity(b.getYVelocity()+(GRAVITATIONAL_FORCE*time));
	}

}