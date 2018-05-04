package authoring.display.controllers;

import java.util.ArrayList;
import java.util.List;

import authoring.AuthBehavior;
import authoring.Event;
import authoring.GameObject;
import authoring.display.popups.eventspopup.BehaviorsWindow;
import authoring.display.popups.eventspopup.EventsWindow;
import authoring.display.popups.eventspopup.GroovyWindow;
import authoring.display.popups.eventspopup.MFWindow;
import authoring.display.popups.eventspopup.ResponseWindow;
import authoring.display.popups.eventspopup.TriggerWindow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author August Ning 
 *
 */
public class EventsPopupController extends PopupController {
	
	private List<GameObject> gos;
	private List<GameObject> allGos;   
	private EventsWindow eventsWindow;
	private TriggerWindow triggerWindow;
	private BehaviorsWindow behaviorsWindow;
	private MFWindow mfWindow;
	private ResponseWindow responseWindow;
	private GroovyWindow groovyWindow;

	public EventsPopupController(List<GameObject> currentGos, List<GameObject> allGO){
		gos = currentGos;
		allGos = allGO;
		initializeScreenComponents();
	}

	@Override
	protected void initializeScreenComponents() {
		eventsWindow = new EventsWindow(this, gos);
		triggerWindow = new TriggerWindow(this, gos, allGos);
		behaviorsWindow = new BehaviorsWindow(this, gos);
		mfWindow = new MFWindow(this, gos);
		responseWindow = new ResponseWindow(this, gos);
		groovyWindow = new GroovyWindow(this, gos);
	}

	protected void refreshView() {
		// does nothing
		responseWindow.createVBox();
		behaviorsWindow.createVBox();
		mfWindow.createVBox();
		eventsWindow.updateEventList();
	}
	
	public Event getCurrEvent() {
		return eventsWindow.getCurrEvent();
	}
	public AuthBehavior getCurrBehavior() {
		return behaviorsWindow.getCurrBehavior();
	}
	public boolean validEvent() {
		return eventsWindow.validEvent();
	}
	public void concatenateString(String stringToAdd, String caller) {
		groovyWindow.concatenateString(stringToAdd, caller);
	}
	public List<VBox> getWindows() {
		List<VBox> windows = new ArrayList<>();
		windows.add(eventsWindow);
		windows.add(triggerWindow);
		windows.add(responseWindow);
		windows.add(behaviorsWindow);
		windows.add(mfWindow);
		windows.add(groovyWindow);
		return windows;
	}
	
	public void updateFromEvent() {
		triggerWindow.createVBox();
		responseWindow.createVBox();
		behaviorsWindow.createVBox();
		mfWindow.createVBox();
		eventsWindow.updateEventList();
		groovyWindow.createVBox();
	}
	
	public void updateFromBehavior() {
		mfWindow.createVBox();
	}
	
	public void updateResponseWindow() {
		responseWindow.createVBox();
	}
	
	public GameObject getCurrCollisionObject() {
		return triggerWindow.getCollideObject();
	}
	
	public void setCurrObject(String name) {
		for (GameObject go : allGos) {
			if (go.getName().equals(name)) {
				behaviorsWindow.setCurrObject(go);
				return;
			}
		}
	}
	
	public String getGroovyString() {
		return groovyWindow.getGroovyString();
	}
	
}
