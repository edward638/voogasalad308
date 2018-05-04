package authoring.display.controllers;

import data.ImageManager;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Edward Zhuang
 * Abstract Controller class. Contains ImageManager to provide front end components with relevant imageviews.
 * Also responsible for setting up observer/observable interactions between game components and front end components,
 * passing commands from the front end to the game, and for attaching front end components to the AuthoringEnvironment.
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


