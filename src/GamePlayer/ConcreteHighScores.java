package GamePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * @author Calvin and Jeff - Peer Programming
 *
 */
public class ConcreteHighScores implements HighScores {

	List<Score> scores;
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
		scores = new ArrayList<Score>();
		gameName = game;
		table = new TableView<Score>();
		setupTableProperties(970, 30, 235, 265);

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
		if (scores.size() < 10) {
			scores.add(newScore);
		} else {
			if (scores.get(0).score < score) {
				scores.remove(0);
				scores.add(newScore);
			}
		}
		Collections.sort(scores, new Score.ScoreComparator());
		addToScoreTable();
	}

	private void addToScoreTable() {

		ObservableList<Score> observableScoreList = FXCollections.observableArrayList(scores);
		Collections.reverse(observableScoreList);
		table.setItems(observableScoreList);
	}

	/**
	 * method for printing the current high scores for testing
	 */
	public void printQ() {
		int size = scores.size();
		for (int i = 0; i < size; i++) {
			System.out.println(scores.get(i));
		}

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
		scores.clear();
	}
//	
//	public static void main(String[] args) {
//		ConcreteHighScores chs = new ConcreteHighScores("test");
//		chs.addScore("hi2", 1);
//		chs.addScore("hi3", 2);
//		chs.addScore("hi4", 3);
//		chs.addScore("hi5", 4);
//		chs.addScore("hi6", 5);
//		chs.addScore("hi2", 6);
//		chs.addScore("hi3", 7);
//		chs.addScore("hi4", 8);
//		chs.addScore("hi5", 9);
//		chs.addScore("hi6", 10);
//		chs.addScore("hi5", 11);
//		chs.addScore("h6", 12);
//		chs.printQ();
//	}

}
