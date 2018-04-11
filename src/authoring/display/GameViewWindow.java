package authoring.display;

import java.util.List;
import java.util.ResourceBundle;

import authoring.Behavior;
import authoring.Game;
import authoring.GameObject;
import authoring.Property;
import data.ImageManager;
import javafx.scene.Node;
import javafx.scene.image.Image;
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
		stackPane.getChildren().add(tilePane);
		stackPane.getChildren().add(pane);
	}

	private void setGameObjectList(Game game){
		gameObjects = game.getSceneManager().getCurrentScene().getMyObjects();
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
	
	private void makeBackgroundPane(String imageName, int xSize, int ySize) {
		tilePane.setMaxSize(xSize, ySize);
		Image image = imageManager.getImage(imageName);
		ImageView bgImage = new ImageView(image);
		int bgImageWidth = (int) bgImage.getFitWidth();
		int bgImageHeight = (int) bgImage.getFitHeight();
		int columnCount = xSize / bgImageWidth;
		int rowCount = ySize / bgImageHeight;
		
		for (int i = 0; i < columnCount * rowCount; i++) {
			tilePane.getChildren().add(bgImage);
		}
	}
	
	public Node asPane() {
		// TODO Auto-generated method stub
		return null;
	}

}
