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
//	private double xPos;
//	private double yPos;
//	private double displayWidth;
//	private double displayHeight;
//	private String imagePath;
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
		setName(toCopy.getName());
		addBehavior(MandatoryBehavior.class.getCanonicalName());
		setxPos((double) toCopy.getMandatoryBehavior().getProperty("xPos").getValue());
		setyPos((double) toCopy.getMandatoryBehavior().getProperty("yPos").getValue());
		if(toCopy.getMandatoryBehavior().getProperty("displayWidth").getValue() != null) {
			setDisplayWidth((double) toCopy.getMandatoryBehavior().getProperty("displayWidth").getValue());
			setDisplayHeight((double) toCopy.getMandatoryBehavior().getProperty("displayHeight").getValue());
		}
		this.setImagePath((String) toCopy.getMandatoryBehavior().getProperty("imagePath").getValue());
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

	public String getName() {
		return myName;
	}
	
	public void setName(String name) {
		myName = name;
		getMandatoryBehavior().getProperty("elementName").setValue(name);
	}

	public double getxPos() {
		return (Double) getMandatoryBehavior().getProperty("xPos").getValue();
	}

	public void setxPos(double xPos) {
//		this.xPos = xPos;
		getMandatoryBehavior().getProperty("xPos").setValue(xPos);
	}

	public double getyPos() {
		return (Double) getMandatoryBehavior().getProperty("yPos").getValue();
	}

	public void setyPos(double yPos) {
//		this.yPos = yPos;
		getMandatoryBehavior().getProperty("yPos").setValue(yPos);
	}

	public double getDisplayWidth() {
		return (Double) getMandatoryBehavior().getProperty("displayWidth").getValue();
	}

	public void setDisplayWidth(double displayWidth) {
//		this.displayWidth = displayWidth;
		getMandatoryBehavior().getProperty("displayWidth").setValue(displayWidth);
	}
	
	public double getDisplayHeight() {
		return (Double) getMandatoryBehavior().getProperty("displayHeight").getValue();
	}

	public void setDisplayHeight(double displayHeight) {
//		this.displayHeight = displayHeight;
		getMandatoryBehavior().getProperty("displayHeight").setValue(displayHeight);
	}
	
	/**
	 * @return The String filepath to the image of this GameObject.
	 */
	public String getImagePath() {
		return ((String) this.getMandatoryBehavior().getProperty("imagePath").getValue());
	}
	
	public void setImagePath(String imagePath) {
//		this.imagePath = imagePath;
		this.getMandatoryBehavior().getProperty("imagePath").setValue(imagePath);
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
