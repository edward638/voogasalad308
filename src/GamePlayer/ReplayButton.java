package GamePlayer;

import javafx.scene.control.Button;

public class ReplayButton extends Button{
	
	ConcreteGamePlayer gamePlayer;
	
	public ReplayButton(double x, double y, double width, double height, ConcreteGamePlayer gamePlayer) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setMinWidth(width);
		this.setHeight(height);
		this.setText("Restart Current Game");
		this.gamePlayer = gamePlayer;
		setAction();
	}
	
	private void setAction() {

		this.setOnAction(event -> {
			gamePlayer.playGame(gamePlayer.getMostRecentFile());
		});
	}
	
}
