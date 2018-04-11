package engine.events.gameevents;

import engine.Engine;
import engine.GameState;

public class EmptyGameEvent extends GameEvent{
	public EmptyGameEvent() {
	}

	@Override
	public void execute(GameState state, Engine engine) {
		// Do Nothing
	}
	
}
