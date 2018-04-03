package authoring;

import java.util.HashSet;
import java.util.Set;

public class SceneManager {
	
	private GameScene currentScene;
	private Set<GameScene> levels;

	public SceneManager() {
		currentScene = new GameScene();
		levels = new HashSet<>();
	}
	
	public GameScene makeScene() {
		GameScene newLevel = new GameScene();
		levels.add(newLevel);
		return newLevel;
	}
	
	public GameScene getCurrentScene() {
		return currentScene;
	}
	
	public void setCurrentScene(GameScene on) {
		currentScene = on;
	}
	
	public void removeScene(GameScene toRemove) {
		levels.remove(toRemove);
	}
	
}
