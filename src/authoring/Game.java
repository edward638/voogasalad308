package authoring;

import data.GameInitializer;
import data.ImageManager;
import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import authoring.displayrefactored.GameObjectImageView;

/** 
 * Game keeps track of an entire game 
 * This includes the name, description, image, and managers
 * 
 * @author: Summer
 **/
public class Game extends Observable implements GameViewObservable, ObjectInfoObservable, LevelsObservable, ViewRefreshInterface{

	private String gameName;
	private String gameDescription;
	private String gameImage;
	private SceneManager mySceneManager;
	private ImageManager myImageManager;
	private ObjectInfoObserver objectInfoObserver;
	
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
	
	public void restoreGame(List<GameScene> list) {
		mySceneManager.restoreScenes(list);
		notifyMyObservers();
	}
	
	public void setObjectInfoObserver(ObjectInfoObserver observer) {
		objectInfoObserver = observer;
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
	public GameObject makeGameObject(AuthBehavior basic) { 
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

			
			AuthBehavior mandatoryBehavior = go.getBehavior("MandatoryBehavior");
			
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
			GameObjectImageView draggableImageView = new GameObjectImageView(imageView, go, this);
			list.add(draggableImageView.getMyImage());
		}
		
		return list;
	}
	
	
	public void addSceneBackgroundImageSerializable(SceneBackgroundImageSerializable s) {
		getSceneManager().getCurrentScene().addBackgroundImageSerializable(s);
	}
	
	public List<SceneBackgroundImage> getBackgroundImages() {
		List<SceneBackgroundImage> list = new ArrayList<>();
		for (SceneBackgroundImageSerializable s: getSceneManager().getCurrentScene().getBackgroundImageSerializables()) {
			list.add(new SceneBackgroundImage(myImageManager.getBackgroundImage(s.getImagePath()), s));
		}
		
		return list;
	}
	
	@Override
	public GameObject getCurrentGameObject() {
		// TODO Auto-generated method stub
		return getSceneManager().getCurrentScene().getCurrentGameObject();
	}
	
	public void notifyMyObservers() {
		setChanged();
		notifyObservers();
	}

	@Override
	public List<GameObject> getInstances() {
		// TODO Auto-generated method stub
		List<GameObject> list = new ArrayList<>();
		GameObject gameObject = getCurrentGameObject();
		String name = gameObject.getName();
//		System.out.println("Object name:" + name);
//		System.out.println("Objectlist size: " + getGameObjects().size());
		for (GameObject go: getGameObjects()) {
			if (name.equals(go.getName())) {
				list.add(go);
			}		
		}
		return list;
	}

	public void notifyObjectInfoObservers(GameObject gameObject) {
		getSceneManager().getCurrentScene().setCurrentGameObject(gameObject);
		objectInfoObserver.notifyOfChanges();
		notifyObservers();
	}
	
	@Override
	public Image getCurrentImage() {
		// TODO Auto-generated method stub
		AuthBehavior mandatoryBehavior = getCurrentGameObject().getBehavior("MandatoryBehavior");
		Property imagePathProperty = mandatoryBehavior.getProperty("imagePath");
		String imagePath = (String) imagePathProperty.getValue();
//		System.out.println(imagePath);
		return myImageManager.getImage(imagePath + ".png");
	}
	
	public boolean checkUniqueObjectNames(String name) {
		boolean isUniqueName = true;
		for (GameObject go : getGameObjects()){
			if (go.getName().equals(name)) {
				isUniqueName = false;
			}
		}
		return isUniqueName;
	}
	
}
