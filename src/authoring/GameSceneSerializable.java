package authoring;

import java.util.List;
import java.util.Set;

public class GameSceneSerializable {
	
	private String myName;
	private List<SceneBackgroundImageSerializable> backgroundImageSerializables;
	private List<GameObject> myObjects;
	private Set<String> myObjectNames;
	private GameObject currentGameObject;
	
	public GameSceneSerializable(GameSceneSerializableCreator scene) {
		myName = scene.getName();
		myObjects = scene.getMyObjects();
		backgroundImageSerializables = scene.getBackgroundImageSerializables();
		currentGameObject = scene.getCurrentGameObject();
		myObjectNames = scene.getMyObjectNames();
	}
	
	public Set<String> getMyObjectNames(){
		return myObjectNames;
	}
	
	public List<GameObject> getMyObjects(){
		return myObjects;
	}
	
	public GameObject getCurrentGameObject() {
		return currentGameObject;
	}
	
	public List<SceneBackgroundImageSerializable> getBackgroundImageSerializables() {
		return backgroundImageSerializables;
	}
	
	public String getName() {
		return myName;
	}

	
}
