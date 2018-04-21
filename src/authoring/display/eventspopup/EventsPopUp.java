package authoring.display.eventspopup;

import java.util.ResourceBundle;

import authoring.Game;
import authoring.GameObject;
import authoring.display.AuthoringUIComponent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

/**
 * 
 * all panels will be in a VBox. All VBoxes will be placed on an Hbox.
 * the hbox will be the scene
 * 
 * @author Summer and August
 *
 */
public class EventsPopUp extends AuthoringUIComponent {

	private final int SCREEN_WIDTH = 50;
	private final int SCREEN_HEIGHT = 50;

	private Scene eventsPUScene;
	private HBox eventsPUBox;
	private EventsPopUpController epuc;

	public EventsPopUp(ResourceBundle resources, Game game, Node root, GameObject go) {
		super(resources, game, root);
		eventsPUBox = new HBox();
		epuc = new EventsPopUpController(game, go);
	}

}
