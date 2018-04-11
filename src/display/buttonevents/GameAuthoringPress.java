package display.buttonevents;

import authoring.display.AuthoringDisplay;
import javafx.stage.Stage;

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
