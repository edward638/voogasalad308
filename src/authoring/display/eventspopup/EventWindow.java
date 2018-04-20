package authoring.display.eventspopup;

import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import authoring.EngineClassRetriever;
import authoring.Event;
import authoring.Game;
import authoring.GameObject;
import authoring.display.MainWindowComponent;
import engine.events.elementevents.ElementEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 * @author Summer
 *
 */
public class EventWindow extends MainWindowComponent{

	private final Class<?> EVENTS_SUPERCLASS = ElementEvent.class;
	private final String EVENTS_PACKAGE = "engine.events.elementevents";
	
	private GameObject currentObject;
	private ComboBox<String> possibleEvents;
	private EngineClassRetriever classRetriever;
	private VBox vBox;
	private ListView<Event> myEvents;
	
	public EventWindow(ResourceBundle resources, Game game, Node root, GameObject currObject) {
		super(resources, game, root);
		currentObject = currObject;
		classRetriever = new EngineClassRetriever();
		createVBox();
	}
	
	private VBox createVBox() {
		vBox = new VBox();
		vBox.setPadding(new Insets(10));
	    vBox.setSpacing(8);
	    Text title = new Text("Events");
	    vBox.getChildren().add(title);
	    vBox.getChildren().addAll(makeEventDropdown(), makeEventList());
	    return vBox;
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
		return possibleEvents;
	}

	private ListView<Event> makeEventList(){
		myEvents = new ListView<>();
		myEvents.getItems().setAll(currentObject.getEvents());
		return myEvents;
	}
	
	@Override
	protected Node asNode() {
		return vBox;
	}
}
