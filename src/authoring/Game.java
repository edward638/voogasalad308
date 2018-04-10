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
	
}
