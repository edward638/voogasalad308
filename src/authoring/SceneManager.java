package authoring;

import java.util.HashSet;
import java.util.Set;

public class SceneManager {
	
	private GameScene currentScene;
	private Set<GameScene> myLevels;

	public SceneManager() {
		currentScene = new GameScene();
		myLevels = new HashSet<>();
	}
	
	public GameScene makeScene() {
		GameScene newLevel = new GameScene();
		myLevels.add(newLevel);
		return newLevel;
	}
	
	public GameScene getCurrentScene() {
		return currentScene;
	}
	
	public void setCurrentScene(GameScene on) {
		currentScene = on;
	}
	
	public void removeScene(GameScene toRemove) {
		myLevels.remove(toRemove);
	}
	
	public Set<GameScene> getScenes(){
		return myLevels;
	}
	
}
