package authoring.display;

import java.util.List;
import java.util.ResourceBundle;

import authoring.Behavior;
import authoring.Game;
import authoring.GameObject;
import authoring.Property;
import data.ImageManager;
import engine.behaviors.MandatoryBehavior;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class GameViewWindow extends AuthoringUIComponent{

	List<GameObject> gameObjects;
	StackPane stackPane;
	TilePane tilePane;
	Pane pane;
	ImageManager imageManager;
	
	public GameViewWindow(ResourceBundle resources, Game game, Node root){
		super(resources, game, root);
		stackPane = new StackPane();
		tilePane = new TilePane();
		pane = new Pane();
		setGameObjectList(game);
		addObjectsToPane();
		imageManager = new ImageManager(game.getName());
		stackPane.getChildren().add(tilePane);
		stackPane.getChildren().add(pane);
	}

	private void setGameObjectList(Game game){
		gameObjects = game.getSceneManager().getCurrentScene().getMyObjects();
	}
	
	public void updateWindow(List<GameObject> gameObjectsList) {
		gameObjects = gameObjectsList;
		addObjectsToPane();
	}
	
	private void addObjectsToPane() {
		for (GameObject object: gameObjects) {
			Behavior mandatoryBehavior = object.getBehavior("MandatoryBehavior");
			Property xPositionProperty = mandatoryBehavior.getProperty("xPos");
			Property yPositionProperty = mandatoryBehavior.getProperty("yPos");
			Property imagePathProperty = mandatoryBehavior.getProperty("imagePath");
			Double xPosition = (Double) xPositionProperty.getValue();
			Double yPosition = (Double) yPositionProperty.getValue();
			String imagePath = (String) imagePathProperty.getValue();
			placeObject(xPosition, yPosition, imagePath);
		}
	}
	
	private void placeObject(Double x, Double y, String imagePath) {
		ImageView imageView = new ImageView(imageManager.getImage(imagePath));
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		pane.getChildren().add(imageView);
	}
	
	private void makeBackgroundPane() {
		
	}
	
	public Node asPane() {
		// TODO Auto-generated method stub
		return null;
	}

}
