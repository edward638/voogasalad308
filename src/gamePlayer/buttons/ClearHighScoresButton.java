package gamePlayer.buttons;

import java.text.SimpleDateFormat;

import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;

public class ClearHighScoresButton extends Button{

	private ButtonData buttonData;
	
	public ClearHighScoresButton(double x, double y, double width, double height, ButtonData buttonData) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setMinWidth(width);
		this.setHeight(height);
		this.setText("Clear High Scores");
		this.buttonData = buttonData;
		setAction();
	}
	
	private void setAction() {
		this.setOnAction(event -> {
			buttonData.clearHighScores();
		});
	}

}
