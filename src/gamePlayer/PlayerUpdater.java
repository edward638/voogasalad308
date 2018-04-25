package gamePlayer;

import java.util.Map;

public interface PlayerUpdater {
	
	public void addHighScore(int score);
	
	public void updateHUD(Map<String, Object> info);	
}
