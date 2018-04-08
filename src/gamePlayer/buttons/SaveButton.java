package gamePlayer.buttons;

import java.text.SimpleDateFormat;

import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;

public class SaveButton extends Button{

	private ButtonData buttonData;
	
	public SaveButton(double x, double y, double width, double height, ButtonData buttonData) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setMinWidth(width);
		this.setHeight(height);
		this.setText("Save Current State");
		this.buttonData = buttonData;
		setAction();
	}
	
	private void setAction() {
		this.setOnAction(event -> {
			String timeStamp = new SimpleDateFormat("MM/dd HH:mm").format(new java.util.Date());
			//buttonData.getSerializer().gameEngineToXML(buttonData.getCurrentGameName() + " " + "MM/dd HH:mm", buttonData.getGameState().getDisplayStates());
		});
	}

}
