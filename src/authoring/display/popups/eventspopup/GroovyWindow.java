package authoring.display.popups.eventspopup;

import java.util.List;

import authoring.GameObject;
import authoring.display.controllers.EventsPopupController;
import engine.actions.GroovyAction;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroovyWindow extends VBox {

	private EventsPopupController epuc;
	private List<GameObject> gos;
	private TextArea groovyInput;
	private TextField groovyName;
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
	    addTextAreas();
	}
	private void addTextAreas() {
		groovyInput = new TextArea();
		groovyInput.setPrefWidth(200);
		groovyInput.setWrapText(true);
		groovyName = new TextField();
		this.getChildren().add(groovyInput);
		this.getChildren().add(groovyName);
		this.getChildren().add(save);
		
		save.setOnAction(e -> savePress());
	}
	
	private void savePress() {
		groovyString = groovyInput.getText();
		String actionName = groovyName.getText();
		GroovyAction action = new GroovyAction(groovyString);
		// apply the groovy response to the event
		epuc.getCurrEvent().addResponse(action);
		// display the groovy input text on the response
		epuc.updateResponseWindow();
	}
	
	public void concatenateString(String stringToAdd, String caller) {
		try {
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
		} catch (Exception e) {
			return;
		}
	}
	
	public String getGroovyString() {
		return groovyString;
	}
	
	public void clearGroovy() {
		groovyInput.clear();
	}
}
