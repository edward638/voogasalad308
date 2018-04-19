package authoring.display;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeSet;

import authoring.Behavior;
import authoring.Game;
import authoring.GameObject;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class ObjectInformationDisplay extends AuthoringUIComponent {

	private Pane pane;
	private TextArea text;
	
	public ObjectInformationDisplay(ResourceBundle resources, Game game, Node root) {
		super(resources, game, root);
		// TODO Auto-generated constructor stub
		text = new TextArea();
		pane = new Pane();
		pane.getChildren().add(text);
		text.setText("This is where all the information about the object can be");
	}

	public void update(GameObject object) {
		text.setText("Object name: " + object.getName());
		text.appendText("\n");
		HashSet<Behavior> set = (HashSet<Behavior>) object.getBehaviors();
		for (Behavior b: set) { 
			text.appendText("Object behavior: " + b.getName());
			text.appendText("\n");
		}
	}

	public Node asPane() {
		// TODO Auto-generated method stub
		return pane;
	}
	
}
