package display;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import display.buttonevents.GameAuthoringPress;
import display.buttonevents.GamePlayerPress;
import display.buttons.GUIButton;
import display.text.SplashText;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author August Ning
 * class that houses the SplashScreen
 *
 */
public class SplashScreen {
	
	private Stage currStage; 
	private Pane myRoot;
	private Color BACKGROUND_COLOR = Color.LIGHTSTEELBLUE;
	private Color TEXT_COLOR = Color.WHITE;
	private List<GUIButton> buttons;
	private static final int SIZE_X = 1310;
	private static final int SIZE_Y = 700;
	private static final String SPLASH_IMAGE = "./data/images/Logo1.png";
	private static final String CREATE = "./data/images/createText.png";
	private static final String PLAY = "./data/images/playText.png";

	public SplashScreen(Stage stage) {
		this.currStage = stage;
        addButtons();
	}
	private void addButtons() {
        buttons = new ArrayList<>();
        GUIButton gamePlayerButton = new GUIButton(900, 400, "", new GamePlayerPress(currStage));
        GUIButton gameAuthoringButton = new GUIButton(900, 480, "", new GameAuthoringPress(currStage));
        gamePlayerButton.setId("enginebutton");
        gameAuthoringButton.setId("authoringbutton");
        
        File playImageFile = new File("./data/images/play.png");
		Image imagePlay;
		try {
			imagePlay = new Image(playImageFile.toURI().toURL().toExternalForm());
			ImageView im = new ImageView(imagePlay);
			im.setPreserveRatio(true);
			im.setFitHeight(20);
			gamePlayerButton.setGraphic(im);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		File authorImageFile = new File("./data/images/write.png");
		Image imageAuthor;
		try {
			imageAuthor = new Image(authorImageFile.toURI().toURL().toExternalForm());
			ImageView im = new ImageView(imageAuthor);
			im.setPreserveRatio(true);
			im.setFitHeight(20);
			gameAuthoringButton.setGraphic(im);    
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        buttons.add(gamePlayerButton);
        buttons.add(gameAuthoringButton);
        
        rotateButtons(buttons);
	}
	
	private void rotateButtons(List<GUIButton> buttons) {
		for (GUIButton b: buttons) {
			RotateTransition rotation = new RotateTransition(Duration.seconds(0.25), b);
			rotation.setCycleCount(1);
			rotation.setByAngle(360);
			b.setOnMouseEntered(e -> rotation.play());
		}
	}
	
	
	
	private ImageView makeSplashImage(String image1, double ratio, double x, double y) {
		try {
		File imageFile = new File(image1);
		Image image = new Image(imageFile.toURI().toURL().toExternalForm());
		ImageView splashImage = new ImageView(image);
		splashImage.setPreserveRatio(true);

		splashImage.setFitHeight(ratio);
		splashImage.setLayoutX(x);
		splashImage.setLayoutY(y);
		
		return splashImage;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * @return a Scene that can be displayed on the splash screen
	 */
	public Scene getSplashScreen() {
		myRoot = new Pane();
		myRoot.setId("pane");
		Scene scene = new Scene(myRoot, SIZE_X, SIZE_Y, BACKGROUND_COLOR);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		myRoot.getChildren().addAll(buttons);
//		myRoot.getChildren().add(new SplashText("VOOGASALAD", 70, 50, TEXT_COLOR, 40));
//		myRoot.getChildren().add(new SplashText("2dessertz", 140, 100, TEXT_COLOR, 30));
		myRoot.getChildren().add(makeSplashImage(SPLASH_IMAGE, 100, 850, 120));
		myRoot.getChildren().add(makeSplashImage(PLAY, 50, 885, 415));
		myRoot.getChildren().add(makeSplashImage(CREATE, 50, 900, 495));
		return scene;
	}

}
