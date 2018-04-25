package engine.events.gameevents;

import engine.DisplayState;
import engine.Engine;
import engine.GamePart;

public abstract class GameEvent {
	
	public abstract void execute(GamePart gamePart);
	
}
