package engine.tests;

import java.util.ArrayList;
import java.util.List;

import engine.DisplayState;
import engine.GameElement;
import engine.GameState;
import engine.actions.CollisionKillable;
import engine.actions.CollisionStopXMotion;
import engine.actions.CollisionStopYMotion;
import engine.actions.IncrementTimeTracker;
import engine.actions.TimeGravity;
import engine.actions.TimeMovable;
import engine.actions.TimeSwitchXMotion;
import engine.behaviors.Gravity;
import engine.behaviors.Killable;
import engine.behaviors.MainCharacter;
import engine.behaviors.MandatoryBehavior;
import engine.behaviors.Movable;
import engine.behaviors.MovableCharacter;
import engine.behaviors.TimeRoutine;
import engine.behaviors.TimeTracker;
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
		for (double i = 0; i < 900; i+=40) {
			elements.add(getBlock(i, 500.0));
		}
		for (double i = 20; i < 500 ; i+=40) {
			elements.add(getMovableBlock(0.0, i));
		}
		
		elements.add(getMario());
		
		for (double i = 500; i < 3000; i+=400) {
			elements.add(getKoopa(i, 100.0));
		}

		state = new GameState();
		display = new DisplayState("enginetestmario");
		for (GameElement el : elements) {
			state.addGameElement(el);
			display.addNewElement(el);
		}
	}
	
	private GameElement getMovableBlock(double xpos, double ypos) {
		List<Double> direction = new ArrayList<>(); direction.add(1.0); direction.add(0.0);
		GameElement block = new GameElement();
		block.addBehavior(new MandatoryBehavior(block, "Block", xpos, ypos, new RectangleShape(40.0, 40.0), "mario_block.png"));
		block.addBehavior(new TimeTracker(block));
		block.addBehavior(new TimeRoutine(block, 5));
		block.addBehavior(new Movable(block, 20.0, direction));
		block.addEventResponse(new TimeEvent(0.0), new IncrementTimeTracker());
		block.addEventResponse(new TimeEvent(0.0), new TimeMovable());
		block.addEventResponse(new TimeEvent(0.0), new TimeSwitchXMotion());
		return block;
	}

	public GameState getState() {
		
		System.out.println(getMario());
		System.out.println(state.getElements());
		return state;
	}
	
	public DisplayState getDisplay() {
		return display;
	}
	
	public GameElement getMario() {
		GameElement mario = new GameElement();
		//Note: Image path untested
		mario.addBehavior(new MandatoryBehavior(mario, "Mario", 200.0, 20.0, new RectangleShape(100.0, 100.0), "MarioSMR.png"));
		List<Double> direction = new ArrayList<>(); direction.add(1.0); direction.add(0.0);
		mario.addBehavior(new MovableCharacter(mario, 0.0, direction));
		mario.addBehavior(new MainCharacter(mario, 1, false, false));
		mario.addBehavior(new Gravity(mario));
		
		//Adding Time Responses
		mario.addEventResponse(new TimeEvent(0.0), new TimeMovable());
		mario.addEventResponse(new TimeEvent(0.0), new TimeGravity());
		
		// Response to up arrow key is to jump
		mario.addEventResponse(new KeyInputEvent(KeyCode.W), (event, element) -> {
			MovableCharacter mov = (MovableCharacter) element.getBehavior(MovableCharacter.class);
			mov.jump();
		});
		
		
		// Response to Right arrow key is to move right
		mario.addEventResponse(new KeyInputEvent(KeyCode.D), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setXVelocity(200.0);
		});
		
		// Response to Left arrow key is to move left
		mario.addEventResponse(new KeyInputEvent(KeyCode.A), (event, element) -> {
			Movable mov = (Movable) element.getBehavior(Movable.class);
			mov.setXVelocity(-200.0);
		});
		
		mario.addEventResponse(new CollisionEvent(mario, CollisionEvent.VERTICALS, getBlock(0.0, 0.0), CollisionEvent.VERTICALS), new CollisionStopYMotion());
		mario.addEventResponse(new CollisionEvent(mario, CollisionEvent.SIDES, getBlock(0.0, 0.0), CollisionEvent.SIDES), new CollisionStopXMotion());

		return mario;
	}
	
	public GameElement getBlock(Double xpos, Double ypos) {
		GameElement block = new GameElement();
		block.addBehavior(new MandatoryBehavior(block, "Block", xpos, ypos, new RectangleShape(40.0, 40.0), "mario_block.png"));
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
		block.addBehavior(new Movable(block, 20.0, direction));
		block.addBehavior(new Killable(block, 100.0));
		block.addEventResponse(new TimeEvent(0.0), new TimeMovable());
		block.addEventResponse(new CollisionEvent(block, CollisionEvent.ALL_SIDES, getMario(), CollisionEvent.ALL_SIDES), new CollisionKillable());
		return block;
	}
}
