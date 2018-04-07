package GamePlayer;

import javafx.scene.control.Button;

public class replayButton extends Button{
	
	public replayButton(double x, double y, double width, double height) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setText("save");
		setAction();
	}
	
	private void setAction() {
//		GamePlayerActionCreator.saveGame();
//		or
//		gameEngine.save(); 
		
		//depending on what we decide on = gameplayeractioncreator would be the command design pattern
	}
	
}
