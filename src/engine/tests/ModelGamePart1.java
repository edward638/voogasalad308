package engine.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.GameElement;
import engine.GamePart;
import engine.actions.ChangeLevel;
import engine.actions.GroovyAction;
import engine.behaviors.BlockLike;
import engine.behaviors.BulletLike;
import engine.behaviors.EntrancePortal;
import engine.behaviors.ExitPortal;
import engine.behaviors.Gravity;
import engine.behaviors.IgnoresBlocks;
import engine.behaviors.Killable;
import engine.behaviors.MainCharacter;
import engine.behaviors.MandatoryBehavior;
import engine.behaviors.Movable;
import engine.behaviors.MovableCharacter;
import engine.behaviors.Shooter;
import engine.behaviors.TimeRoutine;
import engine.behaviors.TimeRoutine2;
import engine.behaviors.TimeTracker;
import engine.behaviors.TrackMainCharacter;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.KeyInputEvent;
import javafx.scene.input.KeyCode;

public class ModelGamePart1 {
	GamePart modelGamePart1;
	
	public ModelGamePart1() {
		modelGamePart1 = new GamePart("modelGamePart1", "level 1");
		
		addMainCharacter();
		List<GameElement> elements = new ArrayList<GameElement>();
		for (double i = 0; i < 900; i+=40) {
			elements.add(getBlock(i, 501.0));
		}	
		
		for (double i = 300; i < 900; i+=40) {
			elements.add(getBlock(i, 301.0));
		}
		
		for (double i = 500; i < 3000; i+=400) {
			elements.add(getKoopa(i, 101.0));
		}
		
		elements.add(getPortal1(900.0, 101.0));
		elements.add(getPortal2(600.0, 101.0));
		
		for (GameElement el : elements) {
			modelGamePart1.addGameElement(el);
		}
	}
	
	private void addMainCharacter() {
		GameElement mainCharacter = getMario();
		modelGamePart1.addGameElement(mainCharacter);
	}
	
	public GameElement getPortal1(Double xpos, Double ypos) {
		GameElement block = new GameElement();
		block.addBehavior(new MandatoryBehavior(block, "Block", xpos, ypos, "rectangle", 40.0, 40.0, 40.0, 40.0, "mario_block.png"));
		block.addBehavior(new ExitPortal(block, 2));
		
		return block;
	}
	
	public GameElement getPortal2(Double xpos, Double ypos) {
		GameElement block = new GameElement();
		block.addBehavior(new MandatoryBehavior(block, "Block", xpos, ypos, "rectangle", 40.0, 40.0, 40.0, 40.0, "mario_block.png"));
		List<String> x = new ArrayList<String>();
		block.addBehavior(new EntrancePortal(block, true, "modelGamePart2", x, 1));
		block.addEventResponse(new CollisionEvent(block, CollisionEvent.ALL_SIDES, new GameElement("Mario"), CollisionEvent.ALL_SIDES), new ChangeLevel());
		
		return block;
	}
	
	public GameElement getMario() {
		GameElement mario = new GameElement();
		//Note: Image path untested
		mario.addBehavior(new MandatoryBehavior(mario, "Mario", 200.0, 20.0, "rectangle", 100.0, 100.0, 100.0, 100.0, "MarioSMR.png"));
		List<Double> direction = new ArrayList<>(); direction.add(1.0); direction.add(0.0);
		mario.addBehavior(new MovableCharacter(mario, 0.0, direction, "data/music/Mario-jump-sound.mp3"));
		mario.addBehavior(new MainCharacter(mario, 1, true, true));
		mario.addBehavior(new Gravity(mario));
		mario.addBehavior(new TimeTracker(mario));
		mario.addBehavior(new TimeRoutine2(mario));
		mario.addBehavior(new Shooter(mario));
		
		TimeRoutine2 marioRoutines = (TimeRoutine2) mario.getBehavior(TimeRoutine2.class);
		marioRoutines.addRoutine(2.0, new GroovyAction(
				"Mario.getBehavior('Shooter').shootRight()"));
				
		mario.addEventResponse(new KeyInputEvent(KeyCode.W), new GroovyAction(
				"Mario.getBehavior('MovableCharacter').jump()"));
		
		// Response to Right arrow key is to move right
		mario.addEventResponse(new KeyInputEvent(KeyCode.D), new GroovyAction(
				"Mario.getBehavior('MovableCharacter').setXVelocity(200.0)"));
		
		// Response to Left arrow key is to move left
		mario.addEventResponse(new KeyInputEvent(KeyCode.A), new GroovyAction(
				"Mario.getBehavior('MovableCharacter').setXVelocity(-200.0)"));
		
		mario.addEventResponse(new KeyInputEvent(KeyCode.P), new GroovyAction("Mario.getBehavior('MovableCharacter').jump()"));

		return mario;
	}
	
