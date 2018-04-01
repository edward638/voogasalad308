package engine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import engine.behaviors.BasicGameElement;
import engine.behaviors.Behavior;
import engine.eventresponses.EventResponse;
import engine.exceptions.TooManyBehaviorsException;

public class GameElement {
	private Set<Behavior> behaviors;
	private Set<EventResponse> responses;

	public GameElement() {
		behaviors = new HashSet<>();
		responses = new HashSet<>();
		addBehavior(new BasicGameElement(this, "Mario", 100.0, 100.0));
	}
	
	/*
	 * Adds the Behavior object containing the fields and methods that correspond to a specific behavior
	 */
	public void addBehavior(Behavior behave) {
		List<Behavior> existing = behaviors.stream()
				.filter(b -> b.getClass() == behave.getClass())
				.collect(Collectors.toList());
		if (existing.size() > 0) {
			throw new TooManyBehaviorsException("Trying to add " + behave.getClass() + " to a GameElement that already has this behavior");
		}
	}
	
	/*
	 * Get the behavior object corresponding to the 
	 */
	public Behavior getBehavior (Class<?> behavior_type) {
		try {
			return behaviors.stream()
					.filter(behavior -> behavior.getClass() == behavior_type)
					.collect(Collectors.toList())
					.get(0);
		}
		catch (NullPointerException n) {
			throw new IllegalArgumentException(behavior_type + " does not exist for GameElement" + getIdentifier());
		}
	}
	
	
	/*
	 * Adds the ability for this game element to respond to an ElementEvent in a certain way
	 */
	public void addEventReaction() {
		
	}
	
	/*
	 * Defines the method we will use to identify this game element. Should be done according the 
	 * BasicGameElement behavior since every element in the game will implement that
	 */
	public String getIdentifier() {
		BasicGameElement el = (BasicGameElement) behaviors.stream()
				.filter(b -> b.getClass() == new BasicGameElement(this, " ", 0.0, 0.0).getClass())
				.collect(Collectors.toList()).get(0);
		return el.getName();
	}
	
}
