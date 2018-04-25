package engine.events.gameevents;

import engine.GameState;

public class ResetLevelEvent {
	private Integer levelToReset;
	public ResetLevelEvent(int levelReset) {
		levelToReset = levelReset;
	}
	public void execute(GameState gameState) {
		System.out.println("IMPLEMENT CODE TO RESET LEVEL IN METADATA");
	}
}
