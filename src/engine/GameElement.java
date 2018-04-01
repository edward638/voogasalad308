package engine;

import java.util.Set;

import engine.behaviors.BasicGameElement;
import engine.behaviors.Behavior;

public class GameElement {
	private Set<Behavior> behaviors;
	
	
	public GameElement() {
		addBehavior(new BasicGameElement(this, "Mario", 100.0, 100.0));
	}
	
	/*
	 * Adds the object containing the fields and methods that correspond to a specific behavior
	 */
	public void addBehavior(Behavior behave) {
		behaviors.add(behave);
	}
	
	/*
	 * Adds the ability for this game element to respond to an ElementEvent in a certain way
	 */
	public void addEventReaction() {
		
	}
	
}
