package engine.behaviors;
import java.util.List;
import engine.GameElement;

public class MovableCharacter extends Movable {

	public MovableCharacter(GameElement ge, Double vel, List<Double> dir) {
		super(ge, vel, dir);
		// TODO Auto-generated constructor stub
	}
	
	public MovableCharacter(GameElement ge, Double xVel, Double yVel) {
		super(ge, xVel, yVel);
	}
	
	@Override
	public void move(Double time) {
		super.move(time);
		this.setXVelocity(this.getXVelocity() * .99);
	}
	
	public void jump() {
		setYVelocity(getYVelocity() - 200);
	}

}
