package engine.events.gameevents;

import engine.DisplayState;
import engine.Engine;
import engine.GameState;

public abstract class GameEvent {
	
	public abstract void execute(DisplayState displayState);
	
}
