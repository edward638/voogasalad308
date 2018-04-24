package authoring;

import java.util.ArrayList;
import java.util.List;

/** 
 * SceneManager keeps track of all the GameScenes
 * and all the levels
 * 
 * @author: Summer
 **/
public class SceneManager {
	
	private GameScene currentScene;
	private List<GameScene> myLevels;

	public SceneManager() {
		myLevels = new ArrayList<>();
	}
	
	public GameScene makeScene(String name, int level) {
		GameScene newLevel = new GameScene(name);
		myLevels.add(level - 1, newLevel);
		return newLevel;
	}
	
	public GameScene getCurrentScene() {
		return currentScene;
	}
	
	public void setCurrentScene(GameScene on) {
		currentScene = on;
		System.out.println("current scene is " + currentScene);
	}
	
	public void removeScene(GameScene toRemove) {
		myLevels.remove(toRemove);
	}
	
	public List<GameScene> getScenes(){
		return myLevels;
	}
	
	public void restoreScenes(List<GameScene> list) {
		myLevels = list;
		setCurrentScene(myLevels.get(0));
		System.out.println("Restore scenes" + list);
	}
	
}
