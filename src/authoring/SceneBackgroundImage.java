package authoring;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Edward Zhuang
 *
 */
public class SceneBackgroundImage {
	
	private Pane myPane;
	private Rectangle myRectangle;
	private ImageView myImage;
	private static final int DEFAULT_X = 200;
	private static final int BORDER = 20;
	private double height;
	private double width;
	private double currentX;
	private double currentY;
	private double positionX;
	private double positionY;
	private Circle cornerCircle;
	private boolean wasEdited = false;
	private boolean selected = false;
	private BorderPane imageHolder;
	private SceneBackgroundImageSerializable serializable;
	
	
	public SceneBackgroundImage(Image image, SceneBackgroundImageSerializable serializable) {
		
		this.serializable = serializable; 
		myImage = new ImageView(image);
		myImage.setPreserveRatio(true);
		myImage.setFitWidth(serializable.getxSize());
			
		
		
		imageHolder = new BorderPane();
		imageHolder.setLayoutX(0);
		imageHolder.setLayoutY(0);
		
		updateDimensions();
		
		
//		cornerCircle = new Circle();
//		System.out.println(imageHolder.getBoundsInParent().getWidth() + " width height " + imageHolder.getBoundsInParent().getHeight());
//		
//		cornerCircle.setCenterX(width);
//		cornerCircle.setCenterY(height);
//		cornerCircle.setRadius(10);
//		cornerCircle.setFill(Color.TRANSPARENT);
//		cornerCircle.setStroke(Color.BLACK);
		
		imageHolder.setOnMousePressed(t -> onMousePressed(t));
		imageHolder.setOnMouseDragged(t -> onMouseDragged(t));
		imageHolder.setOnMouseClicked(t -> onMouseClicked());
//		cornerCircle.setOnMouseDragged(t -> onMouseDraggedCircle(t));
		
//		imageHolder.getChildren().add(myImage);
		imageHolder.setCenter(myImage);
		imageHolder.setStyle("-fx-border-color: red");
		
//		imageHolder.getChildren().add(cornerCircle);		
		
		
//		System.out.println("SceneBackgroundImage()");
	}
	
	public void translate() {
		imageHolder.setTranslateX(serializable.getxPos());
		imageHolder.setTranslateY(serializable.getyPos());
	}
	
	public void setRectangle(Rectangle rectangle) {
		myRectangle = rectangle;
	}
	
	public Pane getPane() {
//		System.out.println("SceneBackgroundImage getPane()");
		return imageHolder;
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
//			if (Math.hypot(currentX2-(cornerCircle.getCenterX()+imageHolder.getTranslateX()), currentY2-(cornerCircle.getCenterY()+imageHolder.getTranslateY())) < cornerCircle.getRadius()*5) {
//				resizeImage(t);	
//			} else {
				dragImage(t);
			}
			
		imageHolder.setStyle("-fx-border-color: green");
		selected = true;
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
		

//		System.out.println("translateX " + translateX);
//		System.out.println("translateY " + translateY);

		if ((translateX) > (myRectangle.getBoundsInParent().getMaxX()- imageHolder.getBoundsInParent().getWidth())) {
			translateX = myRectangle.getBoundsInParent().getMaxX() - imageHolder.getBoundsInParent().getWidth();
//			System.out.println("A");
		}
		if (translateX < myRectangle.getBoundsInParent().getMinX()) {
			translateX = myRectangle.getBoundsInParent().getMinX();
//			System.out.println("B");
		}
		if ((translateY) > (myRectangle.getBoundsInParent().getMaxY()- imageHolder.getBoundsInParent().getHeight())) {
			translateY = myRectangle.getBoundsInParent().getMaxY() - imageHolder.getBoundsInParent().getHeight();
//			System.out.println("C");
		}
		if (translateY < myRectangle.getBoundsInParent().getMinX()) {
			translateY = myRectangle.getBoundsInParent().getMinY();
//			System.out.println("D");
		}
			
		imageHolder.setTranslateX(translateX);
		imageHolder.setTranslateY(translateY);
		
		serializable.setxPos(translateX);
		serializable.setyPos(translateY);
		
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
	
	public void setOpacity(Double value) {
		myImage.setOpacity(value);
	}
	
	private void updateDimensions() {
		height = myImage.getBoundsInLocal().getHeight();
		width = myImage.getBoundsInLocal().getWidth();
		imageHolder.setMaxWidth(width + BORDER);
		imageHolder.setMaxHeight(height + BORDER);
		
	}
	
	
	private void remove() {
		
		
	}
	
}
