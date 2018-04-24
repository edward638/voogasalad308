package authoring.displayrefactored.popups.eventspopup;

import java.util.List;

import authoring.Event;
import authoring.GameObject;
import authoring.displayrefactored.controllers.EventsPopupController;
import display.buttonevents.TriggerKeyboardPress;
import display.buttons.GUIButton;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author August Panel that takes care of trigger of events from game objects
 *
 */
public class TriggerWindow extends VBox {

	private List<GameObject> gos;
	private List<GameObject> allGos;
	private EventsPopupController epuc;
	private Event currentEvent;
	
	private String kc;

	private static final String KEYBOARD = "KeyInputEvent";
	private static final String COLLISION = "";
	private static final String NOEVENT = "No event selected";
	private static final String NOTRIGGER = "No trigger required";

	public TriggerWindow(EventsPopupController myEPUC, List<GameObject> myGos, List<GameObject> allGO) {
		gos = myGos;
		allGos = allGO;
		epuc = myEPUC;
		kc = "";
		createVBox();
	}

	public void createVBox() {
		this.getChildren().clear();
		this.setPadding(new Insets(10));
		this.setSpacing(8);
		this.setMinWidth(200);
		Text title = new Text("Trigger");
		this.getChildren().add(title);
		assignCurrentEvent();
	}

	private void assignCurrentEvent() {
		currentEvent = epuc.getCurrEvent();
		try {
			if (currentEvent.getEventType().equals(KEYBOARD)) {
				this.getChildren().add(new GUIButton(0, 0, "Edit Keybind", new TriggerKeyboardPress(this)));
			} else if (currentEvent.getEventType().equals(COLLISION)) {
				
			} else {
				this.getChildren().add(new Text(NOTRIGGER));
			}
		} catch (Exception e) {
			this.getChildren().add(new Text(NOEVENT));
		}
	}
	
	public void setKeyCode(String newkc) {
		kc = newkc;
	}
	
	public String getKeyCode() {
		return kc;
	}
}
