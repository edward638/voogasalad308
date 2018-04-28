package gamePlayer.buttons;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public abstract class PlayerButtons extends Button {

	protected ButtonData buttonData;
	private String font = "Din Alternate";

	public PlayerButtons(double x, double y, double width, double height, ButtonData buttonData, String text) {
			this.setLayoutX(x);
			this.setLayoutY(y);
			this.setMinWidth(width);
			this.setHeight(height);
			this.setText(text);
			this.setFont(Font.font(font, 14));
			this.buttonData = buttonData;
			setAction();
	}
	
	abstract protected void setAction();




}
