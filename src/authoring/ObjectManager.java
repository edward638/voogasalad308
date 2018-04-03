package authoring;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ObjectManager {

	private Map<GameScene, List<GameObject>> placedObjects;
	private Set<GameObject> objectTemplates;
	
	public ObjectManager() {
		
	}
	
	//makes a game object with no properties
	public GameObject makeObjectTemplate() {
		
	}
	
	public void placeGameObject(GameScene currScene, GameObject toPlace) {
		
	}

	//makes a game object with the given property
	public GameObject makeGameObject(Property prop) { 
		gameObject = new GameObject();
		gameObject.addProperty(prop);
	}
	
	//deletes a game object
	public void deleteGameObject(GameObject gameObject) {
		
	}

	//returns the list of all game objects currently placed in the game
	public List<GameObject> getPlacedObjects() {
		
	}

	//returns the list of all game object "templates" available to use
	public List<GameObject> getObjectTemplates() {
		
	}
}
