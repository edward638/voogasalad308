package authoring.displayrefactored.popups.eventspopup;

import java.util.List;

import authoring.Game;
import authoring.GameObject;
import authoring.displayrefactored.controllers.EventsPopupController;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroovyWindow extends VBox {

	private EventsPopupController epuc;
	private List<GameObject> gos;
	private TextArea groovyInput;

	public GroovyWindow(EventsPopupController myEPUC, List<GameObject> myGos) {
		gos = myGos;
		epuc = myEPUC;
		createVBox();
	}
	private void createVBox() {
		this.getChildren().clear();
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
	    this.setMinWidth(200);
	    Text title = new Text("Groovy");
	    this.getChildren().add(title);
	    addTextArea();
	}
	private void addTextArea() {
		groovyInput = new TextArea();
		groovyInput.setPrefWidth(200);
		groovyInput.setWrapText(true);
		this.getChildren().add(groovyInput);
	}
	
	public void concatenateString(String stringToAdd) {
		String newInput = groovyInput.getText() + stringToAdd;
		groovyInput.setText(newInput);
	}
}
