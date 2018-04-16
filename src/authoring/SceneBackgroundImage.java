package authoring;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * 
 * @author Edward Zhuang
 *
 */
public class SceneBackgroundImage {
	
	private Pane myPane;
	private ImageView myImage;
	private static final int DEFAULT_X = 50;
	private double height;
	private double width;
	private double currentX;
	private double currentY;
	private double positionX;
	private double positionY;
	private Circle cornerCircle;
	private boolean wasEdited = false;
	private boolean selected = false;
	private Pane imageHolder;
	
	
	public SceneBackgroundImage(SceneBackground background, Image image) {
		
		myPane = background.getPane();
		myImage = new ImageView(image);
		myImage.setPreserveRatio(true);
		myImage.setFitWidth(DEFAULT_X);
			
		imageHolder = new Pane();
		
		updateDimensions();
		
		cornerCircle = new Circle();
		cornerCircle.setCenterX(imageHolder.getBoundsInParent().getWidth());
		cornerCircle.setCenterY(imageHolder.getBoundsInParent().getHeight());
		cornerCircle.setRadius(5);
		cornerCircle.setFill(Color.TRANSPARENT);
		cornerCircle.setStroke(Color.BLACK);
		
		imageHolder.setOnMousePressed(t -> onMousePressed(t));
		imageHolder.setOnMouseDragged(t -> onMouseDragged(t));
		imageHolder.setOnMouseClicked(t -> onMouseClicked());
		
		imageHolder.getChildren().add(myImage);
		imageHolder.setStyle("-fx-border-color: red");
		
		myPane.getChildren().add(imageHolder);
		imageHolder.getChildren().add(cornerCircle);		
	}
	
	private void onMouseClicked() {
		selected = !selected;
		
		
		if (selected) {
			imageHolder.setStyle("-fx-border-color: green");
		} else if (! wasEdited){
			imageHolder.setStyle("-fx-border-color: red");
		} else {
			selected = true;
		}
		wasEdited = false;
		
	}
	
	private void onMousePressed(MouseEvent t) {
		
		currentX = t.getSceneX();
		currentY = t.getSceneY();

		positionX = ((Pane)(t.getSource())).getTranslateX();
		positionY = ((Pane)(t.getSource())).getTranslateY();
		
	}
	
	private void onMouseDragged(MouseEvent t) {
		if (selected) {
//			dragImage(t);
			resizeImage(t);
			imageHolder.setStyle("-fx-border-color: green");
			selected = true;
		}
	}
	
	private void dragImage(MouseEvent t) {
		
		double newX = t.getSceneX();
		double newY = t.getSceneY();	
		double translateX = newX - currentX + positionX;
		double translateY = newY - currentY + positionY;
		imageHolder.setTranslateX(translateX);
		imageHolder.setTranslateY(translateY);
		wasEdited = true;
		
	}
	
	private void resizeImage(MouseEvent t) {
		
		double newX = t.getSceneX();
		
		double newSize = width + (newX - currentX);
		currentX = t.getSceneX();
		width = newSize;
		
		myImage.setFitWidth(newSize);
		updateDimensions();
		wasEdited = true;
		
	}
	
	private void updateDimensions() {
		height = myImage.getBoundsInLocal().getHeight();
		width = myImage.getBoundsInLocal().getWidth();
		imageHolder.setMinSize(height, width);
		
	}
	
	private void remove() {
		
		
	}
	
}
