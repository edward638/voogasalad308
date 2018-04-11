package display.buttonevents;

import authoring.display.AuthoringDisplay;
import javafx.stage.Stage;

/**
 * @author August Ning
 * Button event handler that closes the stage it came from, and then opens up the game authoring stage
 *
 */
public class GameAuthoringPress implements ButtonEvent {
	
	private Stage splashStage;
	private Stage gameAuthoringStage;

	public GameAuthoringPress(Stage stage) {
		splashStage = stage;
		gameAuthoringStage = new Stage();
	}

	@Override
	public void pressed() {
		splashStage.close();
		
		new AuthoringDisplay(gameAuthoringStage);

	}

}