	public GameElement getBlock(Double xpos, Double ypos) {
		GameElement block = new GameElement();
		block.addBehavior(new MandatoryBehavior(block, "Block", xpos, ypos, "rectangle", 40.0, 40.0, 40.0, 40.0, "mario_block.png"));
		block.addBehavior(new BlockLike(block));
		return block;
	}

	
	public GameElement getBack(Double xpos, Double ypos) {
		GameElement block = new GameElement();
		block.addBehavior(new MandatoryBehavior(block, "Back", xpos, ypos, "rectangle", 900.0, 590.0, 900.0, 590.0, "prairie.jpg"));
		return block;
	}
	
	public GameElement getKoopa(Double xpos, Double ypos) {
		GameElement koopa = new GameElement();
		
		koopa.addBehavior(new MandatoryBehavior(koopa, "Koopa", xpos, ypos, "rectangle", 60.0, 80.0, 60.0, 80.0, "koopa.png"));
		List<Double> direction = new ArrayList<>(); direction.add(-1.0); direction.add(0.0);
		koopa.addBehavior(new Movable(koopa, 20.0, direction));
		koopa.addBehavior(new Killable(koopa, 100.0));
		koopa.addBehavior(new IgnoresBlocks(koopa));
		koopa.addBehavior(new TrackMainCharacter(koopa, modelGamePart1.getMainCharacter()));
		koopa.addBehavior(new TimeRoutine2(koopa));
		koopa.addBehavior(new Shooter(koopa));
		TimeRoutine2 tr2 = (TimeRoutine2) koopa.getBehavior(TimeRoutine2.class);
		tr2.addRoutine(0.5, new GroovyAction(
				"shooter = Koopa.getBehavior('Shooter')\n"
				+ "tracker = Koopa.getBehavior('TrackMainCharacter')"
				+ "shooter.shoot([tracker.getX(), tracker.getY()]"));

		return koopa;
	}
	
	public GameElement getBullet(Double xpos, Double ypos, Double v, List<Double> direction) {
		GameElement bullet = new GameElement();
		bullet.addBehavior(new MandatoryBehavior(bullet, "Bullet", xpos, ypos, "rectangle", 20.0, 20.0, 20.0, 20.0, "bullet.png"));
		bullet.addBehavior(new Movable(bullet, v, direction));
		bullet.addBehavior(new BulletLike(bullet, 10.0));
		bullet.addBehavior(new IgnoresBlocks(bullet));
		return bullet;
	}
	
	public GameElement getBullet(Double xpos, Double ypos, Double v) {
		GameElement bullet = new GameElement();
		bullet.addBehavior(new MandatoryBehavior(bullet, "Bullet", xpos, ypos, "rectangle", 20.0, 20.0, 20.0, 20.0, "bullet.png"));
		bullet.addBehavior(new Movable(bullet, v, Arrays.asList(1.0, 0.0)));
		bullet.addBehavior(new BulletLike(bullet, 10.0));
		return bullet;
	}
	
	public GamePart getGamePart() {
		return modelGamePart1;
	}
}
