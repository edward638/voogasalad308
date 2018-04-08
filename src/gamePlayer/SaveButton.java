package gamePlayer;

import java.text.SimpleDateFormat;

import javafx.scene.control.Button;

public class SaveButton extends Button{
	ConcreteGamePlayer gamePlayer;
	
	public SaveButton(double x, double y, double width, double height, ConcreteGamePlayer gamePlayer) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setMinWidth(width);
		this.setHeight(height);
		this.setText("Save Current State");
		this.gamePlayer = gamePlayer;
		setAction();
	}
	
	private void setAction() {
		this.setOnAction(event -> {
			String timeStamp = new SimpleDateFormat("MM/dd HH:mm").format(new java.util.Date());
		//	gamePlayer.serializer.gameEngineToXML(gamePlayer.currentGameName + " " + "MM/dd HH:mm", gamePlayer.gameState.getDisplayStates());
		});
	}

}
