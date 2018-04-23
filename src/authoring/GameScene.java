package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import data.propertiesFiles.ResourceBundleManager;
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
	private List<SceneBackgroundImageSerializable> backgroundImageSerializables;
	private List<GameObject> myObjects;
	private Set<String> myObjectNames;
	private GameObject currentGameObject;
	

	public GameScene(String name) {
		myName = name;
		myObjects = new ArrayList<>();
		myObjectNames = new TreeSet<>();
		backgroundImageSerializables = new ArrayList<>();
		mySceneBackground = new SceneBackground(ResourceBundleManager.getPosition("GAMEVIEWSIZE_X"), ResourceBundleManager.getPosition("GAMEVIEWSIZE_Y"));
//		System.out.println("New GameScene made!");
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
//		System.out.println("Current game object is: " + currentGameObject);
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

	public List<SceneBackgroundImageSerializable> getBackgroundImageSerializables() {
		return backgroundImageSerializables;
	}
	
	public void addBackgroundImageSerializable(SceneBackgroundImageSerializable s) {
		backgroundImageSerializables.add(s);
	}
	
	
}
