package authoring;

import java.util.HashSet;
import java.util.Set;

public class GameElement {
	
	private String myName;
	private Set<Behavior> myBehaviors;
	private Set<Event> myEvents;
	
	//add basic behavior elements to the constructor
	public GameElement(double x, double y, String image) {
		myBehaviors = new HashSet<>();
		myEvents = new HashSet<>();
		addBasicBehavior(x, y, image);
	}
	
	public GameElement(Behavior basic) {
		myBehaviors = new HashSet<>();
		myEvents = new HashSet<>();
		myBehaviors.add(basic);
	}
	
	private void addBasicBehavior(double x, double y, String image) {
		Property xLoc = new Property("xLoc", x);
		Property yLoc = new Property("yLoc", y);
		Property myImage = new Property("image", image);
		Set<Property> basicProps = new HashSet<>();
		basicProps.add(xLoc); 
		basicProps.add(yLoc); 
		basicProps.add(myImage); 
		Behavior basic = new Behavior("Basic", basicProps);
		myBehaviors.add(basic);
	}
	
	public GameElement(GameElement toCopy) {
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
	
	
}
