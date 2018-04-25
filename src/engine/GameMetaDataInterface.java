package engine;

public interface GameMetaDataInterface {
	public void setPlayerUpdater(PlayerUpdater playerUpdater);
	public GameState getCurrentLevel();
	public int getCurrentLevelNumber();
}
