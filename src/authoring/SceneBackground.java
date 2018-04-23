package authoring;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Edward Zhuang
 *
 */
public class SceneBackground {
	
	private Pane pane;
	private Rectangle borderRectangle;
	private int myXSize;
	private int myYSize;
	
	public SceneBackground(int xSize, int ySize) {
		pane = new Pane();
		myXSize = xSize;
		myYSize = ySize;
		pane.setPrefSize(xSize, ySize);
		borderRectangle = new Rectangle(xSize, ySize);
		pane.getChildren().add(borderRectangle);
		borderRectangle.setFill(Color.TRANSPARENT);
		borderRectangle.setStroke(Color.BLACK);
		
	}
	
	public Pane getPane() {
		return pane;
	}
	
	
	public void addImage(SceneBackgroundImage sceneBackgroundImage) {
		sceneBackgroundImage.setRectangle(borderRectangle);
		Pane imagePane = sceneBackgroundImage.getPane();
//		imagePane.setLayoutX(myXSize/2);
//		imagePane.setLayoutY(myYSize/2);
		pane.getChildren().add(imagePane);
	}
	
	public void clearPane() {
		pane.getChildren().removeAll();
	}
	
	
}
