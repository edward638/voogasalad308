package authoring.displayrefactored.popups.eventspopup;

import java.util.List;

import authoring.GameObject;
import authoring.displayrefactored.controllers.EventsPopupController;
import engine.actions.GroovyAction;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroovyWindow extends VBox {

	private EventsPopupController epuc;
	private List<GameObject> gos;
	private TextArea groovyInput;
	private Button save;
	private String groovyString;

	
	private static final String SAVE = "Save";

	public GroovyWindow(EventsPopupController myEPUC, List<GameObject> myGos) {
		gos = myGos;
		epuc = myEPUC;
		save = new Button(SAVE);
		groovyString = "";
		createVBox();
	}
	public void createVBox() {
		this.getChildren().clear();
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
	    this.setMinWidth(200);
	    this.setMinHeight(200);
	    Text title = new Text("Groovy");
	    this.getChildren().add(title);
	    addTextArea();
	}
	private void addTextArea() {
		groovyInput = new TextArea();
		groovyInput.setPrefWidth(200);
		groovyInput.setWrapText(true);
		this.getChildren().add(groovyInput);
		this.getChildren().add(save);
		
		save.setOnAction(e -> savePress());
	}
	
	private void savePress() {
		groovyString = groovyInput.getText();
		GroovyAction action = new GroovyAction(groovyString);
		// apply the groovy response to the event
		epuc.getCurrEvent().addResponse(action);
		// display the groovy input text on the response
		epuc.updateResponseWindow();
	}
	
	public void concatenateString(String stringToAdd, String caller) {
		String[] pieces = groovyInput.getText().split("\\.");
		String newInput = "";
		if(caller.contains("ResponseWindow")) {
			newInput = stringToAdd;
		}
		else if(caller.contains("BehaviorsWindow")) {
			newInput = pieces[0] + "." + "getBehavior('" + stringToAdd + "').";
		}
		else if(caller.contains("MFWindow")){
			newInput = pieces[0] + "." + pieces [1] + "." + stringToAdd;
		}
		groovyInput.setText(newInput);
	}
	
	public String getGroovyString() {
		return groovyString;
	}
	
	public void clearGroovy() {
		groovyInput.clear();
	}
}
