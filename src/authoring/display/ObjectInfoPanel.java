package authoring.display;

import java.util.ResourceBundle;

import authoring.Behavior;
import authoring.Game;
import authoring.GameObject;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Maddie Wilkinson
 *
 */
public class ObjectInfoPanel extends MainWindowComponent {
	private static final String IMAGES_FILEPATH = "./";
	private static final double PANE_PREF_WIDTH = 250;

	private ScrollPane myScrollPane;
	private VBox myVBox;
	private ListView<Behavior> myBehaviors;


	//in AuthoringDisplay, don't forget to remove and re-add this to the root every time it changes
	public ObjectInfoPanel(ResourceBundle resources, Game game, Node root) {
		super(resources, game, root);
		myScrollPane = new ScrollPane();
		myVBox = new VBox(DEFAULT_SPACING);
		initializeVBox();
		myScrollPane.setPrefWidth(PANE_PREF_WIDTH);
		myScrollPane.setContent(myVBox);
		myScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	private void initializeVBox() {
		myVBox.getChildren().add(makeBehaviorBar());


	}

	private Node makeBehaviorBar() {
		HBox hBox = new HBox(DEFAULT_SPACING);
		hBox.getChildren().add(new Label("Behaviors"));
		//make a new ButtonEvent that adds new Behaviors to the GameObject
		hBox.getChildren().add(makeButton("+", event -> doNothing()));
		return hBox;
	}

	private void makeBehaviorList() {
		GameObject currObject = getGame().getSceneManager().getCurrentScene().getCurrentGameObject();
		if(currObject != null) {
			for(Behavior b : currObject.getBehaviors()) {
				BehaviorView view = new BehaviorView(getResources(), getGame(), getRoot(), b);
				myVBox.getChildren().add(view.asNode());
			}
		}
		
//		myBehaviors = new ListView<Behavior>();
//		myBehaviors.setOnMouseClicked(event -> {
//			if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
//		        System.out.println("You double clicked!!!");
//		    }
//		});
//		myBehaviors.getSelectionModel().selectedItemProperty().addListener((o, old, neww) -> {
//			getGame().getSceneManager().getCurrentScene().setCurrentGameObject(neww);
//			myPropertyPanel.updatePanel();
//		});
//		return myLevelObjects;
	}

	public void updatePanel() {
		myVBox.getChildren().clear();
		initializeVBox();
		makeBehaviorList();
	}

	public ScrollPane asScrollPane() {
		return myScrollPane;
	}

	@Override
	protected Node asNode() {
		return myScrollPane;
	}
}
