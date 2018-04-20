package engine.events.gameevents;

import engine.Engine;
import engine.GameElement;
import engine.GameState;

public class AddElementEvent extends GameEvent {
	private GameElement toAdd;
	
	public AddElementEvent(GameElement gameElement) {
		toAdd = gameElement;
	}

	@Override
	public void execute(GameState state) {
		state.addGameElement(toAdd);
		System.out.println("GameElement added to state");
	}

}
