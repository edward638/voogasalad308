package authoring;

import java.util.List;

public interface ObjectInfoObservable {
	GameObject getCurrentGameObject();
	List<GameObject> getInstances();
	String getCurrentImageName();
	List<GameObject> getMyObjects();
}
