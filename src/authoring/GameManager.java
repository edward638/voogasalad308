package authoring;

public class GameManager {

	private ElementManager objects;
	private SceneManager scenes;
	
	public GameManager() {
		objects = new ElementManager();
		scenes = new SceneManager();
	}
	
	public GameManager(ElementManager allObjects, SceneManager allScenes) {
		objects = allObjects;
		scenes = allScenes;
	}
	
	public ElementManager getObjectManager() {
		return objects;
	}
	
	public SceneManager getSceneManager() {
		return scenes;
	}
	
	public void placeGameObject(GameElement toPlace) {
		objects.placeGameObject(scenes.getCurrentScene(), toPlace);
	}
	
	public void removeGameObject(GameElement toRemove) {
		objects.removeGameObject(scenes.getCurrentScene(), toRemove);
	}
}
