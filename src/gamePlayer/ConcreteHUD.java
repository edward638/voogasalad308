package gamePlayer;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ConcreteHUD extends Pane implements HUD {

	String gameName;
	int currentScore;
	int livesRemaining;
	Map<String, ?> otherInfo;
	private int xLabel = 0;
	private int yLabel = 0;
	
	public ConcreteHUD(String name) {
		gameName = name;
		setupPane();

	}

	/**
	 * setting up front end JavaFX of the pane
	 */
	private void setupPane() {
		this.setLayoutX(30);
		this.setLayoutY(30);
		this.setWidth(800);
		this.setHeight(650);

		this.addDummyValues();
		setupHUDText();
	}
	
	private void setupHUDText() {
		System.out.println(gameName);
		Label nameText = new Label(gameName);
		nameText.setTextFill(Color.BLACK);
		nameText.setLayoutX(xLabel);
		nameText.setLayoutY(yLabel - 25);
		nameText.setFont(new Font(20));
		this.getChildren().add(nameText);
		Label scoreText = new Label("Score: " + currentScore);
		scoreText.setTextFill(Color.WHITE);
		scoreText.setLayoutX(xLabel);
		xLabel = xLabel + 150;
		scoreText.setLayoutY(yLabel);
		scoreText.setFont(new Font(20));
		this.getChildren().add(scoreText);
		Label livesText = new Label("Lives: " + livesRemaining);
		livesText.setTextFill(Color.WHITE);

		livesText.setLayoutX(xLabel);
		xLabel = xLabel + 150;
		livesText.setLayoutY(yLabel);
		livesText.setFont(new Font(20));
		this.getChildren().add(livesText);
		for (String label : otherInfo.keySet()) {
			if ((xLabel + 100) > 700) {
				xLabel = 0;
				yLabel = yLabel + 25;
			}
			Label otherText = new Label(label + ": " + otherInfo.get(label));
			otherText.setTextFill(Color.WHITE);

			otherText.setLayoutX(xLabel);
			xLabel = xLabel + 150;
			otherText.setLayoutY(yLabel);
			otherText.setFont(new Font(20));
			this.getChildren().add(otherText);
		}
	}

	private void addDummyValues() {
		HashMap other = new HashMap<String, Integer>();
		other.put("Kills", 12);
		other.put("Ammo", 8);
		other.put("Ammo1", 8);
		other.put("Ammo2", 8);
		other.put("Ammo3", 8);
		other.put("Ammo4", 8);
		other.put("Ammo5", 8);

		other.put("Current Player", "Jeffrey");
		updateInfo(758, 7, other);
	}

	@Override
	public void updateInfo(int score, int lives, Map<String, ?> OtherInfo) {
		currentScore = score;
		livesRemaining = lives;
		otherInfo = OtherInfo;
	}

}
