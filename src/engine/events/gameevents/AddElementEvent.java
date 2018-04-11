package engine.events.gameevents;

import engine.Engine;
import engine.GameElement;
import engine.GameState;

public class AddElementEvent extends GameEvent {
	private GameElement toAdd;
	
	AddElementEvent(GameElement gameElement) {
		toAdd = gameElement;
	}

	@Override
	public void execute(GameState state, Engine engine) {
		state.addGameElement(toAdd);
	}

}
