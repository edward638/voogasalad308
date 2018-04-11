package authoring;

public class GameManager {

	private ObjectManager objects;
	private SceneManager scenes;
	
	public GameManager() {
		objects = new ObjectManager("Mario Game");
		scenes = new SceneManager();
	}
	
	public GameManager(ObjectManager allObjects, SceneManager allScenes) {
		objects = allObjects;
		scenes = allScenes;
	}
	
	public ObjectManager getObjectManager() {
		return objects;
	}
	
	public SceneManager getSceneManager() {
		return scenes;
	}
	
	public void placeGameObject(GameObject toPlace) {
		objects.placeGameObject(scenes.getCurrentScene(), toPlace);
	}
	
	public void removeGameObject(GameObject toRemove) {
		objects.removeGameObject(scenes.getCurrentScene(), toRemove);
	}
}
