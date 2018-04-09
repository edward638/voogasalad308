package authoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ElementManager {

	private Map<GameScene, List<GameElement>> placedObjects;
	private Set<GameElement> objectTemplates;
	private GameElement currentObject;
	
	public ElementManager() {
		placedObjects = new HashMap<>();
		objectTemplates = new HashSet<>();
		currentObject = new GameElement();
	}
	
	//makes a game object with no properties
	public GameElement makeObjectTemplate() {
		GameElement gameObject = new GameElement();
		return gameObject;
	}
	
	public GameElement saveAsTemplate(GameElement o) {
		objectTemplates.add(o);
		return o;
	}
	
	public void placeGameObject(GameScene currScene, GameElement toPlace) {
		if (!placedObjects.containsKey(currScene)) {
			List<GameElement> objects = new ArrayList<>();
			placedObjects.put(currScene, objects);
		}
		placedObjects.get(currScene).add(toPlace);
	}

	//makes a game object with the given property
	public GameElement makeGameObject(Behavior toAdd) { 
		GameElement gameObject = new GameElement();
		gameObject.addBehavior(toAdd);
		return gameObject;
	}
	
	//removes a game object from placedObjects
	public void removeGameObject(GameScene currScene, GameElement gameObject) {
		if(placedObjects.containsKey(currScene)) {
			placedObjects.get(currScene).remove(gameObject);
		}
	}

	//returns the list of all game objects currently placed in the game
	public Map<GameScene, List<GameElement>> getPlacedObjects() {
		return placedObjects;
	}

	//returns the list of all game object "templates" available to use
	public Set<GameElement> getObjectTemplates() {
		return objectTemplates;
	}
	
	public GameElement getCurrentObject() {
		return currentObject;
	}
	
	public void setCurrentObject(GameElement current) {
		currentObject = current;
	}
}
