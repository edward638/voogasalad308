package gamePlayer;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ConcreteHUD extends Pane implements HUD {

	String gameName;
	int currentScore;
	int livesRemaining;
	Map<?, ?> otherInfo;

	public ConcreteHUD(String name) {
		setupPane();
		gameName = name;
	}

	/**
	 * setting up front end JavaFX of the pane
	 */
	private void setupPane() {
		this.setLayoutX(30);
		this.setLayoutY(30);
		this.setWidth(800);
		this.setHeight(650);
		createHUDLabels();

		this.addDummyValues();
	}

	private void createHUDLabels() {

		setupScoreText();
		setupLivesText();

	}

	private void setupLivesText() {
		Label livesText = new Label("Lives: " + livesRemaining);
		livesText.setLayoutX(400);
		livesText.setLayoutY(0);

		this.getChildren().add(livesText);

	}

	private void setupScoreText() {
		Label scoreText = new Label("Score: " + currentScore);
		scoreText.setLayoutX(0);
		scoreText.setLayoutY(0);
		this.getChildren().add(scoreText);

	}

	private void addDummyValues() {
		HashMap other = new HashMap<String, Integer>();
		other.put("Kills", 12);
		other.put("Ammo", 8);
		updateInfo(758, 7, other);
	}

	@Override
	public void updateInfo(int score, int lives, Map<?, ?> OtherInfo) {
		currentScore = score;
		livesRemaining = lives;
		otherInfo = OtherInfo;
	}

}
