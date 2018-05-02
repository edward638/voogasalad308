package gamePlayer.buttons;

import java.io.File;
import java.nio.file.Paths;

import data.GameDescriptionProvider;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * front end for selecting new games
 * 
 * @author jeffreyli, calvinma
 *
 */
public class GameSelector extends ScrollPane {

	private ButtonData buttonData;
	private boolean isNew;
	private String playOrLoadGame;

	public GameSelector(ButtonData buttonData, boolean isNew) {
		this.buttonData = buttonData;
		buttonData.pauseGame();
		this.setLayoutX(30);
		this.setLayoutY(30);
		this.setPrefSize(900, 590);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.isNew = isNew;
		if (isNew) {
			playOrLoadGame = "Select Game";
		} else {
			playOrLoadGame = "Load Game";
		}
		initializeGameSelections();
	}

	private void initializeGameSelections() {
		VBox gameSelectorBox = new VBox();
		gameSelectorBox.setSpacing(5);
		gameSelectorBox.setStyle("-fx-background-color:white;");

		gameSelectorBox.setMinWidth(900);
		gameSelectorBox.setMaxWidth(900);

		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/data/gamedata/games";
		File directory = new File(currentPath);
		GameDescriptionProvider gameDescriptionProvider = new GameDescriptionProvider();

		File[] fList = directory.listFiles();
		for (File file : fList) {
			String gameName = file.getName();
			if (gameName.contains("DS_Store")) {
				continue;
			}
			String gameString = gameDescriptionProvider.getGameName(gameName);
			String gameDescription = gameDescriptionProvider.getGameDescription(gameName);
			Image gameImage = gameDescriptionProvider.getDescriptionImage(gameName);

			Pane gameDescriptionPane = setupNewGamePane(gameName, gameString, gameDescription, gameImage);

			Button playButton = new Button(playOrLoadGame);
			playButton.setLayoutX(750);
			playButton.setLayoutY(90);
			playButton.setOnAction((event) -> {
				buttonData.removeFromRoot(gameSelectorBox);
				buttonData.removeFromRoot(this);
				buttonData.playGame(gameName, isNew);

			});

			gameDescriptionPane.getChildren().add(playButton);
			gameSelectorBox.getChildren().add(gameDescriptionPane);

		}

		this.setContent(gameSelectorBox);

	}

	private Pane setupNewGamePane(String gameName, String gameString, String gameDescription, Image gameImage) {
		Pane pane = new Pane();

		pane.setMinWidth(900);
		pane.setMaxWidth(900);
		pane.setMinHeight(200);
		pane.setMaxHeight(200);
		pane.setStyle("-fx-background-color:black;");

		ImageView imageView = new ImageView();
		imageView.setImage(gameImage);
		imageView.setX(20);
		imageView.setY(20);
		imageView.setFitHeight(160);
		imageView.setFitWidth(160);

		Text nameText = new Text(gameString);
		nameText.setX(225);
		nameText.setY(60);
		nameText.setStyle(" -fx-font: 50px Helvetica;\r\n"
				+ "    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\r\n"
				+ "    -fx-stroke: black;\r\n" + "    -fx-stroke-width: 1;");

		Text descriptionText = new Text(gameDescription);
		descriptionText.setX(230);
		descriptionText.setY(90);
		descriptionText.setFont(new Font("Times New Roman", 20));
		descriptionText.setFill(Color.WHITE);

		pane.getChildren().addAll(imageView, nameText, descriptionText);

		return pane;
	}

}
