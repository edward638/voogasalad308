package engine.events.gameevents;

import data.GameLoader;
import engine.DisplayState;
import engine.Engine;
import engine.GameElement;
import engine.GameState;

public class ChangeLevelEvent extends GameEvent{
	
private Integer level;
	
	public ChangeLevelEvent(Integer level) {
		this.level = level;
	}

	@Override
	public void execute(GameState gameState) {
		System.out.println("CHANGE LEVEL EVENT");
		gameState.setState(gameState.getGameMetaData().getLevel(level));
	}
}
