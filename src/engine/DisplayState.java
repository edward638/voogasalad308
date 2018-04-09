package engine;

import java.util.List;

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
	
	public void removeElement(GameElement element) {
		activeElements.stream().filter(c -> c.getReference() == element).map(c -> activeElements.remove(c));
		activeElements.stream().filter(c -> c.getReference() == element).map(c -> removeElements.add(c));
	}

	public void updateImageElements() {
		for (ImageElement imageElement : activeElements) {
			imageElement.updateState();
		}
	}
	
	/*
	
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
	}*/
	
	public static void main(String[] args) {
		Rectangle r1 = new Rectangle();
		Rectangle r2 = new Rectangle();
		r1.setX(4);
		r1.setY(4);
		r1.setWidth(4);
		r1.setHeight(4);
		r2.setX(10);
		r2.setY(10);
		r2.setWidth(4);
		r2.setHeight(5);
		Shape intersect = Shape.intersect(r1, r2);
		//System.out.println(intersect.getBoundsInLocal());
		//int side = getCollisionSide(getCenter(r1), getCenter(intersect));
		//System.out.println(side);
		
	}
	
}
