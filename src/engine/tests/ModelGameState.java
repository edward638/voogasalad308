package engine.tests;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.GameState;
import engine.actions.TimeGravity;
import engine.actions.TimeMovable;
import engine.behaviors.Gravity;
import engine.behaviors.MandatoryBehavior;
import engine.behaviors.Movable;
import engine.behaviors.shapes.RectangleShape;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.KeyInputEvent;
import engine.events.elementevents.TimeEvent;
import javafx.scene.input.KeyCode;

public class ModelGameState {
	private GameState state;
	
	public ModelGameState() {
	}
	
	public GameState getState() {
		state = new GameState();
		System.out.println(getMario());
		state.addGameElement(getMario());
		state.addGameElement(getBlock(10.0, 20.0));
		state.addGameElement(getBlock(30.0, 20.0));
		state.addGameElement(getBlock(50.0, 20.0));
		state.addGameElement(getBlock(70.0, 20.0));
		state.addGameElement(getBlock(90.0, 20.0));
		state.addGameElement(getBlock(110.0, 20.0));
		state.addGameElement(getBlock(50.0, 80.0));
		System.out.println(state.getElements());
		return state;
	}
	
	public GameElement getMario() {
		GameElement mario = new GameElement();
		mario.addBehavior(new MandatoryBehavior(mario, "Mario", 100.0, 100.0, new RectangleShape(20.0, 40.0)));
		List<Double> direction = new ArrayList<>(); direction.add(1.0); direction.add(0.0);
;		mario.addBehavior(new Movable(mario, 0.0, direction));
		mario.addBehavior(new Gravity(mario));
		
		//Adding Time Responses
		mario.addEventResponse(new TimeEvent(0.0), new TimeMovable());
		mario.addEventResponse(new TimeEvent(0.0), new TimeGravity());
		
		// Response to up arrow key is to jump
		mario.addEventResponse(new KeyInputEvent(KeyCode.UP), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setYVelocity(mov.getYVelocity() + 30);
		});
		
		// Response to Right arrow key is to move right
		mario.addEventResponse(new KeyInputEvent(KeyCode.RIGHT), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setXVelocity(10.0);
		});
		
		// Response to Left arrow key is to move left
		mario.addEventResponse(new KeyInputEvent(KeyCode.RIGHT), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setXVelocity(-10.0);
		});
		return mario;
	}
	
	public GameElement getBlock(Double xpos, Double ypos) {
		GameElement block = new GameElement();
		block.addBehavior(new MandatoryBehavior(block, "Block", xpos, ypos, new RectangleShape(20.0, 20.0)));
		block.addEventResponse(new CollisionEvent(getMario(), block), (event, element) -> {
			CollisionEvent ce = (CollisionEvent) event;
			GameElement other = ce.getCollidedWith(element);
			if (other.hasBehavior(Movable.class)) {
				Movable mov = (Movable) other.getBehavior(Movable.class);
				mov.setYVelocity(0.0);
			}
		});
		return block;
	}
}
