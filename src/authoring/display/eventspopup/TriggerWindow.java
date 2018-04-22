package authoring.display.eventspopup;

import authoring.Event;
import authoring.Game;
import authoring.GameObject;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
		this.getChildren().clear();
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
	    this.setPrefWidth(200);
	    Text title = new Text("Trigger");
	    this.getChildren().add(title);
	}
	
	public void assignCurrentEvent() {
		currentEvent = epuc.getCurrEvent();
	}
}
