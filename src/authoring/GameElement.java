package authoring;

import java.util.HashSet;
import java.util.Set;

public class GameElement {
	
	private Set<Behavior> myBehaviors;
	private Set<Event> myEvents;
	
	//add basic behavior elements to the constructor
	public GameElement(int x, int y, String image) {
		myBehaviors = new HashSet<>();
		myEvents = new HashSet<>();
		addBasicBehavior(x, y, image);
	}
	
	private void addBasicBehavior(int x, int y, String image) {
		Property xLoc = new Property("xLoc", x);
		Property yLoc = new Property("yLoc", y);
		Property image = new Property("image", image);
		List<Property> basicProps = new ArrayList<>();
		basicProps.add(xLoc); 
		basicProps.add(yLoc); 
		basicProps.add(image); 
		Behavior basic = new Behavior(basicProps);
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
