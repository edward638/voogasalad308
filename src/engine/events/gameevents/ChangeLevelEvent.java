package engine.events.gameevents;

import data.GameLoader;
import engine.DisplayState;
import engine.Engine;
import engine.GameElement;
import engine.GameState;

public class ChangeLevelEvent extends GameEvent{
	
private Integer level;
	
	ChangeLevelEvent(Integer level) {
		this.level = level;
	}

	@Override
	public void execute(DisplayState displayState) {
		GameLoader loader = new GameLoader(displayState.getGameState().getGameName());
		GameState gameState = loader.convertScene(loader.getGameScenes().get(level));
		displayState.getGameState.setGameState();
	}

	
}
