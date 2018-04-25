package engine.events.gameevents;

import engine.GameState;

public class ChangePartEvent extends GameEvent{

	private String partID;
	private int portalID;

	public ChangePartEvent(String partID, int portalID) {
		this.partID = partID;
		this.portalID = portalID;
	}

	@Override
	public void execute(GameState gameState) {
		gameState.changeCurrentGamePart(partID, portalID);
	}
}
