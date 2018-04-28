package authoring.displayrefactored.popups.eventspopup;

import java.util.List;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.displayrefactored.controllers.EventsPopupController;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author August Ning
 * VBox for listing behaviors of an game object
 */
public class BehaviorsWindow extends VBox {
	
	private List<GameObject> gos;
	private EventsPopupController epuc;
	private AuthBehavior currentBehavior;
	private ListView<AuthBehavior> behaviors;
	
	public BehaviorsWindow(EventsPopupController myEPUC, List<GameObject> myGos) {
		epuc = myEPUC;
		gos = myGos;
		behaviors = new ListView<>();
		behaviors.setMinHeight(200);
		currentBehavior = null;
		createVBox();
	}
	
	public void createVBox() {
		this.getChildren().clear();
		this.setPadding(new Insets(10));
	    this.setSpacing(10);
	    this.setMinWidth(200);
	    Text title = new Text("Behaviors");
	    this.getChildren().add(title);
	    createListView();
	}
	
	private void createListView() {
		if (!epuc.validEvent()) {
			Text nonvalid = new Text("No event selected");
			this.getChildren().add(nonvalid);
			return; 
		}
		behaviors.getItems().clear();
		behaviors.getItems().addAll(gos.get(0).getBehaviors());
		behaviors.setOnMouseClicked(e -> mouseClicked(behaviors.getSelectionModel().getSelectedItem()));
		this.getChildren().add(behaviors);
	}
	
	private void mouseClicked(AuthBehavior selectedBehavior) {
		currentBehavior = selectedBehavior;
		epuc.updateFromBehavior();
		epuc.concatenateString(currentBehavior.getDisplayName(), "BehaviorsWindow");
	}
	
	public AuthBehavior getCurrBehavior() {
		return currentBehavior;
	}
}
