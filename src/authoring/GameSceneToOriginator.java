package authoring;

import java.util.List;

public interface GameSceneToOriginator {

	List<GameObject> getGameObjects();
	List<SceneBackgroundImageSerializable> getSerializables();
	
}
