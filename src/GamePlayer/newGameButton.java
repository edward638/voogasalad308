package GamePlayer;

import java.io.File;
import java.nio.file.Paths;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class newGameButton extends Button {

	ConcreteGamePlayer gamePlayer;
	Stage mainStage;

	public newGameButton(double x, double y, double width, double height, ConcreteGamePlayer gamePlayer, Stage stage) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setMinWidth(width);
		this.setHeight(height);
		this.setText("new game");
		this.gamePlayer = gamePlayer;
		this.mainStage = stage;
		setAction();
	}

	private void setAction() {

		this.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("CHOOSE GAME");
			String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/data";
			fileChooser.setInitialDirectory(new File(currentPath));

			File fileName = fileChooser.showOpenDialog(mainStage);
			String fileString = "No File Selected";
			if (fileName != null) {
				fileString = fileName.getPath();
			}

			gamePlayer.playGame(fileString);

		});

	}

}
