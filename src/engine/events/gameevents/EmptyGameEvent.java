package engine.events.gameevents;

import engine.GameState;

public class EmptyGameEvent extends GameEvent{
	public EmptyGameEvent() {
	}

	@Override
	public void execute(GameState state) {
		// Do Nothing
	}
	
}
