package authoring;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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
		System.out.println("SceneBackground getPane()");
		System.out.println(pane.getChildren().size());
		return pane;
	}
	
	public void addImage(Image image) {
		SceneBackgroundImage i = new SceneBackgroundImage(image);
		pane.getChildren().add(i.getPane());
		System.out.println(pane.getChildren().size());
	}
	
	public void clearPane() {
		pane.getChildren().removeAll();
	}
	
	
}
