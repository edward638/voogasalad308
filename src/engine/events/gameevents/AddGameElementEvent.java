package engine.events.gameevents;

import engine.GameElement;
import engine.GamePart;

public class AddGameElementEvent extends GameEvent{
	
	private GameElement toAdd;
	
	public AddGameElementEvent(GameElement ge) {
		toAdd = ge;
	}

	@Override
	public void execute(GamePart part) {
		part.addGameElement(toAdd);
	}
}
