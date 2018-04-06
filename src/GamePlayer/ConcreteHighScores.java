package GamePlayer;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author Calvin and Jeff - Peer Programming
 *
 */
public class ConcreteHighScores implements HighScores {

	Queue<Score> scoreQueue;
	String gameName;

	/**
	 * initializes a "ConcreteHighScore" by creating a new priorityqueue for the
	 * scores
	 * 
	 * @param game
	 *            - a string that represents the game currently being played - the
	 *            high score is matched to the specific game.
	 */
	public ConcreteHighScores(String game) {
		scoreQueue = new PriorityQueue<Score>(new Score.ScoreComparator());
		gameName = game;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GamePlayer.HighScores#addScore(java.lang.String, int)
	 */
	@Override
	public void addScore(String name, int score) {
		Score newScore = new Score(name, score);
		if (scoreQueue.size() < 10) {
			scoreQueue.add(newScore);
		} else {
			System.out.println(scoreQueue.poll().score);
			scoreQueue.add(newScore);

		}
	}

	// public void printQ() {
	// int queueSize = scoreQueue.size();
	// for (int i = 0; i < queueSize; i++) {
	// System.out.println("hi");
	// System.out.println(scoreQueue.poll().toString());
	// }
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see GamePlayer.HighScores#loadScores(java.util.Map)
	 */
	@Override
	public void loadScores(Map<?, ?> scores) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		ConcreteHighScores chs = new ConcreteHighScores("test");
		chs.addScore("hi2", 1);
		chs.addScore("hi3", 2);
		chs.addScore("hi4", 3);
		chs.addScore("hi5", 4);
		chs.addScore("hi6", 5);
		chs.addScore("hi2", 6);
		chs.addScore("hi3", 7);
		chs.addScore("hi4", 8);
		chs.addScore("hi5", 9);
		chs.addScore("hi6", 10);
		chs.addScore("hi5", 11);
		chs.addScore("h6", 12);
	}
}
