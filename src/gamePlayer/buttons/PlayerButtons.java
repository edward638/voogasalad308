package gamePlayer.buttons;

import javafx.scene.control.Button;

public abstract class PlayerButtons extends Button {

	protected ButtonData buttonData;

	public PlayerButtons(double x, double y, double width, double height, ButtonData buttonData) {
			this.setLayoutX(x);
			this.setLayoutY(y);
			this.setMinWidth(width);
			this.setHeight(height);
			this.buttonData = buttonData;
	}
	
	abstract protected void setAction();




}
