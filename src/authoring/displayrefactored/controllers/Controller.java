package authoring.displayrefactored.controllers;

import data.ImageManager;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Edward Zhuang
 *
 */
public abstract class Controller {

	private ImageManager imageManager;
	
	public Controller(ImageManager imageManager) {
		this.imageManager = imageManager;
	}
	
	protected ImageManager getImageManager() {
		return imageManager;
	};
	
	protected abstract void initializeScreenComponents();
	
	protected abstract void setUpConnections();

	protected abstract void addToGUI(Pane pane);
	
	protected abstract void refreshView();
	
	public Image getImage(String imageName) {
		return imageManager.getImage(imageName);
	}
	
}


