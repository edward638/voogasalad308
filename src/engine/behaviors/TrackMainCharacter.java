package engine.behaviors;

import java.util.List;

import engine.GameElement;

public class TrackMainCharacter extends Behavior{
	GameElement ge;
	GameElement trackedElement;
	public TrackMainCharacter(GameElement ge, GameElement trackedElement) {
		super(ge);
		this.ge = ge;
		this.trackedElement = trackedElement;
	}
	
	public void moveIfMoving() { 
		Movable m = (Movable) ge.getBehavior(Movable.class);
		Movable m_tracked = (Movable) trackedElement.getBehavior(Movable.class);
		System.out.println(m_tracked.getVelocity());
		if (m_tracked.getVelocity()<10) {
			m.setactivity(true);;
		}
		else {
			m.setactivity(false);
		}
	}
}
