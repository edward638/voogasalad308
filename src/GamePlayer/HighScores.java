package GamePlayer;

import java.util.Map;

public interface HighScores {
	/**
	 * adds a single score to the information of high scores.
	 * @param game - name of player
	 * @param score - score amount
	 */
	void addScore(String name, int score);
	
	/**
	 * sets up initial score history
	 * @param scores - a map of name of player to corresponding score
	 */
	void loadScores(Map<?, ?> scores);
}
