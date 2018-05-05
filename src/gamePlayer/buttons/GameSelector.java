package gamePlayer.buttons;

import java.io.File;
import java.nio.file.Paths;

import data.GameDescriptionProvider;
import engine.exceptions.ErrorBox;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
	private VBox gameSelectorBox;
	private static final int boxWidth = 900;
	private static final int boxHeight = 210;
	private static final int paneHeight = 200;
	private static final String selectGameString = "Select Game";
	private static final String loadGameString = "Load Game";
	private static final String font = "Times New Roman";
	private static final Color color = Color.WHITE;
	private static final int LAYOUT_OFFSET = 30;
	private static final int SELECTOR_WIDTH = 900;
	private static final int SELECTOR_HEIGHT = 590;
	private static final int SELECTORBOX_SPACING = 10;
	private static final double IMAGE_OPACITY = 0.4;
	private static final int BUTTON_X = 750;
	private static final int BUTTON_Y = 90;
	private static final int GAMEIMAGE_OFFSET = 20;
	private static final int GAMEIMAGE_SIZE = 160;
	private static final int DESCRIPTION_X = 230;
	private static final int DESCRIPTION_Y = 90;
	private static final String FANCY_TEXT = " -fx-font: 50px Helvetica;\\r\\n\"\r\n"
			+ "				+ \"    -fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\\r\\n\"\r\n"
			+ "				+ \"    -fx-stroke: black;\\r\\n\" + \"    -fx-stroke-width: 1;";
	private static final int NAMETEXT_X = 225;
	private static final int NAMETEXT_Y = 60;

	public GameSelector(ButtonData buttonData, boolean isNew) {
		this.buttonData = buttonData;
		buttonData.setEngineRunning(false);
		buttonData.pauseGame();

		this.setLayoutX(LAYOUT_OFFSET);
		this.setLayoutY(LAYOUT_OFFSET);
		this.setPrefSize(SELECTOR_WIDTH, SELECTOR_HEIGHT);
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.isNew = isNew;
		if (isNew) {
			playOrLoadGame = selectGameString;
		} else {
			playOrLoadGame = loadGameString;
		}
		initializeGameSelections();
	}

	private void initializeGameSelections() {
		setUpGameSelectorBox();

		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/data/gamedata/games";
		File directory = new File(currentPath);
		GameDescriptionProvider gameDescriptionProvider = new GameDescriptionProvider();

		File[] gameList = directory.listFiles();
		for (File game : gameList) {
			String gameName = game.getName();
			try {
				String gameString = gameDescriptionProvider.getGameName(gameName);
				String gameDescription = gameDescriptionProvider.getGameDescription(gameName);
				Image gameImage = gameDescriptionProvider.getDescriptionImage(gameName);
				Pane gameDescriptionPane = setupNewGamePane(gameName, gameString, gameDescription, gameImage);
				gameSelectorBox.getChildren().add(gameDescriptionPane);
			} catch (Exception e) {
				// Error will never happen because the selector only shows legal files
				continue;
			}
		}
		this.setContent(gameSelectorBox);
	}

	private void setUpGameSelectorBox() {
		gameSelectorBox = new VBox();

		gameSelectorBox.setSpacing(SELECTORBOX_SPACING);
		gameSelectorBox.setStyle("-fx-background-color: transparent;");

		gameSelectorBox.setMinWidth(boxWidth);
		gameSelectorBox.setMaxWidth(boxHeight);
	}

	private Pane setupNewGamePane(String gameName, String gameString, String gameDescription, Image gameImage) {
		Pane pane = new Pane();

		ImageView paneImage = new ImageView(new Image("file:background_image.jpg"));

		paneImage.setOpacity(IMAGE_OPACITY);
		paneImage.setFitWidth(boxWidth);
		paneImage.setFitHeight(boxHeight);
		pane.getChildren().add(paneImage);

		pane.setMinWidth(boxWidth);
		pane.setMaxWidth(boxWidth);
		pane.setMinHeight(paneHeight);
		pane.setMaxHeight(paneHeight);
		pane.setStyle("-fx-background-color: transparent;");

		ImageView imageView = setUpGameImage(gameImage);

		Text nameText = setUpGameText(gameString);
		Text descriptionText = setUpGameDescriptionText(gameDescription);
		Button playButton = makePlayButton(gameName);
		pane.getChildren().addAll(imageView, nameText, descriptionText, playButton);

		return pane;
	}

	private Button makePlayButton(String gameName) {
		Button playButton = new Button(playOrLoadGame);

		playButton.setLayoutX(BUTTON_X);
		playButton.setLayoutY(BUTTON_Y);
		playButton.setOnAction((event) -> {
			buttonData.removeFromRoot(gameSelectorBox);
			buttonData.removeFromRoot(this);
			buttonData.playGame(gameName, isNew);
		});
		return playButton;
	}

	private Text setUpGameDescriptionText(String gameDescription) {
		Text descriptionText = new Text(gameDescription);

		descriptionText.setX(DESCRIPTION_X);
		descriptionText.setY(DESCRIPTION_Y);
		descriptionText.setFont(new Font(font, 20));
		descriptionText.setFill(color);
		return descriptionText;
	}

	private ImageView setUpGameImage(Image gameImage) {
		ImageView imageView = new ImageView();
		imageView.setImage(gameImage);
		imageView.setX(GAMEIMAGE_OFFSET);
		imageView.setY(GAMEIMAGE_OFFSET);
		imageView.setFitHeight(GAMEIMAGE_SIZE);
		imageView.setFitWidth(GAMEIMAGE_SIZE);
		return imageView;
	}

	private Text setUpGameText(String gameString) {
		Text nameText = new Text(gameString);
		nameText.setX(NAMETEXT_X);
		nameText.setY(NAMETEXT_Y);
		nameText.setStyle(FANCY_TEXT);
		return nameText;
	}
}
