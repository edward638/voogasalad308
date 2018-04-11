package gamePlayer.buttons;

import java.io.File;
import java.nio.file.Paths;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameSelector extends ScrollPane {

	public GameSelector() {
		this.setLayoutX(30);
		this.setLayoutY(30);
		this.setPrefSize(900, 590);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		initializeGameSelections();
	}

	private void initializeGameSelections() {
		VBox box = new VBox();

		box.setMinWidth(500);
		box.setMaxWidth(500);
		Pane p = new Pane();
		p.setMinWidth(400);
		p.setMaxWidth(400);
		p.setMinHeight(200);
		p.setMaxHeight(200);

		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/src/testGameFolder";
		File directory = new File(currentPath);

		File[] fList = directory.listFiles();
		for (File file : fList) {
			String gamePath = currentPath + "/" + file.getName();
			File gameDirectory = new File(gamePath);
			File[] gameList = gameDirectory.listFiles();
			for (File gameFile : gameList) {
				String path = gameFile.getName();
				System.out.println(path); // this gets name for each file
				String extension = "";
				int i = path.lastIndexOf('.');
				if (i > 0) {
					extension = path.substring(i + 1);
				}
				//create pane in this loop
				
			}

		}

		Text t = new Text("1412412412");
		t.setY(12);
		t.setX(120);

		Rectangle rect1 = new Rectangle(0, 0, 500, 500);
		rect1.setFill(Color.BISQUE);

		p.getChildren().add(t);

		Pane p1 = new Pane();
		p1.setMinWidth(400);
		p1.setMaxWidth(400);
		p1.setMinHeight(900);
		p1.setMaxHeight(900);
		p1.getChildren().add(rect1);

		box.getChildren().add(p);
		box.getChildren().add(p1);

		this.setContent(box);

	}

}
