package authoring;

import javafx.scene.image.Image;

public class GameScene {
	
	//has a list of objects
	private Image myBackground;

	public GameScene() {
		
	}
	
	//gets image set as scene background
	public Image getBackgroundImage() {
		return myBackground;
	}
	
	//sets image for scene background
	public void setBackgroundImage(Image backgroundImage) {
		myBackground = backgroundImage;
	}
	
}
