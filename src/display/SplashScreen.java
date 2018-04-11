package display;

import java.util.ArrayList;
import java.util.List;

import display.buttonevents.GameAuthoringPress;
import display.buttonevents.GamePlayerPress;
import display.buttons.GUIButton;
import javafx.scene.Group;
import javafx.scene.Scene;
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
	private Color BACKGROUND_COLOR = Color.BEIGE;
	private List<GUIButton> buttons;
	private static final int SIZE_X = 400;
	private static final int SIZE_Y = 400;

	public SplashScreen(Stage stage) {
		this.currStage = stage;
        addButtons();
	}
	private void addButtons() {
        buttons = new ArrayList<>();
        GUIButton gamePlayerButton = new GUIButton(10, 10, "Game Player", new GamePlayerPress(currStage));
        GUIButton gameAuthoringButton = new GUIButton(10, 50, "Game Authoring", new GameAuthoringPress(currStage));
        buttons.add(gamePlayerButton);
        buttons.add(gameAuthoringButton);
	}
	/**
	 * @return a Scene that can be displayed on the splash screen
	 */
	public Scene getSplashScreen() {
		myRoot = new Group();
		Scene scene = new Scene(myRoot, SIZE_X, SIZE_Y, BACKGROUND_COLOR);
		myRoot.getChildren().addAll(buttons);
		return scene;
	}

}
