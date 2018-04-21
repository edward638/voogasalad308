package authoring.display.eventspopup;

import authoring.Event;
import authoring.Game;
import authoring.GameObject;
import javafx.scene.layout.VBox;

public class TriggerWindow extends VBox {

	private Game game;
	private GameObject go;
	private EventsPopUpController epuc;
	private Event currentEvent;
	
	public TriggerWindow(EventsPopUpController myEPUC, Game myGame, GameObject myGo) {
		game = myGame;
		go = myGo;
		epuc = myEPUC;
		createVBox();
		assignCurrentEvent();
	}
	
	private void createVBox() {
		
	}
	
	public void assignCurrentEvent() {
		currentEvent = epuc.getCurrEvent();
	}
}
