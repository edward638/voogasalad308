package authoring;

import java.util.HashSet;
import java.util.Set;

public class SceneManager {
	
	private GameScene currentScene;
	private Set<GameScene> myLevels;

	public SceneManager() {
		myLevels = new HashSet<>();
	}
	
	public GameScene makeScene() {
		int level = myLevels.size() + 1;
		String currLevel = "level" + level;
		GameScene newLevel = new GameScene(currLevel);
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
