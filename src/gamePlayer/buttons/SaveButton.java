package gamePlayer.buttons;

import java.io.IOException;
import java.text.SimpleDateFormat;

import data.GameSaver;
import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;

public class SaveButton extends Button {

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
