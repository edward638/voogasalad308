package engine.events.gameevents;

import engine.GameElement;
import engine.GameState;

public class RemoveGameElementEvent extends GameEvent{
	
	private GameElement toRemove;
	
	public RemoveGameElementEvent(GameElement ge) {
		toRemove = ge;
	}

	@Override
	public void execute(GameState state) {
		state.removeGameElement(toRemove);
	}
}
