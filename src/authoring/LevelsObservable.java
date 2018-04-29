package authoring;

import java.util.List;

public interface LevelsObservable {
	
	List<GameScene> getScenes();
	
	String getCurrentSceneName();

	String getCurrentSceneId();

	void setCurrentSceneName(String name);

	void setCurrentLevelId(String id);
	
}
