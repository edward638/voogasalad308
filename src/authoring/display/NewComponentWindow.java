package authoring.display;

import java.util.ResourceBundle;

import authoring.Game;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Maddie Wilkinson
 *
 */
public abstract class NewComponentWindow extends AuthoringUIComponent {

	private Stage myStage;
	
	public NewComponentWindow(ResourceBundle resources, Game game, Node root) {
		super(resources, game, root);
		myStage = new Stage();
	}
	
	protected void setStage(Scene scene) {
		myStage.setScene(scene);
		myStage.centerOnScreen();
		myStage.show();
	}
	
	protected Stage getStage() {
		return myStage;
	}
	
	protected abstract Scene setUpScene();

}
