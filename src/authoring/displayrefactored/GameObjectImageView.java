package authoring.displayrefactored;

import com.sun.org.apache.bcel.internal.generic.GOTO;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.ObjectInfoObservable;
import authoring.ViewRefreshInterface;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GameObjectImageView {

	private Rectangle myRectangle;
	private ImageView myImage;
	private GameObject gameObject;
	private double currentX;
	private double currentY;
	private double positionX;
	private double positionY;
	private boolean wasEdited = false;
	private boolean selected = false;
	double translateX;
	double translateY;
	
	private ViewRefreshInterface viewRefreshInterface;
	
	public GameObjectImageView(ImageView imageView, GameObject go, ViewRefreshInterface viewRefreshInterface) {
		
		myRectangle = new Rectangle(ResourceBundleManager.getPosition("GAMEVIEWSIZE_X"), ResourceBundleManager.getPosition("GAMEVIEWSIZE_Y"));
		myImage = imageView;
		gameObject = go;
		myImage.setOnMousePressed(t -> onMousePressed(t));
		myImage.setOnMouseDragged(t -> onMouseDragged(t));
		myImage.setOnMouseClicked(t -> onMouseClicked());
		myImage.setOnMouseReleased(t -> onMouseReleased());
		this.viewRefreshInterface = viewRefreshInterface;
		
	}
	
	private void onMouseReleased() {
		
		AuthBehavior mandatory = gameObject.getMandatoryBehavior();
		mandatory.getProperty("xPos").setValue(translateX);
		mandatory.getProperty("yPos").setValue(translateY);
		
		
		viewRefreshInterface.notifyObjectInfoObservers(gameObject);
		
	}

	private void onMouseClicked() {
		selected = !selected;
		
		if (selected) {
//			imageHolder.setStyle("-fx-border-color: green");
		} else if (! wasEdited){
//			imageHolder.setStyle("-fx-border-color: red");
		} else {
			selected = true;
		}
		wasEdited = false;
		
	}
	
	private void onMousePressed(MouseEvent t) {
		
		currentX = t.getSceneX();
		currentY = t.getSceneY();

		positionX = ((ImageView)(t.getSource())).getTranslateX();
		positionY = ((ImageView)(t.getSource())).getTranslateY();
		
//		System.out.println("This is the mouse scene location: " + currentX + ", " + currentY);
//		System.out.println("This is the mouse position location: " + positionX + ", " + positionY);
//		System.out.println("This is the pane location: " + imageHolder.getTranslateX() + ", " + imageHolder.getTranslateY());
//		System.out.println("This is the Circle location: " + cornerCircle.getCenterX() + ", " + cornerCircle.getCenterY());
	}
	
	private void onMouseDragged(MouseEvent t) {
		
		double currentX2 = t.getSceneX();
		double currentY2 = t.getSceneY();
		
		if (selected) {
				dragImage(t);
			}
	
			selected = true;
	}
	
	private void dragImage(MouseEvent t) {
		
		double newX = t.getSceneX();
		double newY = t.getSceneY();	
		translateX = newX - currentX + positionX;
		translateY = newY - currentY + positionY;
		

//		System.out.println("translateX " + translateX);
//		System.out.println("translateY " + translateY);

		if ((translateX) > (myRectangle.getBoundsInParent().getMaxX()- myImage.getBoundsInParent().getWidth())) {
			translateX = myRectangle.getBoundsInParent().getMaxX() - myImage.getBoundsInParent().getWidth();
//			System.out.println("A");
		}
		if (translateX < myRectangle.getBoundsInParent().getMinX()) {
			translateX = myRectangle.getBoundsInParent().getMinX();
//			System.out.println("B");
		}
		if ((translateY) > (myRectangle.getBoundsInParent().getMaxY()- myImage.getBoundsInParent().getHeight())) {
			translateY = myRectangle.getBoundsInParent().getMaxY() - myImage.getBoundsInParent().getHeight();
//			System.out.println("C");
		}
		if (translateY < myRectangle.getBoundsInParent().getMinX()) {
			translateY = myRectangle.getBoundsInParent().getMinY();
//			System.out.println("D");
		}
			
		myImage.setTranslateX(translateX);
		myImage.setTranslateY(translateY);
		
		
		wasEdited = true;
		
	}

	public ImageView getMyImage() {
		return myImage;
	}


}
