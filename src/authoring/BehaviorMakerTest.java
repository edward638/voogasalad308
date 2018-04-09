package authoring;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class BehaviorMakerTest {

	
	@Test
	void testGameObjectAddBehavior() {
		GameElement go = new GameElement(new Behavior());
		assertEquals(1, go.getBehaviors().size());
	}
	
	@Test
	void testGameObjectCopy() {
		Behavior behave = new Behavior("Moveable", new HashSet<Property>());
		GameElement go = new GameElement(behave);
		GameElement goCopy = new GameElement(go);
		assertEquals("Moveable", goCopy.getBehavior("Moveable").getName());
	}
	
	@Test
	void testEventSetTrigger() {
		Event newEvent = new Event();
		newEvent.setTrigger("Collision with block");
		assertEquals("Collision with block", newEvent.getTrigger());
	}
	
	@Test
	void testEventsAddResponse() {
		Event myEvent = new Event();
		EventResponse myResponse = new EventResponse();
		myResponse.setMyContent("move right");
		myEvent.addResponse(myResponse);
		assertEquals("move right", myEvent.getResponses().get(0).getMyContent());
	}

}
