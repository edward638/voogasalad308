package authoring;

import java.util.ArrayList;
import java.util.List;

/*
 * Event is owned by a GameObject
 * Event class holds an event trigger and a list of responses to that trigger
 * 
 * @author: Summer
 */
public class Event {
	
	private List<EventResponse> myResponses;
	private String myTrigger;

	public Event() {
		myResponses = new ArrayList<>();
	}
	
	/*
	 * returns the list of event responses
	 */
	public List<EventResponse> getResponses() {
		return myResponses;
	}
	
	/*
	 * returns the trigger
	 */
	public String getTrigger() {
		return myTrigger;
	}
	
	/*
	 * sets the trigger to trigger
	 */
	public void setTrigger(String trigger) {
		myTrigger = trigger;
	}
	
	/*
	 * adds an event response, toAdd, to the list 
	 */
	public void addResponse(EventResponse toAdd) {
		myResponses.add(toAdd);
	}
	
	/*
	 * deletes event response, toDelete, from the list of responses
	 */
	public void deleteResponse(EventResponse toDelete) {
		myResponses.remove(toDelete);
	}
	
}
