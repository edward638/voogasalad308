package authoring.displayrefactored.popups.eventspopup;

import java.util.List;

import authoring.GameObject;
import authoring.displayrefactored.controllers.EventsPopupController;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResponseWindow extends VBox {
	
	private EventsPopupController epuc;
	private List<GameObject> gos;

	public ResponseWindow(EventsPopupController myEPUC, List<GameObject> myGos) {
		gos = myGos;
		epuc = myEPUC;
		createVBox();
	}
	public void createVBox() {
		this.getChildren().clear();
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
	    this.setMinWidth(200);
	    Text title = new Text("Response");
	    this.getChildren().add(title);
	    addGameObjects();
	}
	
	private void addGameObjects() {
		if (!epuc.validEvent()) {
			Text nonvalid = new Text("No event selected");
			this.getChildren().add(nonvalid);
			return;
		}
		Text currGO = new Text("Current Game Object");
		Text goName = new Text(gos.get(0).getName());
		goName.setOnMouseClicked(e -> GOClicked());
		this.getChildren().add(currGO);
		this.getChildren().add(goName);
	}
	
	private void GOClicked() {
		// implement in epuc, like add the name to the groovy window
	}

}
