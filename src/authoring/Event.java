package authoring;

import java.util.List;

public class Event {
	
	private List<EventResponse> myResponses;
	private String myTrigger;

	public Event() {
		
	}
	
	public List<EventResponse> getResponses() {
		return myResponses;
	}
	
	public String getTrigger() {
		return myTrigger;
	}
	
	public void setTrigger(String trigger) {
		myTrigger = trigger;
	}
	
	public void addResponse(EventResponse toAdd) {
		myResponses.add(toAdd);
	}
	
	public void deleteResponse(EventResponse toDelete) {
		myResponses.remove(toDelete);
	}
	
}
