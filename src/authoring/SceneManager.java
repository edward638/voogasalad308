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
	private List<GameScene> myScenes;

	public SceneManager() {
		myScenes = new ArrayList<>();
		GameScene newLevel = new GameScene("Scene 1", "Level 1");
//		GameScene modelGamePart1Scene = new Converter2().gamePart2GameScene(new ModelGamePart1().getGamePart());
//		myLevels.add(modelGamePart1Scene);
		myScenes.add(newLevel);
		currentScene = newLevel;
		setChanged();
	}
	
	public GameScene makeScene(String name, String id) {
		GameScene newLevel = new GameScene(name, id);
		myScenes.add(newLevel);
//		myLevels.add(level - 1, newLevel);
		notifyMyObservers();
		return newLevel;
	}
	
	public GameScene getCurrentScene() {
		return currentScene;
	}
	
	public void setCurrentScene(String name) {
		for (GameScene scene: myScenes) {
			if (scene.getName().equals(name)) {
				currentScene = scene;
			}
		}
//		System.out.println("current scene is " + currentScene);
		notifyMyObservers();
	}
	
	public void removeScene(GameScene toRemove) {
		myScenes.remove(toRemove);
		notifyMyObservers();
	}
	
	@Override
	public List<GameScene> getScenes(){
		return myScenes;
	}
	
	public void restoreScenes(List<GameScene> list) {
		myScenes = list;
		currentScene = myScenes.get(0);
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
	
	@Override 
	public String getCurrentSceneId() {
		return getCurrentScene().getId();
	}
	 @Override
	public void setCurrentSceneName(String name) {
		getCurrentScene().setName(name);
	}
	@Override
	public void setCurrentLevelId(String id) {
		getCurrentScene().setId(id);
	}
	
	
}
