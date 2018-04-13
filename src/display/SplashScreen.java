package display;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import display.buttonevents.GameAuthoringPress;
import display.buttonevents.GamePlayerPress;
import display.buttons.GUIButton;
import display.text.SplashText;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author August Ning
 * class that houses the SplashScreen
 *
 */
public class SplashScreen {
	
	private Stage currStage; 
	private Group myRoot;
	private Color BACKGROUND_COLOR = Color.LIGHTSTEELBLUE;
	private Color TEXT_COLOR = Color.WHITE;
	private List<GUIButton> buttons;
	private static final int SIZE_X = 600;
	private static final int SIZE_Y = 400;
	private static final String SPLASH_IMAGE = "./data/images/Logo.png";

	public SplashScreen(Stage stage) {
		this.currStage = stage;
        addButtons();
	}
	private void addButtons() {
        buttons = new ArrayList<>();
        GUIButton gamePlayerButton = new GUIButton(20, 350, "Game Player", new GamePlayerPress(currStage));
        GUIButton gameAuthoringButton = new GUIButton(230, 350, "Game Authoring", new GameAuthoringPress(currStage));
        gamePlayerButton.setId("enginebutton");
        gameAuthoringButton.setId("authoringbutton");
        buttons.add(gamePlayerButton);
        buttons.add(gameAuthoringButton);
	}
	private ImageView makeSplashImage() {
		try {
		File imageFile = new File(SPLASH_IMAGE);
		Image image = new Image(imageFile.toURI().toURL().toExternalForm());
		ImageView splashImage = new ImageView(image);
		splashImage.setPreserveRatio(true);
//		splashImage.setFitWidth(300);
//		splashImage.setLayoutX(50);
//		splashImage.setLayoutY(120);
		
		splashImage.setFitHeight(100);
		splashImage.setLayoutX(50);
		splashImage.setLayoutY(50);
		
		return splashImage;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * @return a Scene that can be displayed on the splash screen
	 */
	public Scene getSplashScreen() {
		myRoot = new Group();
		myRoot.setId("pane");
		Scene scene = new Scene(myRoot, SIZE_X, SIZE_Y, BACKGROUND_COLOR);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		myRoot.getChildren().addAll(buttons);
//		myRoot.getChildren().add(new SplashText("VOOGASALAD", 70, 50, TEXT_COLOR, 40));
//		myRoot.getChildren().add(new SplashText("2dessertz", 140, 100, TEXT_COLOR, 30));
		myRoot.getChildren().add(makeSplashImage());
		return scene;
	}

}
