package engine.events.gameevents;

import engine.Engine;
import engine.GameElement;
import engine.GameState;
import engine.behaviors.Killable;

public class GameOverEvent extends GameEvent{
	
	private GameElement toRemove;
	
	public GameOverEvent(GameElement gameElement) {
		toRemove = gameElement;
		//System.out.println("Remove Event created");
	}

	@Override
	public void execute(GameState state) {
		System.out.println("GAME OVER EVENT");
		state.resetGame(state.getGameMetaData().getLevel(0));
	}
}
