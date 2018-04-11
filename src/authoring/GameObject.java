package authoring;

import java.util.HashSet;
import java.util.Set;

public class GameObject {
	
	private String myName;
	private Set<Behavior> myBehaviors;
	private Set<Event> myEvents;
	private BehaviorFactory myBehaviorFactory;
	
	public GameObject() {
		myBehaviors = new HashSet<>();
		myEvents = new HashSet<>();
		myBehaviorFactory = new BehaviorFactory();
	}

	public GameObject(Behavior initBehavior) {
		this();
		myBehaviors.add(initBehavior);
	}
	
	public GameObject(String initBehavior) {
		this();
		addBehavior(initBehavior);
	}
	
	public GameObject(GameObject toCopy) {
		this();
		myBehaviors = toCopy.getBehaviors();
		myEvents = toCopy.getEvents();
	}
	
	public void addBehavior(String behaviorToAdd) {
		Behavior newBehavior = myBehaviorFactory.makeBehavior(behaviorToAdd);
		myBehaviors.add(newBehavior);
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
