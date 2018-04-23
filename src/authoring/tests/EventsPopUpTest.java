package authoring.tests;

import authoring.GameObject;
import authoring.displayrefactored.popups.EventsPopupRefactored;
import javafx.application.Application;
import javafx.stage.Stage;

public class EventsPopUpTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		EventsPopupRefactored epu = new EventsPopupRefactored(new GameObject());
	}



	public static void main(String args[]) {
		launch(args);
	}
}
