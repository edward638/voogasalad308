package engine.events.gameevents;

import engine.Engine;
import engine.GameState;

public abstract class GameEvent {
	
	public abstract void execute(GameState state, Engine engine);
	
}
