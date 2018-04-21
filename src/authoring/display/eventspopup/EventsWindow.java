package authoring.display.eventspopup;

import java.util.Set;
import java.util.TreeSet;

import authoring.EngineClassRetriever;
import authoring.Event;
import authoring.Game;
import authoring.GameObject;
import engine.events.elementevents.ElementEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 * @author Summer
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
	private EventsPopUpController epuc;
	
	public EventsWindow(EventsPopUpController myEPUC, Game game, GameObject currObject) {
		go = currObject;
		classRetriever = new EngineClassRetriever();
		epuc = myEPUC;
		currentEvent = null;
		createVBox();
	}
	
	private void createVBox() {
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
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
							String[] name = c.toString().split(".");
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
	}

	private ListView<Event> makeEventList(){
		myEvents = new ListView<>();
		myEvents.getItems().setAll(go.getEvents());
		return myEvents;
	}
	
	public Event getCurrEvent() {
		return currentEvent;
	}
}
