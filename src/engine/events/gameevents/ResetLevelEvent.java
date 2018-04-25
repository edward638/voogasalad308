package engine.events.gameevents;

import engine.GameState;

public class ResetLevelEvent extends GameEvent{
	private Integer levelToReset;
	public ResetLevelEvent(int levelReset) {
		levelToReset = levelReset;
	}
	
	@Override
	public void execute(GameState gameState) {
		// TODO Auto-generated method stub
		
	}
}
