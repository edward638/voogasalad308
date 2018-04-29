package engine.behaviors;
import java.util.List;

import authoring.groovy.GroovyMethod;
import engine.GameElement;
import engine.events.gameevents.PlayAudioEvent;

public class MovableCharacter extends Movable {
	private String jumpAudioFilePath = "";

	public MovableCharacter(GameElement ge, Double vel, List<Double> dir) {
		super(ge, vel, dir);
	}
	
	public MovableCharacter(GameElement ge, Double vel, List<Double> dir, String jumpAudioFilePath) {
		super(ge, vel, dir);
		this.jumpAudioFilePath = jumpAudioFilePath;
	}
	
	public MovableCharacter(GameElement ge, Double xVel, Double yVel) {
		super(ge, xVel, yVel);
	}
	
	public MovableCharacter(GameElement ge) {
		super(ge);
	}
	
	@GroovyMethod
	@Override
	public void move(Double time) {
		super.move(time);
		this.setXVelocity(this.getXVelocity() * .98);
	}
	
	@GroovyMethod
	public void jump() {
		setYVelocity(getYVelocity() - 100);
//		if (!jumpAudioFilePath.equals("")) {
//			getParent().addGameEvent(new PlayAudioEvent(jumpAudioFilePath));
//		}
	}

}
