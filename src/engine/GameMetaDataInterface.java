package engine;

import java.util.List;

import authoring.GameScene;

public interface GameMetaDataInterface {
	//public void setPlayerUpdater(PlayerUpdater playerUpdater);
	public String getCurrentLevelID();
	public int getMainCharacterLives();
	public List<GameScene> getGameScenes();
}
