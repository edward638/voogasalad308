package authoring.tests;

import java.util.HashSet;
import java.util.Set;

import authoring.AuthBehavior;
import authoring.Event;
import authoring.EventResponse;
import authoring.GameObject;
import authoring.Property;

public class EventTranslationTest {

	public static void main(String args[]) {
		
		//moveable character that will jump on the w key
		GameObject go = new GameObject();
		go.setName("myObject");
		Property x = new Property("xVel", Double.class);
		x.setValue(0.0);
		Property y = new Property("yVel", Double.class);
		y.setValue(1.0);
		Set<Property> moveProps = new HashSet<>();
		moveProps.add(y);
		moveProps.add(x);
 		AuthBehavior movableCharacter = new AuthBehavior("MovableCharacter", moveProps);
		go.addBehavior(movableCharacter);
		Event keyInput = new Event();
		keyInput.setEventType("engine.events.elementevents.KeyInputEvent");
		keyInput.setTrigger("w");
		//this will be changed to GroovyAction 
		EventResponse response = new EventResponse();
		response.setMyContent("myObject.getBehaviors('MovableCharacter').jump()");
		keyInput.addResponse(response);
		go.addEvent(keyInput);
		
	}
	
}
