package authoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ObjectManager {

	private Map<GameScene, List<GameObject>> placedObjects;
	private Set<GameObject> objectTemplates;
	private GameObject currentObject;
	
	public ObjectManager() {
		placedObjects = new HashMap<>();
		objectTemplates = new HashSet<>();
		currentObject = new GameObject();
	}
	
	//makes a game object with no properties
	public GameObject makeObjectTemplate() {
		GameObject gameObject = new GameObject();
		return gameObject;
	}
	
	public GameObject saveAsTemplate(GameObject o) {
		objectTemplates.add(o);
		return o;
	}
	
	public void placeGameObject(GameScene currScene, GameObject toPlace) {
		if (!placedObjects.containsKey(currScene)) {
			List<GameObject> objects = new ArrayList<>();
			placedObjects.put(currScene, objects);
		}
		placedObjects.get(currScene).add(toPlace);
	}

	//makes a game object with the given property
	public GameObject makeGameObject(Behavior toAdd) { 
		GameObject gameObject = new GameObject();
		gameObject.addBehavior(toAdd);
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

	//returns the list of all game object "templates" available to use
	public Set<GameObject> getObjectTemplates() {
		return objectTemplates;
	}
	
	public GameObject getCurrentObject() {
		return currentObject;
	}
	
	public void setCurrentObject(GameObject current) {
		currentObject = current;
	}
}
