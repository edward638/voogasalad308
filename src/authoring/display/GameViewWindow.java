package authoring.display;

import java.util.List;
import java.util.ResourceBundle;

import authoring.Game;
import authoring.GameObject;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class GameViewWindow extends AuthoringUIComponent{

	List<GameObject> gameObjects;
	StackPane stackPane;
	TilePane tilePane;
	Pane pane;
	
	public GameViewWindow(ResourceBundle resources, Game game, Node root){
		super(resources, game, root);
		
	}

	private void setGameObjectList(Game game){
		game.getSceneManager().getCurrentScene();
	}
	
	
	private void addObjectsToPane() {
		
	}
	
	public Node asPane() {
		// TODO Auto-generated method stub
		return null;
	}

}
