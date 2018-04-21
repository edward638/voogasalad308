package authoring.display.eventspopup;

import authoring.Event;
import authoring.Game;
import authoring.GameObject;

public class EventsPopUpController {

	private Game game;
	private GameObject go;
	
	private EventsWindow eventsWindow;
	private TriggerWindow triggerWindow;
	
	public EventsPopUpController(Game myGame, GameObject myGo) {
		game = myGame;
		go = myGo;
		
		eventsWindow = new EventsWindow(this, game, go);
		triggerWindow = new TriggerWindow(this, game, go);
	}
	
	public Event getCurrEvent() {
		return eventsWindow.getCurrEvent();
	}
}
