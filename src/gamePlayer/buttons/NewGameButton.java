package gamePlayer.buttons;

import java.io.File;
import java.nio.file.Paths;

import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class NewGameButton extends PlayerButtons {

	public NewGameButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData);
		this.setText("New Game");
		setAction();
	}

	protected void setAction() {

		this.setOnAction(event -> {
			
			GameSelector gs = new GameSelector(buttonData);
						
			buttonData.addToRoot(gs);
			// remove the game from the root or have a back option in the game selector which removes gameSelector
			
		});

	}

}
