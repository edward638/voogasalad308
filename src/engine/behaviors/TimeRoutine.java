package engine.behaviors;

import engine.GameElement;

public class TimeRoutine extends Behavior{
	
	GameElement ge;
	int routineTime;
	public TimeRoutine(GameElement ge, int i) {
		super(ge);
		this.ge = ge;
		addUnimplementedMethods();
		routineTime = i;
		
	}
	
	private void addUnimplementedMethods() {
		if (!ge.hasBehavior(TimeTracker.class)) {
			ge.addBehavior(new TimeTracker(ge));
		}
	}
	
	public void switchXEveryNSteps(int step) {
		if (ge.hasBehavior(Movable.class)) {
			Movable movable = (Movable) ge.getBehavior(Movable.class);
			TimeTracker timetracker = (TimeTracker) ge.getBehavior(TimeTracker.class);
			if (timetracker.isMultipleOf(step)) {
				timetracker.setTimePassed(Math.ceil(timetracker.getTimePassed()));
				movable.setXVelocity(-movable.getXVelocity());
				
			}
			
		}
	}
	
	public void switchYEveryNSteps(int step) {
		if (ge.hasBehavior(Movable.class)) {
			Movable movable = (Movable) ge.getBehavior(Movable.class);
			TimeTracker timetracker = (TimeTracker) ge.getBehavior(TimeTracker.class);
			if (timetracker.isMultipleOf(step)) {
				timetracker.setTimePassed(Math.ceil(timetracker.getTimePassed()));
				movable.setYVelocity(-movable.getYVelocity());
			}
			
		}
	}
	
	public void jumpEveryNSteps(int step) {
		if (ge.hasBehavior(Movable.class) && ge.hasBehavior(MovableCharacter.class)) {
			MovableCharacter mc = (MovableCharacter) ge.getBehavior(MovableCharacter.class);		
			TimeTracker timetracker = (TimeTracker) ge.getBehavior(TimeTracker.class);
			if (timetracker.isMultipleOf(step)) {
				mc.jump();
			}
		}
	}
	
	public void shootEveryNSteps(int step) {
		
	}

	public int getStepIncrement() {
		return routineTime;
	}
}
