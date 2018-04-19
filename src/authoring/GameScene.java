package authoring;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/** 
 * GameScene is the background image of each level
 * 
 * @author: Summer
 **/
public class GameScene {
	
	//has a list of objects
	private String myName;
	private Image myBackground;
	private List<GameObject> myObjects;
	private GameObject currentGameObject;

	public GameScene(String name) {
		myName = name;
		myObjects = new ArrayList<>();
	}
	
	//gets image set as scene background
	public Image getBackgroundImage() {
		return myBackground;
	}
	
	//sets image for scene background
	public void setBackgroundImage(Image backgroundImage) {
		myBackground = backgroundImage;
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
	
}
