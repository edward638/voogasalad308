package authoring;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Edward Zhuang
 *
 */
public class SceneBackground {
	
	private Pane pane;
	
	public SceneBackground(int xSize, int ySize) {
		pane = new Pane();
		pane.setPrefSize(xSize, ySize);
	}
	
	public Pane getPane() {
		return pane;
	}
	
	public void addImage(Image image) {
		SceneBackgroundImage i = new SceneBackgroundImage(this, image);
	}
	
	public void clearPane() {
		pane.getChildren().removeAll();
	}
	
	
}
