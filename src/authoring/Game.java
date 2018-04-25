package authoring;

import data.GameInitializer;
import data.ImageManager;
import engine.behaviors.Behavior;
import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.sun.xml.internal.bind.v2.TODO;

import authoring.displayrefactored.GameObjectImageView;

/** 
 * Game keeps track of an entire game 
 * This includes the name, description, image, and managers
 * 
 * @author: Summer
 **/
public class Game {

	private String gameName;
	private String gameDescription;
	private String gameImage;
	private SceneManager mySceneManager;
	
	public Game() {
		this("TestGame");
	}
	
	public Game(String name) {
		gameName = name;
		mySceneManager = new SceneManager();
		new GameInitializer(gameName);
		gameImage = "draw-more-few-cloud.png";
	}
	
	public void restoreGame(List<GameScene> list) {
		mySceneManager.restoreScenes(list);
		System.out.println("restoreGame: " + list);
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

	public List<GameScene> getScenes(){
		return mySceneManager.getScenes();
	}
//TODO: are these necessary?	
//	//makes a game object with the given property
//	public GameObject makeGameObject(AuthBehavior basic) { 
//		GameObject gameObject = new GameObject(basic);
//		return gameObject;
//	}
//	
//	//removes a game object from its scenes list of objects
//	public void removeGameObject(GameObject gameObject) {
//		for(GameScene current : mySceneManager.getScenes()) {
//			for(GameObject object : current.getMyObjects()) {
//				if (object.equals(gameObject)) {
//					current.getMyObjects().remove(gameObject);
//				}
//			}
//		}
//	}
	
	public SceneManager getSceneManager() {
		return mySceneManager;
	}
	
	public void setSceneManager(SceneManager sceneManager) {
		mySceneManager = sceneManager;
	}
	
}
