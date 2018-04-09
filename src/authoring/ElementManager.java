package authoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ElementManager {

	private String gameName;
	private String gameDescription;
	private String gameImage;
	private Map<GameScene, List<GameObject>> placedObjects;
	private GameObject currentObject;
	
	public ElementManager(String name) {
		placedObjects = new HashMap<>();
		gameName = name;
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
	
	public void placeGameObject(GameScene currScene, GameObject toPlace) {
		if (!placedObjects.containsKey(currScene)) {
			List<GameObject> objects = new ArrayList<>();
			placedObjects.put(currScene, objects);
		}
		placedObjects.get(currScene).add(toPlace);
	}

	//makes a game object with the given property
	public GameObject makeGameObject(Behavior basic) { 
		GameObject gameObject = new GameObject(basic);
		return gameObject;
	}
	
	//removes a game object from placedObjects
	public void removeGameObject(GameScene currScene, GameObject gameObject) {
		if(placedObjects.containsKey(currScene)) {
			placedObjects.get(currScene).remove(gameObject);
		}
	}

	//returns the list of all game objects currently placed in the game
	public Map<GameScene, List<GameObject>> getPlacedObjects() {
		return placedObjects;
	}
	
	public GameObject getCurrentObject() {
		return currentObject;
	}
	
	public void setCurrentObject(GameObject current) {
		currentObject = current;
	}
}
