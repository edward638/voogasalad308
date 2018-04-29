package authoring;

import java.util.List;
import java.util.Set;

public interface GameSceneSerializableCreator {

	List<SceneBackgroundImageSerializable> getBackgroundImageSerializables();
	List<GameObject> getMyObjects();
	String getName();
	GameObject getCurrentGameObject();
	Set<String> getMyObjectNames();
	
}
