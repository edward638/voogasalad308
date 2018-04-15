package display.buttonevents;

import gamePlayer.ConcreteGamePlayer;
import gamePlayer.GamePlayer;
import javafx.stage.Stage;

/**
 * @author August Ning
 * This class is used to start up the game player when a button is pressed.
 * It will close the current stage from where the button was called from, and then open up a new stage
 * that will run the game player
 */
public class GamePlayerPress implements ButtonEvent {

	private Stage splashStage;
	private Stage gamePlayerStage;
	
	public GamePlayerPress(Stage stage) {
		splashStage = stage;
		gamePlayerStage = new Stage();
	}

	@Override
	public void pressed() {
//		splashStage.close();
		
	new ConcreteGamePlayer(gamePlayerStage);
	gamePlayerStage.show();
	gamePlayerStage.setResizable(false);

	}

}
