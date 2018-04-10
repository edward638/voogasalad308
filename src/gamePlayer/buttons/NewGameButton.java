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

public class NewGameButton extends Button {

	private ButtonData buttonData;

	public NewGameButton(double x, double y, double width, double height, ButtonData buttonData) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setMinWidth(width);
		this.setHeight(height);
		this.setText("New Game");
		this.buttonData = buttonData;
		setAction();
	}

	private void setAction() {

		this.setOnAction(event -> {
			
			Popup popup = new Popup();
			popup.setX(40);
	        popup.setY(55);
	        popup.getContent().add(new GameSelector());
			popup.show(buttonData.getStage());
			
			
			
//			FileChooser fileChooser = new FileChooser();
//			fileChooser.setTitle("CHOOSE GAME");
//			String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/data";
//			fileChooser.setInitialDirectory(new File(currentPath));

//			File fileName = fileChooser.showOpenDialog(buttonData.getStage());
//			String fileString = "No File Selected";
//			if (fileName != null) {
//				fileString = fileName.getPath();
//			}
			
			String fileString = "dummy-mario.pngasdfa";

			buttonData.playGame(fileString);
			
		});

	}

}
