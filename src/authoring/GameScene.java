package authoring;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class GameScene {
	
	//has a list of objects
	private String myName;
	private Image myBackground;
	private List<GameObject> myObjects;
	//have some kind of index that tells what order the levels play in

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
	
	public List<GameObject> getMyElements(){
		return myObjects;
	}

	public String getName() {
		return myName;
	}

	public void setName(String name) {
		myName = name;
	}
	
}
