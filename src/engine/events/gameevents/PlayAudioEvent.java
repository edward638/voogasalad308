package engine.events.gameevents;

import engine.GameState;

public class PlayAudioEvent extends GameEvent {
	private String musicPath;
	
	public PlayAudioEvent(String audioPath) {
		musicPath = audioPath;
	}

	@Override
	public void execute(GameState gameState) {
		gameState.getAudioManager().newAudioPlayer(musicPath);
	}

}
