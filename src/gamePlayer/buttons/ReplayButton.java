package gamePlayer.buttons;

import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;

public class ReplayButton extends Button{
	
	private ButtonData buttonData;
	
	public ReplayButton(double x, double y, double width, double height, ButtonData buttonData) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setMinWidth(width);
		this.setHeight(height);
		this.setText("Restart Current Game");
		this.buttonData = buttonData;
		setAction();
	}
	
	private void setAction() {

		this.setOnAction(event -> {
			buttonData.playGame(buttonData.getMostRecentFile());
		});
	}
	
}
