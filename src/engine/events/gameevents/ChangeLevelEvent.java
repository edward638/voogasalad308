package engine.events.gameevents;

import engine.Engine;
import engine.GameElement;
import engine.GameState;

public class ChangeLevelEvent extends GameEvent{
	
private Integer level;
	
	ChangeLevelEvent(Integer level) {
		this.level = level;
	}

	@Override
	public void execute(GameState state, Engine engine) {
		engine.changeLevel(level);
		
	}

	
}
