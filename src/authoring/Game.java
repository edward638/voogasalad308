package authoring;

import data.GameInitializer;
import data.ImageManager;
import javafx.beans.InvalidationListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/** 
 * Game keeps track of an entire game 
 * This includes the name, description, image, and managers
 * 
 * @author: Summer
 **/
public class Game extends Observable implements GameViewObservable, ObjectInfoObservable, LevelsObservable{

	private String gameName;
	private String gameDescription;
	private String gameImage;
	private SceneManager mySceneManager;
	private ImageManager myImageManager;
	
	public Game() {
		this("TestGame");
	}
	
	public Game(String name) {
		gameName = name;
		mySceneManager = new SceneManager();
		myImageManager = new ImageManager(gameName);
		new GameInitializer(gameName);
		gameImage = "draw-more-few-cloud.png";
	}
	
	public void setGameName(String name) {
		gameName = name;
	}
	
	public String getName() {
		return gameName;
	}
	
	public void setGameDescription(String description) {
		gameDescription = description;
	}
	
	public String getGameDescription() {
		return gameDescription;
	}
	
	public void setGameImage(String image) {
		gameImage = image;
	}
	
	public String getGameImage() {
		return gameImage;
	}
	
	//makes a game object with the given property
	public GameObject makeGameObject(Behavior basic) { 
		GameObject gameObject = new GameObject(basic);
		return gameObject;
	}
	
	//removes a game object from its scenes list of objects
	public void removeGameObject(GameObject gameObject) {
		for(GameScene current : mySceneManager.getScenes()) {
			for(GameObject object : current.getMyObjects()) {
				if (object.equals(gameObject)) {
					current.getMyObjects().remove(gameObject);
				}
			}
		}
	}
	
	public SceneManager getSceneManager() {
		return mySceneManager;
	}
	
	public ImageManager getImageManager() {
		return myImageManager;
	}
	
	public void setSceneManager(SceneManager sceneManager) {
		mySceneManager = sceneManager;
	}

	@Override
	public List<GameObject> getGameObjects() {
		// TODO Auto-generated method stub
		return getSceneManager().getCurrentScene().getMyObjects();
	}

	@Override
	public List<GameScene> getScenes() {
		// TODO Auto-generated method stub
		return getSceneManager().getScenes();
	}

	@Override
	public List<ImageView> getImageViews() {
		// TODO Auto-generated method stub
		List<ImageView> list = new ArrayList<>();
	
		for (GameObject go: getSceneManager().getCurrentScene().getMyObjects()) {
			Behavior mandatoryBehavior = go.getBehavior("MandatoryBehavior");
			Property xPositionProperty = mandatoryBehavior.getProperty("xPos");
			Property yPositionProperty = mandatoryBehavior.getProperty("yPos");
			Property imagePathProperty = mandatoryBehavior.getProperty("imagePath");
			Double xPosition = (Double) xPositionProperty.getValue();
			Double yPosition = (Double) yPositionProperty.getValue();
			String imagePath = (String) imagePathProperty.getValue();
			ImageView imageView =new ImageView(myImageManager.getImage(imagePath + ".png"));
			imageView.setLayoutX(xPosition);
			imageView.setLayoutY(yPosition);
			imageView.setPreserveRatio(true);
			imageView.setFitHeight(200);
			list.add(imageView);
		}
		
		return list;
	}

	@Override
	public Pane getSceneBackgroundPane() {
		// TODO Auto-generated method stub
		return getSceneManager().getCurrentScene().getSceneBackground().getPane();
	}
	
	public void notifyMyObservers() {
		setChanged();
		notifyObservers();
	}
	
}
