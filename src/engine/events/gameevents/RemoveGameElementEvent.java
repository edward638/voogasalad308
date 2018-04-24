package engine.events.gameevents;

import engine.Engine;
import engine.GameElement;
import engine.GameState;
import engine.behaviors.MainCharacter;

public class RemoveGameElementEvent extends GameEvent{
	
	private GameElement toRemove;
	
	public RemoveGameElementEvent(GameElement gameElement) {
		toRemove = gameElement;
		System.out.println("Remove Event created");
	}

	@Override
	public void execute(GameState state) {
		if (!toRemove.hasBehavior(MainCharacter.class)) {
			state.removeGameElement(toRemove);
		}
		System.out.println("RemoveElement event executed");
	}
}