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
		
		myPane.getChildren().add(imageHolder);
		
		cornerCircle = new Circle();
		
		System.out.println(imageHolder.getBoundsInParent().getWidth() + " width height " + imageHolder.getBoundsInParent().getHeight());
		
		cornerCircle.setCenterX(width);
		cornerCircle.setCenterY(height);
		cornerCircle.setRadius(10);
		cornerCircle.setFill(Color.TRANSPARENT);
		cornerCircle.setStroke(Color.BLACK);
		
		imageHolder.setOnMousePressed(t -> onMousePressed(t));
		imageHolder.setOnMouseDragged(t -> onMouseDragged(t));
		imageHolder.setOnMouseClicked(t -> onMouseClicked());
		cornerCircle.setOnMouseDragged(t -> onMouseDraggedCircle(t));
		
		imageHolder.getChildren().add(myImage);
		imageHolder.setStyle("-fx-border-color: red");
		
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
		
//		System.out.println("This is the mouse scene location: " + currentX + ", " + currentY);
//		System.out.println("This is the mouse position location: " + positionX + ", " + positionY);
//		System.out.println("This is the pane location: " + imageHolder.getTranslateX() + ", " + imageHolder.getTranslateY());
//		System.out.println("This is the Circle location: " + cornerCircle.getCenterX() + ", " + cornerCircle.getCenterY());
	}
	
	private void onMouseDragged(MouseEvent t) {
		
		double currentX2 = t.getSceneX();
		double currentY2 = t.getSceneY();
		
		if (selected) {
			if (Math.hypot(currentX2-(cornerCircle.getCenterX()+imageHolder.getTranslateX()), currentY2-(cornerCircle.getCenterY()+imageHolder.getTranslateY())) < cornerCircle.getRadius()*5) {
				resizeImage(t);	
			} else {
				dragImage(t);
			}
			
			imageHolder.setStyle("-fx-border-color: green");
			selected = true;
		}
	}
	
	private void onMouseDraggedCircle(MouseEvent t) {
		
		if (selected) {
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
		
//		System.out.println(width + " width height" + height);
//		System.out.println(imageHolder.getWidth() + " width imageholder height" + imageHolder.getHeight());
//		
		cornerCircle.setCenterX(imageHolder.getWidth());
		cornerCircle.setCenterY(imageHolder.getHeight());
		imageHolder.getChildren().remove(cornerCircle);
		
		imageHolder.getChildren().add(cornerCircle);
		
//		System.out.println(cornerCircle.getLayoutX() + " x y " + cornerCircle.getLayoutY());
		
		wasEdited = true;
		
	}
	
	private void updateDimensions() {
		height = myImage.getBoundsInLocal().getHeight();
		width = myImage.getBoundsInLocal().getWidth();
		imageHolder.setMaxWidth(width);
		imageHolder.setMaxHeight(height);
		
	}
	
	
	private void remove() {
		
		
	}
	
}
