package GamePlayer;

import java.util.HashMap;
import java.util.Map;


import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ConcreteHUD extends HBox implements HUD{
	
	int currentScore;
	int livesRemaining;
	Map<?, ?> otherInfo;
	
	public ConcreteHUD() {
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
		Rectangle rect = new Rectangle(0,0,900,100);
		rect.setFill(Color.BLACK);
		this.getChildren().add(rect);
		
		this.addDummyValues();
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
