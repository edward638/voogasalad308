package GamePlayer;

import java.util.Map;

public interface HighScores {
	/**
	 * adds a single score to the information of high scores.
	 * @param game
	 * @param score
	 */
	void addScore(String game, int score);
	
	/**
	 * sets up initial score history
	 * @param scores
	 */
	void loadScores(Map<?, ?> scores);
}
