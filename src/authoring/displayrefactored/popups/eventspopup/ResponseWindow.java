package authoring.displayrefactored.popups.eventspopup;

import authoring.GameObject;
import authoring.displayrefactored.controllers.EventsPopupController;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ResponseWindow extends VBox {
	
	private EventsPopupController epuc;
	private GameObject go;

	public ResponseWindow(EventsPopupController myEPUC, GameObject myGo) {
		go = myGo;
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
		Text goName = new Text(go.getName());
		goName.setOnMouseClicked(e -> GOClicked());
		this.getChildren().add(goName);
	}
	
	private void GOClicked() {
		// implement in epuc, like add the name to the groovy window
	}

}
