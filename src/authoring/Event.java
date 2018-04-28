package authoring;

import java.util.ArrayList;
import java.util.List;

import engine.events.elementevents.ElementEvent;

/*
 * Event is owned by a GameObject
 * Event class holds an event trigger and a list of responses to that trigger
 * 
 * @author: Summer
 */
public class Event {
	
	private List<EventResponse> myResponses;
	private String eventType;
	private String myTrigger;

	public Event() {
		myResponses = new ArrayList<>();
	}
	
	/**
	 * returns the list of event responses
	 */
	public List<EventResponse> getResponses() {
		return myResponses;
	}
	
	public void setEventType(String type) {
		eventType = type;
	}
	
	public String getEventType() {
		return eventType;
	}
	
	/**
	 * returns the trigger
	 */
	public String getTrigger() {
		return myTrigger;
	}
	
	/**
	 * sets the trigger to trigger
	 */
	public void setTrigger(String trigger) {
		myTrigger = trigger;
	}
	
	/**
	 * adds an event response, toAdd, to the list 
	 */
	public void addResponse(EventResponse toAdd) {
		myResponses.add(toAdd);
	}
	
	/**
	 * deletes event response, toDelete, from the list of responses
	 */
	public void deleteResponse(EventResponse toDelete) {
		myResponses.remove(toDelete);
	}
	
	public String toString() {
		String[] name = eventType.split("\\.");
		String use = name[name.length-1];
		return use;
	}
	
	public Event clone() {
		Event event = new Event();
		event.setEventType(this.getEventType());
		event.setTrigger(this.getTrigger());
		for (EventResponse er : this.myResponses) {
			event.addResponse(er.clone());
		}
		
		return event;
	}
}
