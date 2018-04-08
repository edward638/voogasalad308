package GamePlayer;

import java.io.File;
import java.nio.file.Paths;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadButton extends Button {

	Stage mainStage;

	public LoadButton(double x, double y, double width, double height, Stage stage) {
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setText("load");
		mainStage = stage;
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

			System.out.println(fileString);

		});

	}
}
