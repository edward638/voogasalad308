package gamePlayer.buttons;

import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;

public class ReplayButton extends PlayerButtons{
	
	private ButtonData buttonData;
	
	public ReplayButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height);
		this.buttonData = buttonData;
		this.setText("Restart Current Game");
		setAction();
	}
	
	protected void setAction() {

		this.setOnAction(event -> {
			buttonData.playGame(buttonData.getMostRecentFile());
		});
	}
	
}
