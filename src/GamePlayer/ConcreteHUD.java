package GamePlayer;

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
		this.setLayoutX(90);
		this.setLayoutY(90);
		this.setWidth(90);
		this.setHeight(20);
		Rectangle rect = new Rectangle(0,0,500,500);
		rect.setFill(Color.BLACK);
		this.getChildren().add(rect);
	}

	@Override
	public void updateInfo(int score, int lives, Map<?, ?> OtherInfo) {
		currentScore = score;
		livesRemaining = lives;
		otherInfo = OtherInfo;
	}

}
