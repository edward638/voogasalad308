package authoring;

public class Game {

	private String gameName;
	private String gameDescription;
	private String gameImage;
	private SceneManager mySceneManager;
	
	public Game() {
		mySceneManager = new SceneManager();
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
	
	//makes a game object with the given property
	public GameObject makeGameObject(Behavior basic) { 
		GameObject gameObject = new GameObject(basic);
		return gameObject;
	}
	
	//removes a game object from its scenes list of objects
	public void removeGameObject(GameObject gameObject) {
		for(GameScene current : mySceneManager.getScenes()) {
			for(GameObject object : current.getMyObjects()) {
				if (object.equals(gameObject)) {
					current.getMyObjects().remove(gameObject);
				}
			}
		}
	}
	
	public SceneManager getSceneManager() {
		return mySceneManager;
	}
	
	public void setSceneManager(SceneManager scenes) {
		mySceneManager = scenes;
	}
}
