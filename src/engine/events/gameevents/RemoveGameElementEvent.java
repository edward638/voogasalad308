package engine.events.gameevents;

import engine.Engine;
import engine.GameElement;
import engine.GameState;

public class RemoveGameElementEvent extends GameEvent{
	
	private GameElement toRemove;
	
	public RemoveGameElementEvent(GameElement gameElement) {
		toRemove = gameElement;
		System.out.println("Remove Event created");
	}

	@Override
	public void execute(GameState state) {
		state.removeGameElement(toRemove);
		System.out.println("RemoveElement event executed");
	}
}
