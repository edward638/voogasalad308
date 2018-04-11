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
	Pane myPane;
	ImageManager imageManager;

	public GameViewWindow(ResourceBundle resources, Game game, Node root){
		super(resources, game, root);
		stackPane = new StackPane();
		tilePane = new TilePane();
		myPane = new Pane();
		//		setGameObjectList(game);
		//		addObjectsToPane();
		imageManager = new ImageManager(getGame().getName());
		makeBackgroundPane(getGame().getGameImage(), 400, 400);
		stackPane.getChildren().add(tilePane);
		stackPane.getChildren().add(myPane);
	}

	private void setGameObjectList(){
		gameObjects = getGame().getSceneManager().getCurrentScene().getMyObjects();
		System.out.println((gameObjects == null) + "gameobjets");
		System.out.println((getGame().getSceneManager() == null) + "sceneman");
		System.out.println((getGame().getSceneManager().getCurrentScene() == null) + "curr");
		System.out.println((getGame().getSceneManager().getCurrentScene().getMyObjects() == null) + "myobjs");
		System.out.println( gameObjects.size() + "  objs size");


	}

//	public void updateWindow(List<GameObject> gameObjectsList) {
	public void updateWindow() {
		//		gameObjects = gameObjectsList;
		setGameObjectList();
		addObjectsToPane();
	}

	private void addObjectsToPane() {
		if(gameObjects != null) {
			for (GameObject object: gameObjects) {
				Behavior mandatoryBehavior = object.getBehavior("MandatoryBehavior");
				Property xPositionProperty = mandatoryBehavior.getProperty("xPos");
				Property yPositionProperty = mandatoryBehavior.getProperty("yPos");
				Property imagePathProperty = mandatoryBehavior.getProperty("imagePath");
				Double xPosition = (Double) xPositionProperty.getValue();
				Double yPosition = (Double) yPositionProperty.getValue();
				String imagePath = (String) imagePathProperty.getValue();
				System.out.println(xPosition + " " + yPosition + " " + imagePath);
				placeObject(xPosition, yPosition, imagePath);
			}
		}
	}

	private void placeObject(Double x, Double y, String imagePath) {
		System.out.println(imagePath);
		ImageView imageView = new ImageView(imageManager.getImage(imagePath + ".png"));
		imageView.setFitHeight(50);
		imageView.setFitWidth(50);
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		myPane.getChildren().add(imageView);
	}

	private void makeBackgroundPane(String imageName, int xSize, int ySize) {
		tilePane.setMaxSize(xSize, ySize);
		Image image = imageManager.getImage(imageName);
		ImageView bgImage = new ImageView(image);
//		int bgImageWidth = (int) bgImage.getFitWidth();
//		int bgImageHeight = (int) bgImage.getFitHeight();
		int bgImageWidth = 500;
		int bgImageHeight = 500;
		int columnCount = xSize / bgImageWidth;
		int rowCount = ySize / bgImageHeight;
		bgImage.setFitHeight(bgImageHeight);
		bgImage.setFitWidth(bgImageWidth);

//		for (int i = 0; i < columnCount * rowCount; i++) {
			tilePane.getChildren().add(bgImage);
//		}
	}

	public Node asPane() {
		return stackPane;
	}

}
