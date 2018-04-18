package engine.behaviors;

import java.util.Arrays;
import java.util.List;

import engine.GameElement;
import javafx.scene.input.KeyCode;

public class MainCharacter extends Behavior{
	
	int gamelives;
	List<KeyCode> defaultmoves = Arrays.asList();
	
	public MainCharacter(GameElement ge, int gamelives) {
		super(ge);
		if (!ge.hasBehavior(Movable.class)) {
			ge.addBehavior(new Movable(ge));
		}
		if (!ge.hasBehavior(Killable.class)) {
			ge.addBehavior(new Killable(ge));
		}
		this.gamelives = gamelives;
		
	}
	
	public boolean isMainCharacter() {
		return true;
	}
	
	public int getLives() {
		return gamelives;
	}
	
	public List<KeyCode> getmoveKeys() {
		return defaultmoves;
	}
}
