package engine.tests;

import java.util.ArrayList;
import java.util.List;

import engine.DisplayState;
import engine.GameElement;
import engine.GameState;
import engine.actions.CollisionKillable;
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
		ArrayList<GameElement> elements = new ArrayList<GameElement>();
		
		
		//elements.add(getBack(0.0,0.0));
		for (double i = 0; i < 900; i+=45) {
			elements.add(getBlock(i, 500.0));
		}
		elements.add(getMario());
		
		for (double i = 500; i < 2000; i+=150) {
			elements.add(getKoopa(i, 100.0));
		}

		/*GameElement e2 = getBlock(10.0, 20.0);
		GameElement e3 = getBlock(30.0, 20.0);
		GameElement e4 = getBlock(50.0, 20.0);
		GameElement e5 = getBlock(70.0, 20.0);
		GameElement e6 = getBlock(90.0, 20.0);
		GameElement e7 = getBlock(110.0, 20.0);
		GameElement e8 = getBlock(50.0, 80.0);*/
		state = new GameState();
		display = new DisplayState();
		for (GameElement el : elements) {
			state.addGameElement(el);
			display.addNewElement(el);
		}
		/*state.addGameElement(e1);
		state.addGameElement(e2);
		state.addGameElement(e3);
		state.addGameElement(e4);
		state.addGameElement(e5);
		state.addGameElement(e6);
		state.addGameElement(e7);
		state.addGameElement(e8);
		
		display = new DisplayState("enginetestmario");
		display.addNewElement(e1);
		display.addNewElement(e2);
		display.addNewElement(e3);
		display.addNewElement(e4);
		display.addNewElement(e5);
		display.addNewElement(e6);
		display.addNewElement(e7);
		display.addNewElement(e8);*/
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
		mario.addBehavior(new MandatoryBehavior(mario, "Mario", 200.0, 20.0, new RectangleShape(100.0, 100.0), "MarioSMR.png"));
		List<Double> direction = new ArrayList<>(); direction.add(1.0); direction.add(0.0);
		mario.addBehavior(new Movable(mario, 0.0, direction));
		mario.addBehavior(new Gravity(mario));
		
		//Adding Time Responses
		mario.addEventResponse(new TimeEvent(0.0), new TimeMovable());
		mario.addEventResponse(new TimeEvent(0.0), new TimeGravity());
		mario.addEventResponse(new CollisionEvent(mario, mario), new CollisionKillable());
		
		// Response to up arrow key is to jump
		mario.addEventResponse(new KeyInputEvent(KeyCode.W), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setYVelocity(-150.0);
		});
		
		// Response to Right arrow key is to move right
		mario.addEventResponse(new KeyInputEvent(KeyCode.D), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setXVelocity(mov.getXVelocity()+20.0);
		});
		
		// Response to Left arrow key is to move left
		mario.addEventResponse(new KeyInputEvent(KeyCode.A), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setXVelocity(mov.getXVelocity()-20.0);
		});
		return mario;
	}
	
	public GameElement getBlock(Double xpos, Double ypos) {
		GameElement block = new GameElement();
		block.addBehavior(new MandatoryBehavior(block, "Block", xpos, ypos, new RectangleShape(40.0, 40.0), "mario_block.png"));
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
	
	public GameElement getBack(Double xpos, Double ypos) {
		GameElement block = new GameElement();
		block.addBehavior(new MandatoryBehavior(block, "Back", xpos, ypos, new RectangleShape(900.0, 590.0), "prairie.jpg"));
		return block;
	}
	
	public GameElement getKoopa(Double xpos, Double ypos) {
		GameElement block = new GameElement();
		
		block.addBehavior(new MandatoryBehavior(block, "Koopa", xpos, ypos, new RectangleShape(60.0, 80.0), "koopa.png"));
		List<Double> direction = new ArrayList<>(); direction.add(-1.0); direction.add(0.0);
		block.addBehavior(new Movable(block, 10.0, direction));
		block.addEventResponse(new TimeEvent(0.0), new TimeMovable());
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
