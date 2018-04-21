package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.scene.image.Image;

/** 
 * GameScene is the background image of each level
 * 
 * @author: Summer
 **/
public class GameScene {
	
	//has a list of objects
	private String myName;
	private SceneBackground mySceneBackground;
	private List<GameObject> myObjects;
	private Set<String> myObjectNames;
	private GameObject currentGameObject;
	
	private static final int SCENE_SIZE_X = 1000;
	private static final int SCENE_SIZE_Y = 1000;

	public GameScene(String name) {
		myName = name;
		myObjects = new ArrayList<>();
		myObjectNames = new TreeSet<>();
		mySceneBackground = new SceneBackground(SCENE_SIZE_X, SCENE_SIZE_Y);
		System.out.println("This shit is being made again isn't it.");
	}
	
	public void addObject(GameObject toAdd) {
		myObjects.add(toAdd);
	}
	
	public List<GameObject> getMyObjects(){
		return myObjects;
	}

	public GameObject getCurrentGameObject() {
		return currentGameObject;
	}

	public void setCurrentGameObject(GameObject selectedGameObject) {
		currentGameObject = selectedGameObject;
		System.out.println("Current game object is: " + currentGameObject);
	}

	public String getName() {
		return myName;
	}

	public void setName(String name) {
		myName = name;
	}
	
	public String toString() {
		return myName;
	}
	
	public SceneBackground getSceneBackground() {
		return mySceneBackground;
	}
	
}
