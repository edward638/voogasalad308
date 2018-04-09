package authoring;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
	
	private GameScene currentScene;
	private List<GameScene> myLevels;

	public SceneManager() {
		myLevels = new ArrayList<>();
	}
	
	public GameScene makeScene(String name, int level) {
		GameScene newLevel = new GameScene(name);
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
	
	public List<GameScene> getScenes(){
		return myLevels;
	}
	
}
