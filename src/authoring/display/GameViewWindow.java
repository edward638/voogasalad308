package authoring.display;

import java.util.List;
import java.util.ResourceBundle;

import authoring.Behavior;
import authoring.Game;
import authoring.GameObject;
import authoring.GameScene;
import authoring.Property;
import authoring.SceneBackground;
import authoring.SceneManager;
import data.ImageManager;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameViewWindow extends AuthoringUIComponent{

	List<GameObject> gameObjects;
	ScrollPane scrollPane;
	StackPane stackPane;
	Pane sceneBackgroundPane;
	Pane myPane;
	ImageManager imageManager;
	int xSize, ySize;

	public GameViewWindow(ResourceBundle resources, Game game, Node root, int x_Size, int y_Size){
		super(resources, game, root);
		scrollPane = new ScrollPane();
		stackPane = new StackPane();
		sceneBackgroundPane = new Pane();
		myPane = new Pane();
		//		setGameObjectList(game);
		//		addObjectsToPane();
		imageManager = new ImageManager(getGame().getName());
		xSize = x_Size;
		ySize = y_Size;
//		makeBackgroundPane(getGame().getGameImage(), xSize, ySize);
		initBackground();
		stackPane.getChildren().add(sceneBackgroundPane);
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
		stackPane.getChildren().clear();
//		sceneBackgroundPane.getChildren().clear();
		myPane.getChildren().clear();
		setGameObjectList();
		addObjectsToPane();
//		makeBackgroundPane(getGame().getGameImage(), xSize, ySize);
		makeBackgroundPane();
		stackPane.getChildren().add(sceneBackgroundPane);
		stackPane.getChildren().add(myPane);
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

	/**
	 * @param imageName desired background image that will be tiled if smaller than the game size
	 * @param xSize refers to total size of the game/platform
	 * @param ySize refers to the total size of the game/platform
	 */
	private void makeBackgroundPane() {
//		tilePane.setMaxSize(xSize, ySize);
//		Image image = imageManager.getImage(imageName);
//		ImageView bgImage = new ImageView(image);
////		int bgImageWidth = (int) bgImage.getFitWidth();
////		int bgImageHeight = (int) bgImage.getFitHeight();
//		int bgImageWidth = xSize;
//		int bgImageHeight = ySize;
//		int columnCount = xSize / bgImageWidth;
//		int rowCount = ySize / bgImageHeight;
//		bgImage.setFitHeight(bgImageHeight);
//		bgImage.setFitWidth(bgImageWidth);
//
//		for (int i = 0; i < columnCount * rowCount; i++) {
//			tilePane.getChildren().add(bgImage);
//		}
//		tilePane = getGame().getSceneManager().getCurrentScene().getScenceBackground().getPane();
		SceneManager sm = getGame().getSceneManager();
		GameScene gs = sm.getCurrentScene();
		SceneBackground sb = gs.getSceneBackground();
		sceneBackgroundPane = sb.getPane();
		System.out.println("scene background pane added from game view window");
		
		
	}
	private void initBackground() {
		sceneBackgroundPane.setPrefSize(1000, 1000);
	}
	/**
	 * This creates the scroll pane, and should be the size of the "camera" view in authoring/player
	 */
	private void makeScrollPane() {
		
	}
	
	public void switchPanes(String key) {
		if (key.equals("Background")) {
			stackPane.getChildren().clear();
			stackPane.getChildren().add(sceneBackgroundPane);
		} 
		if (key.equals("Foreground")) {
			stackPane.getChildren().clear();
			stackPane.getChildren().add(sceneBackgroundPane);
			stackPane.getChildren().add(myPane);
		}
	}

	public Node asPane() {
		return stackPane;
	}

}
