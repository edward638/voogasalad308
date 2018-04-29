package gamePlayer.buttons;

import java.io.IOException;
import java.text.SimpleDateFormat;

import data.GameSaver;
import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;

/**
 * a button for saving the game
 * @author jeffreyli
 *
 */
public class SaveButton extends PlayerButtons {

	public SaveButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, "Save Current State");
	}

	protected void setAction() {
		this.setOnAction(event -> {
			String rootFolder = "Demo308";
			GameSaver gameSaver = new GameSaver(rootFolder);
//			try {
//				gameSaver.saveGameState(buttonData.getGameState());
//			} catch (IOException e) {
//				System.out.println("e dot print stack trace");
//			}
		});
	}

}
