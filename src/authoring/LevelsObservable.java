package authoring;

import java.util.List;

public interface LevelsObservable {
	
	List<GameScene> getScenes();
	
	String getCurrentSceneName();
	
}
