package engine.events.gameevents;

import engine.GameState;

public class ChangePartEvent extends GameEvent{

	private String partID;

	public ChangePartEvent(String partID) {
		this.partID = partID;
	}

	@Override
	public void execute(GameState gameState) {
		gameState.changeCurrentGamePart(partID);
	}
}
