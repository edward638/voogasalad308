package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/** 
 * SceneManager keeps track of all the GameScenes
 * and all the levels
 * 
 * @author: Summer
 **/
public class SceneManager extends Observable implements LevelsObservable {
	
	private GameScene currentScene;
	private List<GameScene> myLevels;

	public SceneManager() {
		myLevels = new ArrayList<>();
		GameScene newLevel = new GameScene("Level 1");
		myLevels.add(newLevel);
		setCurrentScene(newLevel);
		setChanged();
	}
	
	public GameScene makeScene(String name, int level) {
		GameScene newLevel = new GameScene(name);
		myLevels.add(level - 1, newLevel);
		notifyMyObservers();
		return newLevel;
	}
	
	public GameScene getCurrentScene() {
		return currentScene;
	}
	
	public void setCurrentScene(GameScene on) {
		currentScene = on;
//		System.out.println("current scene is " + currentScene);
		notifyMyObservers();
	}
	
	public void removeScene(GameScene toRemove) {
		myLevels.remove(toRemove);
		notifyMyObservers();
	}
	
	@Override
	public List<GameScene> getScenes(){
		return myLevels;
	}
	
	public void restoreScenes(List<GameScene> list) {
		myLevels = list;
		setCurrentScene(myLevels.get(0));
		notifyMyObservers();
//		System.out.println("Restore scenes" + list);
	}
	
	public void notifyMyObservers() {
		setChanged();
		notifyObservers();
	}


	@Override
	public String getCurrentSceneName() {
		// TODO Auto-generated method stub
		return getCurrentScene().getName();
	}
	
}
