package gamePlayer.buttons;

import java.io.IOException;
import java.text.SimpleDateFormat;

import data.GameSaver;
import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;

public class SaveButton extends PlayerButtons {

	private ButtonData buttonData;

	public SaveButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height);
		this.buttonData = buttonData;
		this.setText("Save Current State");
		setAction();
	}

	protected void setAction() {
		this.setOnAction(event -> {
			String rootFolder = "CALVIN FILL IN HOW TO MAKE THIS STRING = ROOTFOLDER";
			GameSaver gameSaver = new GameSaver(rootFolder);
			try {
				gameSaver.saveGameState(buttonData.getGameState());
			} catch (IOException e) {
				System.out.println("e dot print stack trace");
			}
		});
	}

}
