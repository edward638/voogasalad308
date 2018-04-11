package engine.tests;

import java.util.ArrayList;
import java.util.List;

import engine.DisplayState;
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
	private DisplayState display;
	
	public ModelGameState() {
		GameElement e1 = getMario();
		GameElement e2 = getBlock(10.0, 20.0);
		GameElement e3 = getBlock(30.0, 20.0);
		GameElement e4 = getBlock(50.0, 20.0);
		GameElement e5 = getBlock(70.0, 20.0);
		GameElement e6 = getBlock(90.0, 20.0);
		GameElement e7 = getBlock(110.0, 20.0);
		GameElement e8 = getBlock(50.0, 80.0);
		state = new GameState();
		state.addGameElement(e1);
		state.addGameElement(e2);
		state.addGameElement(e3);
		state.addGameElement(e4);
		state.addGameElement(e5);
		state.addGameElement(e6);
		state.addGameElement(e7);
		state.addGameElement(e8);
		
		display = new DisplayState();
		display.addNewElement(e1);
		display.addNewElement(e2);
		display.addNewElement(e3);
		display.addNewElement(e4);
		display.addNewElement(e5);
		display.addNewElement(e6);
		display.addNewElement(e7);
		display.addNewElement(e8);
	}
	
	public GameState getState() {
		
		System.out.println(getMario());
		/*
		state.addGameElement(getMario());
		state.addGameElement(getBlock(10.0, 20.0));
		state.addGameElement(getBlock(30.0, 20.0));
		state.addGameElement(getBlock(50.0, 20.0));
		state.addGameElement(getBlock(70.0, 20.0));
		state.addGameElement(getBlock(90.0, 20.0));
		state.addGameElement(getBlock(110.0, 20.0));
		state.addGameElement(getBlock(50.0, 80.0));*/
		System.out.println(state.getElements());
		return state;
	}
	
	public DisplayState getDisplay() {
		/*display = new DisplayState();
		display.addNewElement(getMario());
		display.addNewElement(getBlock(10.0, 20.0));
		display.addNewElement(getBlock(30.0, 20.0));
		display.addNewElement(getBlock(50.0, 20.0));
		display.addNewElement(getBlock(70.0, 20.0));
		display.addNewElement(getBlock(90.0, 20.0));
		display.addNewElement(getBlock(110.0, 20.0));
		display.addNewElement(getBlock(50.0, 80.0));*/
		return display;
	}
	
	public GameElement getMario() {
		GameElement mario = new GameElement();
		//Note: Image path untested
		mario.addBehavior(new MandatoryBehavior(mario, "Mario", 200.0, 20.0, new RectangleShape(100.0, 100.0), "mario_1.jpg"));
		List<Double> direction = new ArrayList<>(); direction.add(1.0); direction.add(0.0);
;		mario.addBehavior(new Movable(mario, 0.0, direction));
		mario.addBehavior(new Gravity(mario));
		
		//Adding Time Responses
		mario.addEventResponse(new TimeEvent(0.0), new TimeMovable());
		mario.addEventResponse(new TimeEvent(0.0), new TimeGravity());
		
		// Response to up arrow key is to jump
		mario.addEventResponse(new KeyInputEvent(KeyCode.UP), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setYVelocity(mov.getYVelocity() - 30);
		});
		
		// Response to Right arrow key is to move right
		mario.addEventResponse(new KeyInputEvent(KeyCode.RIGHT), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setXVelocity(10.0);
		});
		
		// Response to Left arrow key is to move left
		mario.addEventResponse(new KeyInputEvent(KeyCode.LEFT), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setXVelocity(-10.0);
		});
		return mario;
	}
	
	public GameElement getBlock(Double xpos, Double ypos) {
		GameElement block = new GameElement();
		block.addBehavior(new MandatoryBehavior(block, "Block", xpos, ypos, new RectangleShape(20.0, 20.0), "20pxbox.png"));
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
