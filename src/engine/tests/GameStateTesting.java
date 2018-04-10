package engine.tests;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import engine.EventManager;
import engine.GameState;
import engine.events.elementevents.TimeEvent;

class GameStateTesting {
	
	private GameState state;
	private EventManager manager;
	
	
	@Before
	public void setUp() {
		state = new ModelGameState().getState();
		manager = new EventManager();
	}
	
	@Test
	public void testMove() {
		state = new ModelGameState().getState();
		System.out.println("Print Something");
		System.out.println(state.getElements());
		state.getElements().stream().forEach(e -> e.reportProperties());
		manager.processInputEvent(new TimeEvent(5.0), state);
	}
}
