package authoring.display.eventspopup;

import authoring.Behavior;
import authoring.Game;
import authoring.GameObject;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BehaviorsWindow extends VBox {
	
	private Game game;
	private GameObject go;
	private EventsPopUpController epuc;
	private Behavior currentBehavior;
	private ListView<Behavior> behaviors;
	
	public BehaviorsWindow(EventsPopUpController myEPUC, Game myGame, GameObject myGo) {
		epuc = myEPUC;
		game = myGame;
		go = myGo;
		behaviors = new ListView<>();
		currentBehavior = null;
		createVBox();
	}
	
	private void createVBox() {
		this.getChildren().clear();
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
	    this.setPrefWidth(200);
	    Text title = new Text("Behaviors");
	    this.getChildren().add(title);
	    createListView();
	}
	
	private void createListView() {
		System.out.println("From BehaviorsWindow: is epuc valid?: " + epuc.validEvent());
		if (!epuc.validEvent()) {
			System.out.println("From BehaviorsWindow: is epuc valid?: " + epuc.validEvent());
			Text nonvalid = new Text("No event selected");
			this.getChildren().add(nonvalid);
			return; 
		}
		behaviors.getItems().clear();
		behaviors.getItems().addAll(go.getBehaviors());
		behaviors.setOnMouseClicked(e -> mouseClicked(behaviors.getSelectionModel().getSelectedItem()));
		this.getChildren().add(behaviors);
	}
	
	private void mouseClicked(Behavior selectedBehavior) {
		currentBehavior = selectedBehavior;
	}
	
	public Behavior getCurrBehavior() {
		return currentBehavior;
	}
}
