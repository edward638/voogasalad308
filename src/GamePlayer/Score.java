package GamePlayer;

import java.util.Comparator;

public class Score {

	String playerName;
	int score;

	public Score(String name, int score) {
		playerName = name;
		this.score = score;
	}
	public String toString() {
		return playerName +": " + score;
	}


	public static class ScoreComparator implements Comparator<Score> {

		@Override
		public int compare(Score score1, Score score2) {
			if (score1.score > score2.score) {
				return 1;
			}
			if (score1.score < score2.score) {
				return -1;
			}
			return 0;
		}
		

	}
}
