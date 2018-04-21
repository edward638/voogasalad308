package engine.behaviors;

import java.util.Arrays;
import java.util.List;

import authoring.GameObject;
import engine.GameElement;
import engine.events.gameevents.AddElementEvent;
import engine.tests.ModelGameState2;

public class Shooter extends Behavior {
	
	private GameObject toShoot;
	private Double defaultVelocity;
	private Double distAway; // The distance away from the character the bullet is generated 
	
	public Shooter(GameElement ge, Double defaultVel, Double dist) {
		super(ge);
		defaultVelocity = defaultVel;
		distAway = dist;
	}
	
	public Shooter(GameElement ge) {
		this(ge, 30.0, 10.0);
	}
	
	public void shoot(Double v, List<Double> direction) {
		MandatoryBehavior mand = (MandatoryBehavior) getParent().getBehavior(MandatoryBehavior.class);
		Double magDirection = Math.sqrt(Math.pow(direction.get(0), 2) + Math.pow(direction.get(1), 2));
		GameElement bullet = new ModelGameState2().getBullet(mand.getX() + distAway * direction.get(0)/magDirection, mand.getY() + direction.get(1)/magDirection, v);
		System.out.println(((Movable)(bullet.getBehavior(Movable.class))).getVelocity());
		getParent().addGameEvent(new AddElementEvent(bullet));
	}
	
	public void shootRight() {
		shoot(defaultVelocity, Arrays.asList(1.0, 0.0));
	}
	
	public void shootLeft() {
		shoot(defaultVelocity, Arrays.asList(-1.0, 0.0));
	}
	
	public void shootUp() {
		shoot(defaultVelocity, Arrays.asList(0.0, 1.0));
	}
	
	public void shootDown() {
		shoot(defaultVelocity, Arrays.asList(0.0, -1.0));
	}
}
