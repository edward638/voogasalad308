package gamePlayer.buttons;

import java.io.File;
import java.nio.file.Paths;

import gamePlayer.ConcreteGamePlayer;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadButton extends Button {

	private ButtonData buttonData;

	public LoadButton(double x, double y, double width, double height, ButtonData buttonData) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setMinWidth(width);
		this.setHeight(height);
		this.setText("Load Game");
		this.buttonData = buttonData;
		setAction();
	}

	private void setAction() {
		this.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("CHOOSE GAME");
			String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/data";
			fileChooser.setInitialDirectory(new File(currentPath));

			File fileName = fileChooser.showOpenDialog(buttonData.getStage());
			String fileString = "No File Selected";
			if (fileName != null) {
				fileString = fileName.getPath();
			}

			buttonData.playGame(fileString);

		});

	}
}
