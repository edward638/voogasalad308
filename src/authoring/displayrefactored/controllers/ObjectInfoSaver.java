package authoring.displayrefactored.controllers;

import java.util.List;

import authoring.GameObject;
import javafx.scene.image.Image;

public interface ObjectInfoSaver {
	List<GameObject> getAllGameObjects();
	void duplicateGameObject();
	Image getImage(String imageName);
	void updatePositions();
	void setImage(Image image, String imageName);
}
