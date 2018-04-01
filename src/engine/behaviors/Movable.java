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
		this(ge, 0.0, Arrays.asList(0.0, 10.0));
	}
	
	public void move(Double time) {
		GameElement p = getParent();
		p.setPosition(p.getX() + velocity * time * direction.get(X_LIST_POS), p.getY() + velocity * time * direction.get(Y_LIST_POS));
	}
	
	public void setDirection(List<Double> dir) {
		if (isValidDirection(dir)) {
			Double total = dir.stream().reduce(0.0, (a, b) -> a + b);
			List<Double> normalized = dir.stream().map(n -> n/total).collect(Collectors.toList());
			direction = normalized;
		} else {
			throw new IllegalArgumentException("Illegal Direction for " + getParent().getName() + ": " + dir);
		}
	}
	
	public void setVelocity (Double v) {
		if (isValidVelocity(v)) {
			velocity = v;
		} else {
			throw new IllegalArgumentException("Illegal Velocity for " + getParent().getName() + ": " + v);
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
