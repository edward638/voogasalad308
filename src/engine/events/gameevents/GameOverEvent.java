package engine.events.gameevents;

import engine.Engine;
import engine.GameElement;
import engine.GameState;

public class GameOverEvent extends GameEvent{
	
	private GameElement toRemove;
	
	public GameOverEvent(GameElement gameElement) {
		toRemove = gameElement;
		System.out.println("Remove Event created");
	}

	@Override
	public void execute(GameState state) {
		state.removeAllElements();
		state.setState(state.getGameMetaData().getLevel(0));
		System.out.println("GameOver event executed");
	}
}
