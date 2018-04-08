package GamePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * @author Calvin and Jeff - Peer Programming
 *
 */
public class ConcreteHighScores implements HighScores {

	Queue<Score> scoreQueue;
	String gameName;

	private TableView<Score> table;

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
		table = new TableView<Score>();
		setupTableProperties(0, 0, 300, 300);
		
		this.addDummyScores();
	}
	
	private void addDummyScores() {
		addScore("Calvin", 400);
		addScore("Maddy", 450);
		addScore("August", 473);
		addScore("Jeffrey", 324);
		addScore("Gouttham", 934);
		addScore("Summer", 234);
	}

	private void setupTableProperties(double xPos, double yPos, double width, double height) {
		table.setEditable(false);
		table.setLayoutX(xPos);
		table.setLayoutY(yPos);
		table.setMaxWidth(width);
		table.setMaxHeight(height);
		setupTableColumns();
	}

	private void setupTableColumns() {
		table.setEditable(true);
		TableColumn<Score, String> nameCol = new TableColumn("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Score, String>("playerName"));
		nameCol.setMaxWidth(150);
		nameCol.setResizable(false);

		TableColumn<Score, Integer> scoreCol = new TableColumn("Score");
		scoreCol.setCellValueFactory(new PropertyValueFactory<Score, Integer>("score"));
		scoreCol.setMinWidth(150);
		scoreCol.setResizable(false);
		scoreCol.setEditable(true);
		
		table.getColumns().addAll(nameCol, scoreCol);


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

		addToScoreTable();
	}

	private void addToScoreTable() {
		ArrayList<Score> scoreList = new ArrayList(scoreQueue);
		ObservableList<Score> observableScoreList = FXCollections.observableArrayList(scoreList);
		table.setItems(observableScoreList);
	}
	
	/**
	 * method for printing the current high scores for testing
	 */
	public void printQ() {
		int size = scoreQueue.size();
		PriorityQueue<Score> copy = new PriorityQueue(scoreQueue);
		for (int i = 0; i < size; i++) {
			System.out.println(copy.poll());
		}
		System.out.println(scoreQueue.size());
		System.out.println(copy.size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GamePlayer.HighScores#loadScores(java.util.Map)
	 */
	@Override
	public void loadScores(Map<?, ?> scores) {
		// TODO Auto-generated method stub
	}

	public TableView<Score> getScores() {
		return table;
	}

	@Override
	public void clear() {
		scoreQueue.clear();
	}
}
