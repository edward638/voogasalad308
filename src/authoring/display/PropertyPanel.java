package authoring.display;

import java.util.ResourceBundle;

import authoring.Behavior;
import authoring.Game;
import authoring.GameObject;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

/**
 * @author Maddie Wilkinson
 *
 */
public class PropertyPanel extends MainWindowComponent {
	private static final String IMAGES_FILEPATH = "./";

	private ScrollPane myScrollPane;
	private VBox myVBox;

	//in AuthoringDisplay, don't forget to remove and re-add this to the root every time it changes
	public PropertyPanel(ResourceBundle resources, Game game, Node root) {
		super(resources, game, root);
		myScrollPane = new ScrollPane();
		myVBox = new VBox();
		initializeVBox();
		myScrollPane.setContent(myVBox);
		myScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	private void initializeVBox() {
		myVBox.getChildren().add(new Label("Behaviors"));


	}

	private void addBehaviors() {
		try {
			GameObject currObject = getGame().getSceneManager().getCurrentScene().getCurrentGameObject();
			if(currObject != null) {
				for(Behavior b : currObject.getBehaviors()) {
					BehaviorView view = new BehaviorView(getResources(), getGame(), getRoot(), b);
					myVBox.getChildren().add(view.asNode());
				}
			}
		} catch (Exception e) {

		}

	}

	public void updatePanel() {
		myVBox.getChildren().clear();
		initializeVBox();
		addBehaviors();
	}

	public ScrollPane asScrollPane() {
		return myScrollPane;
	}

	@Override
	protected Node asNode() {
		return myScrollPane;
	}
}
