package gamePlayer.buttons;

import javafx.scene.control.Button;

public abstract class PlayerButtons extends Button {



	public PlayerButtons(double x, double y, double width, double height) {
			this.setLayoutX(x);
			this.setLayoutY(y);
			this.setMinWidth(width);
			this.setHeight(height);
	}
	
	abstract protected void setAction();




}
