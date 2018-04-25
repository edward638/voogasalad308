package engine;

public interface GameMetaDataInterface {
	public void setPlayerUpdater(PlayerUpdater playerUpdater);
	public GamePart getCurrentLevel();
	public int getCurrentLevelNumber();
}
