package engine.events.gameevents;

import engine.GameElement;
import engine.GameState;

public class AddGameElementEvent extends GameEvent{
	
	private GameElement toAdd;
	
	public AddGameElementEvent(GameElement ge) {
		toAdd = ge;
	}

	@Override
	public void execute(GameState state) {
		GameElement newInstance = new GameElement(toAdd);
		state.addGameElement(newInstance);
	}
}
