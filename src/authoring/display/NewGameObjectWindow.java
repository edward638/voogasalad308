package authoring.display;

import java.util.ResourceBundle;

import authoring.Game;
import javafx.scene.Node;
import javafx.scene.Scene;

public class NewGameObjectWindow extends NewComponentWindow {

	public NewGameObjectWindow(ResourceBundle resources, Game game, Node root) {
		super(resources, game, root);
		setStage(setUpScene());
	}

	@Override
	protected Scene setUpScene() {
		// TODO Auto-generated method stub
		return null;
	}

}
