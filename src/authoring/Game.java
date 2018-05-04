package authoring;

import data.GameInitializer;
import java.util.List;

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
	
	/**
	 * @param list is a list of scenes that represents one game
	 * restores a certain scene in a game
	 */
	public void restoreGame(List<GameScene> list) {
		mySceneManager.restoreScenes(list);
		System.out.println("restoreGame: " + list);
	}
	
	/**
	 * 
	 * @param name new game name
	 */
	public void setGameName(String name) {
		gameName = name;
	}
	
	/**
	 * 
	 * @return gameName
	 */
	public String getName() {
		return gameName;
	}
	
	/**
	 * 
	 * @param description is the new game description 
	 */
	public void setGameDescription(String description) {
		gameDescription = description;
	}
	
	/**
	 * 
	 * @return gameDescription
	 */
	public String getGameDescription() {
		return gameDescription;
	}
	
	/**
	 * 
	 * @param image is new image path
	 */
	public void setGameImage(String image) {
		gameImage = image;
	}
	
	/**
	 * 
	 * @return gameImage
	 */
	public String getGameImage() {
		return gameImage;
	}

	/** 
	 * 
	 * @return gameScenes in the game
	 */
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
	
	/**
	 * 
	 * @return sceneManager
	 */
	public SceneManager getSceneManager() {
		return mySceneManager;
	}
	
	/**
	 * 
	 * @param sceneManager is new sceneManager
	 */
	public void setSceneManager(SceneManager sceneManager) {
		mySceneManager = sceneManager;
	}
	
}
