package engine.behaviors.shapes;

import engine.behaviors.MandatoryBehavior;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleShape extends ShapeDefinition{
	
	private Double hitBoxWidth;
	private Double hitBoxHeight;
	public RectangleShape(Double width, Double height) {
		this.width = width;
		this.height = height;
		hitBoxWidth = width;
		hitBoxHeight = height;
	}
	
	public RectangleShape(Double width, Double height, Double hitBoxWidth, Double hitBoxHeight) {
		this.width = width;
		this.height = height;
		this.hitBoxWidth = hitBoxWidth;
		this.hitBoxHeight = hitBoxHeight;
	}
	
	
	@Override
	public Shape getShape(MandatoryBehavior mandatory) {
		return new Rectangle(mandatory.getX() + (width - hitBoxWidth)/2, mandatory.getY() + (height - hitBoxHeight)/2, hitBoxWidth, hitBoxHeight);
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
