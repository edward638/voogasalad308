package gamePlayer.buttons;

import java.text.SimpleDateFormat;

import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;

public class ClearHighScoresButton extends PlayerButtons {
	
	private ButtonData buttonData;

	public ClearHighScoresButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height);
		this.setText("Clear High Scores");
		this.buttonData = buttonData;
		setAction();
	}

	protected void setAction() {
		this.setOnAction(event -> {
			buttonData.clearHighScores();
		});
	}

}
