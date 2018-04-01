package engine.behaviors;

import java.util.List;
import java.util.stream.Stream;

import engine.GameElement;

public class Movable extends Behavior{
	
	private Double velocity;
	private List<Double> direction;
	
	public Movable(GameElement ge, Double vel, List<Double> dir) {
		super(ge);
		setVelocity(vel);
		setDirection(dir);
	}
	
	public void moveRight() {
		
	}
	
	public void setDirection(List<Double> dir) {
		if (isValidDirection(dir)) {
			direction = dir;
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
		if (d.size() > 2) {
			return false;
		}
		Stream<Double> st = d.stream();
		Double sum = st.mapToDouble(f -> f.doubleValue()).sum();
		st.map(n -> n / sum);
		return true;
	}
	
	private boolean isValidVelocity(Double v) {
		return true;
	}
	
}
