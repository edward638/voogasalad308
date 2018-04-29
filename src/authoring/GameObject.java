package authoring;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import engine.behaviors.MandatoryBehavior;

/**
 * 
 * @author: Summer and Maddie
 **/
public class GameObject {

	private String myName;
	private Set<AuthBehavior> myBehaviors;
	private Set<Event> myEvents;
	private BehaviorFactory myBehaviorFactory;

	/**
	 * Constructs a default GameObject that contains only the MandatoryBehavior.
	 */
	public GameObject() {
		myBehaviors = new HashSet<>();
		myEvents = new HashSet<>();
		myBehaviorFactory = new BehaviorFactory();
		addBehavior(MandatoryBehavior.class.getCanonicalName());
	}

	/**
	 * Constructs a default {@code GameObject} that contains the MandatoryBehavior
	 * and the {@code initBehavior}.
	 * 
	 * @param initBehavior
	 *            The AuthBehavior that the GameObject is initialized with.
	 */
	public GameObject(AuthBehavior initBehavior) {
		this();
		myBehaviors.add(initBehavior);
	}

	/**
	 * Constructs a default {@code GameObject} that contains the MandatoryBehavior
	 * and the {@code initBehavior}.
	 * 
	 * @param initBehavior
	 *            The String name of the AuthBehavior that the GameObject is
	 *            initialized with.
	 */
	public GameObject(String initBehavior) {
		this();
		addBehavior(initBehavior);
	}

	/**
	 * Constructs a {@code GameObject} identical to the {@code GameObject}
	 * {@code toCopy}.
	 * 
	 * @param toCopy
	 */
	public GameObject(GameObject toCopy) {
		this();

		Set<AuthBehavior> newBehaviors = new HashSet<>();
		for (AuthBehavior ab : toCopy.getBehaviors()) {
			if (!ab.equals(this.getMandatoryBehavior())) {
				newBehaviors.add(ab);
			}
		}
		myBehaviors = newBehaviors;

//		Set<Event> newEvents = new HashSet<>();
//		for (Event e : toCopy.getEvents()) {
//			newEvents.add(e.clone());
//		}
		myEvents = toCopy.getEvents();
		
		myName = toCopy.getName();
		
		addBehavior(MandatoryBehavior.class.getCanonicalName());
				
		this.getMandatoryBehavior().getProperty("xPos").setValue(toCopy.getMandatoryBehavior().getProperty("xPos").getValue());
		this.getMandatoryBehavior().getProperty("yPos").setValue(toCopy.getMandatoryBehavior().getProperty("yPos").getValue());
		this.getMandatoryBehavior().getProperty("displayWidth").setValue(toCopy.getMandatoryBehavior().getProperty("displayWidth").getValue());
		this.getMandatoryBehavior().getProperty("displayHeight").setValue(toCopy.getMandatoryBehavior().getProperty("displayHeight").getValue());

		this.getMandatoryBehavior().getProperty("imagePath").setValue(toCopy.getMandatoryBehavior().getProperty("imagePath").getValue());
		
	}

	public void addBehavior(String behaviorToAdd) {
		AuthBehavior newBehavior = myBehaviorFactory.makeBehavior(behaviorToAdd);
		myBehaviors.add(newBehavior);
	}

	public void addBehavior(AuthBehavior behaviorToAdd) {
		myBehaviors.add(behaviorToAdd);
	}
	
	public boolean hasBehavior(String s) {
		return !myBehaviors.stream().filter(beh -> beh.getName().contains(s))
				.collect(Collectors.toList()).isEmpty();
	}
	

	public void removeBehavior(AuthBehavior behaviorToRemove) {
		myBehaviors.remove(behaviorToRemove);
	}

	public Set<AuthBehavior> getBehaviors() {
		return myBehaviors;
	}

	public AuthBehavior getMandatoryBehavior() {
		return getBehavior(MandatoryBehavior.class.getCanonicalName());
	}

	public AuthBehavior getBehavior(String behavior) {
		try {
			for (AuthBehavior curr : myBehaviors) {
				if (curr.getName().equals(behavior) || curr.getDisplayName().equals(behavior)) {
					// is the above ok? i'm not sure whether that makes it more or less confusing
					//i think it should be fine if we allow both methods of accessing it I can't see any problems with that
//					System.out.println("RETURNED BEHAVIOR: " + curr);
					// i think it should be fine if we allow both methods of accessing it I can't
					// see any problems with that
					return curr;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Tried to access a behavior that this object does not have");
		}
		return null;
	}

	public void addEvent(Event toAdd) {
		myEvents.add(toAdd);
	}

	public void deleteEvent(Event toDelete) {
		if (myEvents.contains(toDelete)) {
			myEvents.remove(toDelete);
		}
	}

	public Set<Event> getEvents() {
		return myEvents;
	}

	public void setName(String name) {
		myName = name;
	}

	public String getName() {
		return myName;
	}

	public String toString() {
		return myName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!obj.getClass().equals(this.getClass()))
			return false;
		return ((GameObject) obj).getName().equals(this.getName());
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
}
