package authoring;

import java.util.List;

import javafx.scene.image.Image;


public interface ObjectInfoObservable {
	GameObject getCurrentGameObject();
	List<GameObject> getInstances();
	String getCurrentImageName();
	List<GameObject> getMyObjects();
}
