package authoring.tests;

import authoring.Game;
import authoring.GameObject;
import authoring.display.eventspopup.EventsPopUp;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class EventsPopUpTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		EventsPopUp epu = new EventsPopUp(null, new Game(), new Group(), new GameObject());
		stage.setScene(epu.getScene());
		stage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}
}
