package authoring.display;

import java.util.ResourceBundle;

import authoring.Game;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

/**
 * @author Maddie Wilkinson
 *
 */
public class PropertyPanel extends MainWindowComponent {

	private ScrollPane myScrollPane;
	private static final String IMAGES_FILEPATH = "./";

	//in AuthoringDisplay, don't forget to remove and re-add this to the root every time it changes
	public PropertyPanel(ResourceBundle resources, Game game, Node root) {
		super(resources, game, root);
		myScrollPane = new ScrollPane();

		VBox vBox = new VBox();

		myScrollPane.setContent(vBox);
		myScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	public ScrollPane asScrollPane() {
		return myScrollPane;
	}

	@Override
	protected Node asNode() {
		return myScrollPane;
	}


}
