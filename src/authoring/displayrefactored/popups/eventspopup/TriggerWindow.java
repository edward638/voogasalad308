package authoring.displayrefactored.popups.eventspopup;

import java.util.List;

import authoring.Event;
import authoring.GameObject;
import authoring.displayrefactored.controllers.EventsPopupController;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author August
 * Panel that takes care of trigger of events from game objects
 *
 */
public class TriggerWindow extends VBox {

	private List<GameObject> gos;
	private EventsPopupController epuc;
	private Event currentEvent;
	
	public TriggerWindow(EventsPopupController myEPUC, List<GameObject> myGos) {
		gos = myGos;
		epuc = myEPUC;
		createVBox();
		assignCurrentEvent();
	}
	
	private void createVBox() {
		this.getChildren().clear();
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
	    this.setMinWidth(200);
	    Text title = new Text("Trigger");
	    this.getChildren().add(title);
	}
	
	public void assignCurrentEvent() {
		currentEvent = epuc.getCurrEvent();
	}
}
