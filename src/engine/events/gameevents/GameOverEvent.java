package engine.events.gameevents;

import engine.GameElement;
import engine.GameState;

public class GameOverEvent extends GameEvent{
	
	private GameElement toRemove;
	
	public GameOverEvent(GameElement gameElement) {
		
		//System.out.println("Remove Event created");
	}

	@Override
	public void execute(GameState gameState) {
		gameState.resetAllLevels();
	}
}
