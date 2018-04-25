package engine.events.gameevents;

import engine.GamePart;

public class ResetLevelEvent {
	private Integer levelToReset;
	public ResetLevelEvent(int levelReset) {
		levelToReset = levelReset;
	}
	public void execute(GamePart gamePart) {
		System.out.println("IMPLEMENT CODE TO RESET LEVEL IN METADATA");
	}
}
