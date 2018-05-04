package authoring.display.popups.eventspopup;

import java.util.List;

import authoring.GameObject;
import authoring.display.controllers.EventsPopupController;
import engine.actions.GroovyAction;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Window for groovy action string field
 * @author August Ning
 *
 */
public class GroovyWindow extends VBox {

	private EventsPopupController epuc;
	@SuppressWarnings("unused")
	private List<GameObject> gos;
	private TextArea groovyInput;
	private Button save;
	private String groovyString;
	
	private static final String GROOVY = "Groovy";
	private static final int SPACING = 10;
	private static final int SIZE = 200;


	
	private static final String SAVE = "Save";

	public GroovyWindow(EventsPopupController myEPUC, List<GameObject> myGos) {
		gos = myGos;
		epuc = myEPUC;
		save = new Button(SAVE);
		groovyString = "";
		createVBox();
	}
	/**
	 * Creates the VBox for the Groovy Window
	 */
	public void createVBox() {
		this.getChildren().clear();
		this.setPadding(new Insets(SPACING));
	    this.setSpacing(SPACING);
	    this.setMinWidth(SIZE);
	    this.setMinHeight(SIZE);
	    Text title = new Text(GROOVY);
	    this.getChildren().add(title);
	    addTextArea();
	}
	private void addTextArea() {
		groovyInput = new TextArea();
		groovyInput.setPrefWidth(SIZE);
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
	
	/**
	 * Concatenates strings on the groovy window
	 * @param stringToAdd 	The string to add to the window
	 * @param caller 		The name of the class that called the function
	 */
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
	
}
