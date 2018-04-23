package authoring.displayrefactored.popups.eventspopup;

import java.util.Set;
import java.util.TreeSet;

import authoring.EngineClassRetriever;
import authoring.Event;
import authoring.GameObject;
import authoring.displayrefactored.controllers.EventsPopupController;
import engine.events.elementevents.ElementEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 * @author Summer and August
 *
 */
public class EventsWindow extends VBox {

	private final Class<?> EVENTS_SUPERCLASS = ElementEvent.class;
	private final String EVENTS_PACKAGE = "engine.events.elementevents";
	
	private GameObject go;
	private ComboBox<String> possibleEvents;
	private EngineClassRetriever classRetriever;
	private ListView<Event> myEvents;
	private Event currentEvent;
	private EventsPopupController epuc;
	
	public EventsWindow(EventsPopupController myEPUC, GameObject currObject) {
		go = currObject;
		classRetriever = new EngineClassRetriever();
		epuc = myEPUC;
		myEvents = new ListView<>();
		myEvents.setMinHeight(200);
		currentEvent = null;
		createVBox();
	}
	
	private void createVBox() {
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
	    this.setMinWidth(200);
	    Text title = new Text("Events");
	    this.getChildren().add(title);
	    this.getChildren().addAll(makeEventDropdown(), makeEventList());
	}
	
	private ComboBox<String> makeEventDropdown(){
		possibleEvents = new ComboBox<>();
		possibleEvents.setPromptText("Event options");
		Set<?> retrieved = new TreeSet<>();
		retrieved = classRetriever.getClasses(EVENTS_SUPERCLASS, EVENTS_PACKAGE);
		retrieved.forEach(c -> {
							String[] holder = c.toString().split(" ");
							String[] name = holder[holder.length - 1].split("\\.");
							String use = name[name.length-1];
							possibleEvents.getItems().add(use);
		});
		possibleEvents.setOnAction(e -> comboBoxAction(possibleEvents.getValue()));
		return possibleEvents;
	}
	
	private void comboBoxAction(String eventName) {
		currentEvent = new Event();
		currentEvent.setEventType(eventName);
		go.addEvent(currentEvent);
		epuc.updateFromEvent(currentEvent);
	}

	private ListView<Event> makeEventList(){
		myEvents.getItems().clear();
		myEvents.getItems().setAll(go.getEvents());
		return myEvents;
	}
	
	public void updateEventList() {
		this.getChildren().remove(myEvents);
		this.getChildren().add(makeEventList());
	}
	
	public Event getCurrEvent() {
		return currentEvent;
	}
	
	public boolean validEvent() {
		return currentEvent != null;
	}
}
