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
import engine.behaviors.Movable;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.KeyInputEvent;
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
	
	public GameElement(String name) {
		this();
		behaviors.add(new MandatoryBehavior(this, name));
	}
	
	public Set<Behavior> getAllBehaviors() {
		return behaviors;
	}
	
	public EventResponder getResponder() {
		return responder;
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
					.filter(behavior -> behavior_type.isAssignableFrom(behavior.getClass()))
					.collect(Collectors.toList())
					.get(0);
		}
		catch (NullPointerException n) {
			throw new IllegalArgumentException(behavior_type + " does not exist for GameElement" + getIdentifier());
		}
	}
	
	
	/*
	 * Checks if this GameElement has a Behavior object of the requested type
	 */
	public boolean hasBehavior(Class<?> behavior_type) {
		return behaviors.stream()
			.filter(behavior -> behavior_type.isAssignableFrom(behavior.getClass()))
			.collect(Collectors.toList()).size() > 0;
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
		
		// Save the GameEvents added on this processEvent and return it. Reset the field 
		// returnedGameEvents for the next time this 
		// element processes an event
		List<GameEvent> returnableEvents = returnedGameEvents.stream()
				.collect(Collectors.toList()); 
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
	
	
	/*
	 * Defines the method we will use to retrieve the position of a game element. Should be done according the 
	 * MandatoryBehavior since every element in the game will implement that
	 */
	public List<Double> getPosition() {
		List<Double> position = new ArrayList<Double>();
		position.add(((MandatoryBehavior)(getBehavior(MandatoryBehavior.class))).getX());
		position.add(((MandatoryBehavior)(getBehavior(MandatoryBehavior.class))).getY());
		return position;
	}
	
	public void setPosition(List<Double> position) {
		((MandatoryBehavior)(getBehavior(MandatoryBehavior.class))).setPosition(position.get(0), position.get(1));
	}
	
	public void resetPosition() {
		((MandatoryBehavior)(getBehavior(MandatoryBehavior.class))).resetPosition();
	}
	
	/*
	 * Easy Printing
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		Double locX = ((MandatoryBehavior)(getBehavior(MandatoryBehavior.class))).getX();
		Double locY = ((MandatoryBehavior)(getBehavior(MandatoryBehavior.class))).getY();
		return getIdentifier() + " at (" + locX + ", " + locY + ")";
	}
	
	public boolean matchesType(GameElement other) {
		return other.getIdentifier().equals(getIdentifier()) || other.getIdentifier().equals(MandatoryBehavior.REFER_ALL_ELEMENTS);
	}
	
	/*
	 * Returns a map of every behavior's instance variables to their value (represented as an object)
	 */
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
