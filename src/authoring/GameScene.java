package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.TreeSet;

/** 
 * GameScene is the background image of each level
 * 
 * @author: Summer
 **/
public class GameScene extends Observable implements GameViewObservable, ObjectInfoObservable, GameSceneSerializableCreator {
	
	//has a list of objects
	private String myName;
	private String levelId;
	private String backgroundImageName;
	private List<SceneBackgroundImageSerializable> backgroundImageSerializables;
	private List<GameObject> myObjects;
	private Set<String> myObjectNames;
	private GameObject currentGameObject;
	private GameSceneToCareTaker memento;

	public GameScene(String name, String id) {
		myName = name;
		levelId = id;
		myObjects = new ArrayList<>();
		myObjectNames = new TreeSet<>();
		backgroundImageSerializables = new ArrayList<>();	
	}

	public String getBackgroundImageName() {
		return backgroundImageName;
	}

	public void setBackgroundImageName() {
		this.backgroundImageName = myName.replaceAll("\\s", "") + "backgroundimage";
		System.out.println(backgroundImageName);
	}
	
	public GameScene(GameSceneSerializable scene) {
		myName = scene.getName();
		myObjects = scene.getMyObjects();
		backgroundImageSerializables = scene.getBackgroundImageSerializables();
		currentGameObject = scene.getCurrentGameObject();
		myObjectNames = scene.getMyObjectNames();
		backgroundImageName = scene.getBackgroundImageName();
	}
	
	@Override
	public Set<String> getMyObjectNames(){
		return myObjectNames;
	}
	
	public void addObject(GameObject toAdd) {
		backupGameScene();
		myObjects.add(toAdd);
		notifyMyObservers();
	}
	
	@Override
	public List<GameObject> getMyObjects(){
		return myObjects;
	}

	@Override
	public GameObject getCurrentGameObject() {
		return currentGameObject;
	}

	public void setCurrentGameObject(GameObject selectedGameObject) {
		currentGameObject = selectedGameObject;
//		System.out.println("Current game object is: " + currentGameObject);
		notifyMyObservers();
	}

	@Override
	public String getName() {
		return myName;
	}

	public void setName(String name) {
		myName = name;
	}
	
	public String getId() {
		return levelId;
	}

	public void setId(String id) {
		levelId = id;
	}
	
	@Override
	public String toString() {
		return myName;
	}
	
	public void addBackgroundImageSerializable(SceneBackgroundImageSerializable s) {
		backgroundImageSerializables.add(s);
		notifyMyObservers();
	}
	
	public boolean checkUniqueObjectNames(String name) {
		boolean isUniqueName = true;
		for (GameObject go : myObjects){
			if (go.getName().equals(name)) {
				isUniqueName = false;
			}
		}
		return isUniqueName;
	}
	
	@Override
	public List<GameObject> getInstances() {
		// TODO Auto-generated method stub
		List<GameObject> list = new ArrayList<>();
		GameObject gameObject = getCurrentGameObject();
		String name = gameObject.getName();
		for (GameObject go: myObjects) {
			if (name.equals(go.getName())) {
				list.add(go);
			}		
		}
		return list;
	}

	
	public void notifyMyObservers() {
		setChanged();
		notifyObservers();
	}

	@Override
	public String getCurrentImageName() {
		// TODO Auto-generated method stub
		AuthBehavior mandatoryBehavior = getCurrentGameObject().getMandatoryBehavior();
		Property imagePathProperty = mandatoryBehavior.getProperty("imagePath");
		String imagePath = (String) imagePathProperty.getValue();
//		System.out.println(imagePath);
		return (imagePath + ".png");
	}


	@Override
	public List<SceneBackgroundImageSerializable> getBackgroundImageSerializables() {
		return backgroundImageSerializables;
	}
	
	public void backupGameScene() {
		memento = new GameSceneMemento(myObjects, backgroundImageSerializables);
		System.out.println("backupGameScene() memento " + ((GameSceneToOriginator)memento).getGameObjects());

	}
	
	public void restorePreviousGameScene() {
		System.out.println("restorepreviousgamescene " + ((GameSceneToOriginator)memento).getGameObjects());
		myObjects = ((GameSceneToOriginator)memento).getGameObjects();
		backgroundImageSerializables = ((GameSceneToOriginator)memento).getSerializables();
		notifyMyObservers();
	}
	
	public void duplicateGameObject() {
		GameObject newGo = new GameObject(currentGameObject);
		myObjects.add(newGo);
	}

	
	
}
