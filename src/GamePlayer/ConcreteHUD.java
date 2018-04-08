package GamePlayer;

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

public class ConcreteHUD extends Pane implements HUD{
	
	String gameName;
	int currentScore;
	int livesRemaining;
	Map<String, ?> otherInfo;
	private int xLabel = 0;
	private int yLabel = 0;
	
	
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
		//Rectangle rect = new Rectangle(0,0,900,100);
		//rect.setFill(Color.ALICEBLUE);

		//this.getChildren().add(rect);
		
		this.addDummyValues();
		setupHUDText();
	}
	
	private void setupHUDText() {
		Label nameText = new Label(gameName);
		nameText.setLayoutX(xLabel);
		xLabel = xLabel + 150;
		nameText.setLayoutY(yLabel);
		nameText.setFont(new Font(20));
		this.getChildren().add(nameText);
		Label scoreText = new Label("Score: " + currentScore);
		scoreText.setLayoutX(xLabel);
		xLabel = xLabel + 150;
		scoreText.setLayoutY(yLabel);
		scoreText.setFont(new Font(20));
		this.getChildren().add(scoreText);
		Label livesText = new Label("Lives: " + livesRemaining);
		livesText.setLayoutX(xLabel);
		xLabel = xLabel + 150;
		livesText.setLayoutY(yLabel);
		livesText.setFont(new Font(20));
		this.getChildren().add(livesText);
		for (String label : otherInfo.keySet()) {
			Label otherText = new Label(label + ": " + otherInfo.get(label));
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
