package engine.events.gameevents;

import engine.Engine;
import engine.GameElement;
import engine.GameState;

public class RemoveGameElementEvent extends GameEvent{
	
	private GameElement toRemove;
	
	public RemoveGameElementEvent(GameElement gameElement) {
		toRemove = gameElement;
	}

	@Override
	public void execute(GameState state, Engine engine) {
		state.removeGameElement(toRemove);
	}
}
