package authoring.displayrefactored.popups.eventspopup;

import java.util.ArrayList;
import java.util.List;

import authoring.Game;
import authoring.GameObject;
import authoring.displayrefactored.controllers.EventsPopupController;
import engine.groovy.GroovyException;
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
	
	public void concatenateString(String stringToAdd, String caller) {
		System.out.println("string to add: " + stringToAdd);
		System.out.println("current input: " + groovyInput.getText());
		String[] pieces = groovyInput.getText().split("\\.");
		if(groovyInput.getText()!=null) {
			System.out.println("not null - first word: " + pieces[0]);
		}
		for(String p : pieces) {
			System.out.println("loop: " + p);
		}
		System.out.println("caller: " + caller);
		String newInput = "";
		if(caller.contains("ResponseWindow")) {
//			groovyInput.setText(stringToAdd);
			newInput = stringToAdd;
		}
		else if(caller.contains("BehaviorsWindow")) {
//			if(pieces.length > 0) {
				newInput = pieces[0] + "." + "getBehavior(" + stringToAdd + ").";
//			}
//			else {
//				throw new GroovyException("error in concatenateString in GroovyWindow class");
//			}
		}
		else if(caller.contains("MFWindow")){
			newInput = pieces[0] + "." + pieces [1] + "." + stringToAdd;
		}
//		String newInput = groovyInput.getText() + stringToAdd;
		groovyInput.setText(newInput);
	}
}
