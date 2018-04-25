package authoring.tests;

import java.util.ArrayList;

import authoring.GameObject;
import authoring.displayrefactored.popups.EventsPopupRefactored;
import authoring.displayrefactored.popups.eventspopup.TWKPopup;
import authoring.displayrefactored.popups.eventspopup.TriggerWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author August Ning
 * 
 * testing main class for the EventPopupRefactored
 *
 */
public class EventsPopUpTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		ArrayList<GameObject> gos = new ArrayList<>();
		GameObject go = new GameObject();
		go.setName("CalvinBoi");
		gos.add(go);
		
		EventsPopupRefactored epu = new EventsPopupRefactored(gos, gos);
	}



	public static void main(String args[]) {
		launch(args);
	}
}
