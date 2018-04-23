package engine.events.gameevents;

import engine.GameState;

public class AddToScoreEvent extends GameEvent {
	public int score;
	public int characterID;
	
	public AddToScoreEvent(int characterID, int score) {
		this.characterID = characterID;
		this.score = score;
	}

	@Override
	public void execute(GameState gameState) {
		gameState.getMainCharacter().getMainCharacterBehavior.addToScore(score);
	}

}
