package engine;

import engine.behaviors.MainCharacter;

public class GameMetaData implements GameMetaDataInterface {
	private GameState gameState;

	public GameMetaData(GameState currentGameState) {
		gameState = currentGameState;
	}

	@Override
	public String getCurrentLevelID() {
		return gameState.getCurrentGameLevel().getGameLevelID();
	}

	@Override
	public int getMainCharacterLives() {
		return ((MainCharacter) gameState.getCurrentGamePart().getMainCharacter().getBehavior(MainCharacter.class)).getLives();
	}
}
