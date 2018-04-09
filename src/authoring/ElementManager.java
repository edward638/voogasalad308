package authoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementManager {

	private String gameName;
	private String gameDescription;
	private String gameImage;
	private Map<GameScene, List<GameElement>> placedObjects;
	private GameElement currentObject;
	
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
	
	public void placeGameObject(GameScene currScene, GameElement toPlace) {
		if (!placedObjects.containsKey(currScene)) {
			List<GameElement> objects = new ArrayList<>();
			placedObjects.put(currScene, objects);
		}
		placedObjects.get(currScene).add(toPlace);
	}

	//makes a game object with the given property
	public GameElement makeGameObject(Behavior basic) { 
		GameElement gameObject = new GameElement(basic);
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
	
	public GameElement getCurrentObject() {
		return currentObject;
	}
	
	public void setCurrentObject(GameElement current) {
		currentObject = current;
	}
}
