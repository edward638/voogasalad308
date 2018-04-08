package engine;

import java.util.LinkedList;
import java.util.List;

import engine.events.ElementEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DisplayState {
	public List<ImageElement> activeElements;
	public List<ImageElement> newElements;
	public List<ImageElement> removeElements;
	
	public void addNewElement(GameElement element) {
		ImageElement imageElement = new ImageElement(element);
		newElements.add(imageElement);
		activeElements.add(imageElement);
	}
	
	public void removeElements()

	public void updateImageElements() {
		for (ImageElement imageElement : activeElements) {
			imageElement.updateState();
		}
	}
	
	public List<ElementEvent> detectCollisions() {
		List<ElementEvent> collisionEvents = new LinkedList<CollisionEvent>();
		
		for (int i = 0; i < activeElements.size(); i++) {
			for (int j = i+1; j < activeElements.size(); j++) {
				ImageElement element1 = activeElements.get(i);
				ImageElement element2 = activeElements.get(j);
				Shape intersect = Shape.intersect(element1.toShape(), element2.toShape());
				Point2D intersectCenter = getCenter(intersect);
				
				/*if (element1.getBoundsInLocal().intersects(element2.getBoundsInLocal())) {
					ElementEvent collision = new CollisionEvent(e1, e2);
					collisionEvents.add(collision);
				}*/
					
			}
		}
		return collisionEvents;
	}
	
	private static Point2D getCenter(Shape s) {
		Bounds b = s.getBoundsInLocal();
		double x = (b.getMinX() + b.getMaxX())/2;
		double y = (b.getMinY() + b.getMaxY())/2;
		System.out.println(x + " " + y);
		return new Point2D(x, y);
	}
	
	private static int getCollisionSide(Point2D element, Point2D collision) {
		double angle = element.angle(collision);
		System.out.println(angle);
		int side = (int) (angle % 90 % 4);
		return side;
	}
	
	public static void main(String[] args) {
		Rectangle r1 = new Rectangle();
		Rectangle r2 = new Rectangle();
		r1.setX(4);
		r1.setY(4);
		r1.setWidth(4);
		r1.setHeight(4);
		r2.setX(5);
		r2.setY(7);
		r2.setWidth(4);
		r2.setHeight(5);
		Shape intersect = Shape.intersect(r1, r2);
		int side = getCollisionSide(getCenter(r1), getCenter(intersect));
		System.out.println(side);
		
	}
	
}
