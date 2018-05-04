package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import authoring.GameObject;
import engine.actions.Action;
import engine.behaviors.Behavior;
import engine.behaviors.MainCharacter;
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
	 * Checks if there already exists a behavior object for this type. If there is, it removes it and adds 
	 * the incoming one. Can be used to help subclasses replace super classes
	 */
	public void addBehavior(Behavior behave) {
//		System.out.println("GameElement: Incoming Behavior: " + behave);
		List<Behavior> existing = behaviors.stream()
				.filter(b -> b.getClass().isAssignableFrom(behave.getClass()))
				.collect(Collectors.toList());
		if (existing.size() > 0) {
			if (existing.get(0).getClass().isAssignableFrom(behave.getClass())) {
				behaviors.remove(existing.get(0));
//				System.out.println("Game Element: Removed Behavior " + existing.get(0));
			} else {
				throw new TooManyBehaviorsException("Trying to add " + behave.getClass() + " to a GameElement that already has this behavior: " + existing.get(0));
			}
		}
//		System.out.println("GameElement: Added Behavior: " + behave);
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
		catch (Exception e) {
			throw new IllegalArgumentException(behavior_type + " does not exist for GameElement " + getIdentifier());
		}
	}
	
	public Behavior getBehavior (String className) {
		String qualifiedName = Behavior.class.getPackage().getName() + "." + className;
		try {
			Class<?> clazz = Class.forName(qualifiedName);
			return getBehavior(clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not convert " + qualifiedName + " to a behavior type");
		}
	}
	
	public MandatoryBehavior getMandatoryBehavior() {
		return (MandatoryBehavior) getBehavior(MandatoryBehavior.class);
	}
	
	
	/*
	 * Checks if this GameElement has a Behavior object of the requested type
	 */
	public boolean hasBehavior(Class<?> behaviorType) {
//		System.out.println("GameElement: hasBehavior: incoming: " + behaviorType);
//		System.out.println("GameElement: " + behaviors.stream()
//			.filter(behavior -> behaviorType.isAssignableFrom(behavior.getClass()) || behaviorType.equals(behavior.getClass()))
//			.collect(Collectors.toList()));
		behaviors.stream().forEach(b -> {
//			System.out.println("Game Element: hasBehavior Existing Behavior: " + b.getClass());
//			System.out.println("Game Element: hasBehavior Existing Behavior: " + b.getClass());
		});
		return behaviors.stream()
			.filter(behavior -> behaviorType.isAssignableFrom(behavior.getClass()) || behaviorType.equals(behavior.getClass()))
			.collect(Collectors.toList()).size() > 0;
	}
	
	public boolean hasBehavior(String className) {
		String qualifiedName = MandatoryBehavior.class.getPackage().getName() + className;
		try {
			return hasBehavior(Class.forName(qualifiedName));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not find" + qualifiedName + " class");
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
//		System.out.println("GameElement: hasBehavior: " + other);
//		System.out.println("GameElement: hasBehavior: " + other.hasBehavior(MainCharacter.class));
		return other.getIdentifier().equals(getIdentifier()) 
				|| other.getIdentifier().equals(MandatoryBehavior.REFER_ALL_ELEMENTS)
				|| (getIdentifier().equals(MandatoryBehavior.REFER_MAIN_CHARACTER) && other.hasBehavior(MainCharacter.class))
				|| (other.getIdentifier().equals(MandatoryBehavior.REFER_MAIN_CHARACTER) && hasBehavior(MainCharacter.class));
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
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof GameElement)) {
			return false;
		}
		GameElement other = (GameElement) o;
		Map<String, Object> thisProperties = reportProperties();
		Map<String, Object> otherProperties = other.reportProperties();
		if (thisProperties.size() != otherProperties.size()) {return false;}
		
		for (String thisKey: thisProperties.keySet()) {
			if ((thisProperties.get(thisKey) instanceof GameObject)) {
				continue;
			}
			if (!(otherProperties.get(thisKey).equals(thisProperties.get(thisKey)))) {
				return false;
			}
		}
		return true;
	}
}
