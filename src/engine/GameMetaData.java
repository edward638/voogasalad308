package engine;

import java.util.ArrayList;
import java.util.List;

import authoring.GameScene;
import engine.authouringconversion.Converter2;
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

	@Override
	public List<GameScene> getGameScenes() {
		Converter2 converter = new Converter2();
		List<GameScene> toReturn = new ArrayList<GameScene>();
		for (GamePart part : gameState.getAllGameParts()) {
			toReturn.add(converter.gamePart2GameScene(part));
		}
		return toReturn;
	}
}
