package engine.behaviors;

import engine.GameElement;

public class TimeTracker extends Behavior{
	private Double timePassed = 0.0;
	public TimeTracker(GameElement ge) {
		super(ge);
		timePassed = 0.0;
	}
	
	public double getTimePassed() {
		return timePassed;
	}
	
	public void setTimePassed(Double time) {
		timePassed = time;
	}
	
	public boolean isMultipleOf(int i) {
		return ((int) Math.ceil(timePassed) % i)==0 && timePassed>1;
	}
	
	public void incrementTimePass(Double steps) {
		timePassed+=steps;
	}
}
