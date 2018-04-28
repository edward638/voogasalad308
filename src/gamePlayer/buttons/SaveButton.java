package gamePlayer.buttons;

import java.io.IOException;
import java.text.SimpleDateFormat;

import data.GameSaver;
import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;

public class SaveButton extends PlayerButtons {

	public SaveButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, "Save Current State");
	}

	protected void setAction() {
		this.setOnAction(event -> {
			String rootFolder = "CALVIN FILL IN HOW TO MAKE THIS STRING = ROOTFOLDER";
			GameSaver gameSaver = new GameSaver(rootFolder);
			try {
				gameSaver.saveGamePart(buttonData.getGamePart());
			} catch (IOException e) {
				System.out.println("e dot print stack trace");
			}
		});
	}

}
