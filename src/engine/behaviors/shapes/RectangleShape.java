package engine.behaviors.shapes;

import engine.behaviors.MandatoryBehavior;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleShape extends ShapeDefinition{
	private Double width;
	private Double height;
	public RectangleShape(Double w, Double h) {
		width = w;
		height = h;
	}
	@Override
	public Shape getShape(MandatoryBehavior mandatory) {
		return new Rectangle(mandatory.getX(), mandatory.getY(), width, height);
		//return new Ellipse(mandatory.getX(), mandatory.getY(), width/2, height/2);
	}
	
	@Override
	public String toString() {
		return "Rectangle: Width " + width + " , Height: " + height;
	}
	
	@Override
	public double getWidth() {
		return width;
	}
	
	@Override
	public double getHeight() {
		return height;
	}

}
