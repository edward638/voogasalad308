package engine.behaviors;

import engine.GameElement;

public class TimeRoutine extends Behavior{
	
	private GameElement ge;
	private int routineTime;
	private boolean activateMotion;
	
	public TimeRoutine(GameElement ge, int i, boolean activate) {
		super(ge);
		this.ge = ge;
		this.activateMotion = activate;
		addUnimplementedMethods();
		routineTime = i;
		
	}
	
	public void setActivity(boolean state) {
		activateMotion = state;
	}
	
	private void addUnimplementedMethods() {
		if (!ge.hasBehavior(TimeTracker.class)) {
			ge.addBehavior(new TimeTracker(ge));
		}
		
	}
	
	public void switchXEveryNSteps() {
		if (ge.hasBehavior(Movable.class) && activateMotion) {
			Movable movable = (Movable) ge.getBehavior(Movable.class);
			TimeTracker timetracker = (TimeTracker) ge.getBehavior(TimeTracker.class);
			if (timetracker.isMultipleOf(routineTime)) {
				movable.setXVelocity(-movable.getXVelocity());
			}
			
		}
	}
	
	public void switchYEveryNSteps() {
		if (ge.hasBehavior(Movable.class) && activateMotion) {
			Movable movable = (Movable) ge.getBehavior(Movable.class);
			TimeTracker timetracker = (TimeTracker) ge.getBehavior(TimeTracker.class);
			if (timetracker.isMultipleOf(routineTime)) {
				movable.setYVelocity(-movable.getYVelocity());
			}
			
		}
	}
	
	public void jumpEveryNSteps() {
		if (ge.hasBehavior(Movable.class) && ge.hasBehavior(MovableCharacter.class) && activateMotion) {
			MovableCharacter mc = (MovableCharacter) ge.getBehavior(MovableCharacter.class);		
			TimeTracker timetracker = (TimeTracker) ge.getBehavior(TimeTracker.class);
			if (timetracker.isMultipleOf(routineTime)) {
				mc.jump();
			}
		}
	}
	
	
	public void createGameElementEveryNSteps() {
		
		if (ge.hasBehavior(AddsGameElement.class)) {
			AddsGameElement age = (AddsGameElement) ge.getBehavior(AddsGameElement.class);
			
			TimeTracker timetracker = (TimeTracker) ge.getBehavior(TimeTracker.class);
			if (timetracker.isMultipleOf(routineTime)) {
				System.out.println("Adding game element");
				age.addGameElement();
				//System.out.println("Added game element");
			}
		}
	}

	public int getStepIncrement() {
		return routineTime;
	}
	
	
}
