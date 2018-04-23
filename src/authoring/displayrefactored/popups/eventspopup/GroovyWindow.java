package authoring.displayrefactored.popups.eventspopup;

import authoring.Game;
import authoring.GameObject;
import authoring.displayrefactored.controllers.EventsPopupController;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroovyWindow extends VBox {

	private EventsPopupController epuc;
	private Game game;
	private GameObject go;
	private TextArea groovyInput;

	public GroovyWindow(EventsPopupController myEPUC, GameObject myGo) {
		go = myGo;
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
