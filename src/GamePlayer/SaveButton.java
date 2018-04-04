package GamePlayer;

import javafx.scene.control.Button;

public class SaveButton extends Button{
	
	public SaveButton(double x, double y, double width, double height) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setWidth(width);
		this.setHeight(height);
		setAction();
	}
	
	private void setAction() {
		GamePlayerActionCreator.saveGame();
		or
		gameEngine.save(); 
		
		//depending on what we decide on = gameplayeractioncreator would be the command design pattern
	}
		

}
