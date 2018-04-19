package authoring.display;

import java.util.ResourceBundle;

import authoring.Behavior;
import authoring.Game;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author madelinewilkinson
 *
 */
public class BehaviorView extends MainWindowComponent {

	private VBox myVBox;
	private Behavior myBehavior;
	//don't think you need the GameObject as an instance var

	public BehaviorView(ResourceBundle resources, Game game, Node root, Behavior behavior) {
		super(resources, game, root);
		myBehavior = behavior;
		myVBox = new VBox();
		initialize();
	}
	
	private void initialize() {
		HBox hBox = new HBox();
		hBox.getChildren().add(new Label(myBehavior.getName()));
		myVBox.getChildren().add(hBox);
	}

	@Override
	protected Node asNode() {
		return myVBox;
	}

}
