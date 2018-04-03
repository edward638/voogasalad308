package engine.behaviors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import engine.GameElement;

public class Movable extends Behavior{
	
	public static final int X_LIST_POS = 0; // Position of X in direction 
	public static final int Y_LIST_POS = 1; // Position of Y in direction
	
	private Double velocity;
	private List<Double> direction;
	
	public Movable(GameElement ge, Double vel, List<Double> dir) {
		super(ge);
		setVelocity(vel);
		setDirection(dir);
	}
	
	public Movable(GameElement ge) {
		this(ge, 0.0, Arrays.asList(0.0, 1.0));
	}
	
	/*
	 * Moves the parent game element according to the time amount requested
	 */
	public void move(Double time) {
		BasicGameElement bge = (BasicGameElement) getParent().getBehavior(BasicGameElement.class);
		bge.setPosition(bge.getX() + velocity * time * direction.get(X_LIST_POS), bge.getY() + velocity * time * direction.get(Y_LIST_POS));
	}
	
	/*
	 * Sets the direction for this element. Checks if the direction 
	 * is valid and normalizes it so move method works correctly
	 */
	public void setDirection(List<Double> dir) {
		if (isValidDirection(dir)) {
			Double total = dir.stream().reduce(0.0, (a, b) -> a + b);
			List<Double> normalized = dir.stream().map(n -> n/total).collect(Collectors.toList());
			direction = normalized;
		} else {
			throw new IllegalArgumentException("Illegal Direction for " + getParent().getIdentifier() + ": " + dir);
		}
	}
	
	/*
	 * Sets the velocity according to what the other classes want. Checks if velocity is valid for this object 
	 */
	public void setVelocity (Double v) {
		if (isValidVelocity(v)) {
			velocity = v;
		} else {
			throw new IllegalArgumentException("Illegal Velocity for " + getParent().getIdentifier() + ": " + v);
		}
	}
	
	public Double getVelocity () {
		return velocity;
	}
	
	public List<Double> getDirection() {
		return direction;
	}
	
	private boolean isValidDirection(List<Double> d) {
		if (d.size() > 2 || 
				d.stream().reduce(0.0, (a, b) -> Math.abs(a) + Math.abs(b)) == 0) {
			return false;
		}
		return true;
	}
	
	private boolean isValidVelocity(Double v) {
		return true;
	}
	
}
