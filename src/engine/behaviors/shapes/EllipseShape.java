package engine.behaviors.shapes;

import engine.behaviors.MandatoryBehavior;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class EllipseShape extends ShapeDefinition {
	private Double hitBoxWidth;
	private Double hitBoxHeight;
	public EllipseShape(Double width, Double height) {
		this.width = width;
		this.height = height;
		hitBoxWidth = width;
		hitBoxHeight = height;
	}
	
	public EllipseShape(Double width, Double height, Double hitBoxWidth, Double hitBoxHeight) {
		this.width = width;
		this.height = height;
		this.hitBoxWidth = hitBoxWidth;
		this.hitBoxHeight = hitBoxHeight;
	}
	
	@Override
	public Shape getShape(MandatoryBehavior mandatory) {
		//return new Rectangle(mandatory.getX(), mandatory.getY(), width, height);
		return new Ellipse(mandatory.getX()+width/2, mandatory.getY()+height/2, hitBoxWidth/2, hitBoxHeight/2);
	}
	
	@Override
	public String toString() {
		return "Ellipse: Width " + width + " , Height: " + height;
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
