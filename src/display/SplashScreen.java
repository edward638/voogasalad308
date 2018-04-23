package display;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import data.propertiesFiles.ResourceBundleManager;
import display.buttonevents.GameAuthoringPress;
import display.buttonevents.GamePlayerPress;
import display.buttons.GUIButton;
import javafx.animation.RotateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author August Ning and Edward Zhuang
 * class that houses the SplashScreen
 *
 */
public class SplashScreen {

	private Stage currStage;
	private Pane myRoot;
	private List<GUIButton> buttons;

	public SplashScreen(Stage stage) {
		this.currStage = stage;
		addButtons();
	}

	private void addButtons() {
		buttons = new ArrayList<>();
		GUIButton gamePlayerButton = new GUIButton(
				Integer.parseInt(ResourceBundleManager.getSplash("PLAYERBUTTONSIZEX")),
				Integer.parseInt(ResourceBundleManager.getSplash("PLAYERBUTTONSIZEY")), "",
				new GamePlayerPress(currStage));
		GUIButton gameAuthoringButton = new GUIButton(
				Integer.parseInt(ResourceBundleManager.getSplash("AUTHBUTTONSIZEX")),
				Integer.parseInt(ResourceBundleManager.getSplash("AUTHBUTTONSIZEY")), "",
				new GameAuthoringPress(currStage));
		gamePlayerButton.setId(ResourceBundleManager.getSplash("PLAYERBUTTONID"));
		gameAuthoringButton.setId(ResourceBundleManager.getSplash("AUTHBUTTONID"));

		File playImageFile = new File(ResourceBundleManager.getSplash("PLAYFILE"));
		Image imagePlay;
		try {
			imagePlay = new Image(playImageFile.toURI().toURL().toExternalForm());
			ImageView im = new ImageView(imagePlay);
			im.setPreserveRatio(true);
			im.setFitHeight(Integer.parseInt(ResourceBundleManager.getSplash("FILEHEIGHT")));
			gamePlayerButton.setGraphic(im);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException();
		}

		File authorImageFile = new File(ResourceBundleManager.getSplash("WRITEFILE"));
		Image imageAuthor;
		try {
			imageAuthor = new Image(authorImageFile.toURI().toURL().toExternalForm());
			ImageView im = new ImageView(imageAuthor);
			im.setPreserveRatio(true);
			im.setFitHeight(Integer.parseInt(ResourceBundleManager.getSplash("FILEHEIGHT")));
			gameAuthoringButton.setGraphic(im);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException();
		}
		buttons.add(gamePlayerButton);
		buttons.add(gameAuthoringButton);

		rotateButtons(buttons);
	}

	private void rotateButtons(List<GUIButton> buttons) {
		for (GUIButton b : buttons) {
			RotateTransition rotation = new RotateTransition(Duration.seconds(0.25), b);
			rotation.setCycleCount(1);
			rotation.setByAngle(360);
			b.setOnMouseEntered(e -> rotation.play());
		}
	}

	private ImageView makeSplashImage(String imageName, double ratio, double x, double y) {
		try {
			File imageFile = new File(imageName);
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
		Scene scene = new Scene(myRoot, Integer.parseInt(ResourceBundleManager.getSplash("WINDOWSIZEX")),
				Integer.parseInt(ResourceBundleManager.getSplash("WINDOWSIZEY")));
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		myRoot.getChildren().addAll(buttons);
		myRoot.getChildren()
				.add(makeSplashImage(ResourceBundleManager.getSplash("LOGO"),
						Integer.parseInt(ResourceBundleManager.getSplash("LOGOSCALE")),
						Integer.parseInt(ResourceBundleManager.getSplash("LOGOX")),
						Integer.parseInt(ResourceBundleManager.getSplash("LOGOY"))));
		myRoot.getChildren()
				.add(makeSplashImage(ResourceBundleManager.getSplash("PLAY"),
						Integer.parseInt(ResourceBundleManager.getSplash("PLAYSCALE")),
						Integer.parseInt(ResourceBundleManager.getSplash("PLAYX")),
						Integer.parseInt(ResourceBundleManager.getSplash("PLAYY"))));
		myRoot.getChildren()
				.add(makeSplashImage(ResourceBundleManager.getSplash("CREATE"),
						Integer.parseInt(ResourceBundleManager.getSplash("CREATESCALE")),
						Integer.parseInt(ResourceBundleManager.getSplash("CREATEX")),
						Integer.parseInt(ResourceBundleManager.getSplash("CREATEY"))));
		return scene;
	}

}
