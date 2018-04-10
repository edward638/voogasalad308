package authoring;

import java.util.HashSet;
import java.util.Set;

public class GameObject {
	
	private String myName;
	private Set<Behavior> myBehaviors;
	private Set<Event> myEvents;
	private BehaviorFactory myBehaviorFactory;
	
	//add basic behavior elements to the constructor
	
	//do we need this?
	public GameObject(double x, double y, String image) {
		myBehaviors = new HashSet<>();
		myEvents = new HashSet<>();
//		addBasicBehavior(x, y, image);
	}
	
	public GameObject(Behavior basic) {
		myBehaviors = new HashSet<>();
		myEvents = new HashSet<>();
		myBehaviors.add(basic);
	}
	
	public GameObject() {
		myBehaviors = new HashSet<>();
		myEvents = new HashSet<>();
	}
	
//	BehaviorFactory should do this
//	private void addBasicBehavior(Double x, Double y, String image) {
//		Property xLoc = new Property("xLoc", x.getClass());
//		Property yLoc = new Property("yLoc", y.getClass());
//		Property myImage = new Property("image", image);
//		Set<Property> basicProps = new HashSet<>();
//		basicProps.add(xLoc); 
//		basicProps.add(yLoc); 
//		basicProps.add(myImage); 
//		Behavior basic = new Behavior("Basic", basicProps);
//		myBehaviors.add(basic);
//	}
	
	public GameObject(GameObject toCopy) {
		myBehaviors = toCopy.getBehaviors();
		myEvents = toCopy.getEvents();
	}
	
	//each game object will have properties that describe how it behaves. 
	public void addBehavior(Behavior behaviorToAdd) {
		myBehaviors.add(behaviorToAdd);
	}
	
	//the user can remove a behavior after assigning it
	public void removeBehavior(Behavior behaviorToRemove) {
		myBehaviors.remove(behaviorToRemove);
	}
	
	public void addEvent(Event toAdd) {
		myEvents.add(toAdd);
	}
	
	public void deleteEvent(Event toDelete) {
		if(myEvents.contains(toDelete)) {
			myEvents.remove(toDelete);
		}
	}
	
	public void setName(String name) {
		myName = name;
	}
	
	public String getName() {
		return myName;
	}

	public Set<Event> getEvents(){
		return myEvents;
	}

	//returns the list of all behaviors associated with the object
	public Set<Behavior> getBehaviors() {
		return myBehaviors;
	}
	
	public Behavior getBehavior(String behavior) {
		try {
			for(Behavior curr: myBehaviors) {
				if (curr.getName().equals(behavior)) {
					return curr;
				}
			}
			throw new Exception();
		} catch (Exception e) {
			System.out.println("Tried to access a behavior that this object does not have");
		}
		return new Behavior();
	}
	
	public String toString() {
		return myName;
	}
}
