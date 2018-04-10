package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import engine.actions.Action;
import engine.behaviors.Behavior;
import engine.behaviors.MandatoryBehavior;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;
import engine.exceptions.TooManyBehaviorsException;

public class GameElement {
	private Set<Behavior> behaviors;
	private EventResponder responder;
	private List<GameEvent> returnedGameEvents;
	
	public GameElement() {
		behaviors = new HashSet<>();
		responder = new EventResponder(this);
		returnedGameEvents = new ArrayList<>();
	}
	
	/*
	 * Adds the Behavior object containing the fields and methods that correspond to a specific behavior.
	 * Checks if there already exists a behavior object for this behavior and throws an exception if the 
	 * type of the one being added matches that of an existing behavior
	 */
	public void addBehavior(Behavior behave) {
		List<Behavior> existing = behaviors.stream()
				.filter(b -> b.getClass() == behave.getClass())
				.collect(Collectors.toList());
		if (existing.size() > 0) {
			throw new TooManyBehaviorsException("Trying to add " + behave.getClass() + " to a GameElement that already has this behavior");
		}
		behaviors.add(behave);
	}
	
	/*
	 * Get the behavior object corresponding to the class type specified
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
	public void addEventResponse(ElementEvent e, Action a) {
		responder.addResponse(e, a);
	}
	
	/*
	 * Tells element to respond to an event
	 */
	public List<GameEvent> processEvent(ElementEvent event) {
		responder.respondTo(event);
		List<GameEvent> returnableEvents = new ArrayList<>(returnedGameEvents);
		returnedGameEvents = new ArrayList<>();
		return returnableEvents;
	}
	
	/*
	 * Allows behaviors to add GameEvent objects to this GameElement that will 
	 * be returned at the completion of processEvent
	 */
	
	public void addGameEvent(GameEvent gameevent) {
		returnedGameEvents.add(gameevent);
	}
	/*
	 * Defines the method we will use to identify this game element. Should be done according the 
	 * BasicGameElement behavior since every element in the game will implement that
	 */
	public String getIdentifier() {
		MandatoryBehavior el = (MandatoryBehavior) behaviors.stream()
				.filter(b -> b.getClass() == MandatoryBehavior.class)
				.collect(Collectors.toList()).get(0);
		return el.getName();
	}
	
	public Map<String, Object> reportProperties() {
		List<Map<String, Object>> behaviorResponses = behaviors.stream()
				.map(behavior -> behavior.reportProperties())
				.collect(Collectors.toList());
		Map<String, Object> returning = new HashMap<String, Object>();
		for (Map<String, Object> map: behaviorResponses) {
			returning.putAll(map);
		}
		return returning;
	}
	
}
