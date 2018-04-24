package authoring.display;

import java.util.ResourceBundle;

import authoring.Game;
import javafx.scene.Node;

public abstract class MainWindowComponent extends AuthoringUIComponent {

	public MainWindowComponent(ResourceBundle resources, Game game, Node root) {
		super(resources, game, root);
	}

	protected abstract Node asNode();
}
