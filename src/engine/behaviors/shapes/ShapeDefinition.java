package engine.behaviors.shapes;

import engine.behaviors.MandatoryBehavior;
import javafx.scene.shape.Shape;

public abstract class ShapeDefinition {
	protected Double width;
	protected Double height;
	
	public abstract Shape getShape(MandatoryBehavior mandatory);
	public abstract String toString();
	public abstract double getWidth();
	public abstract double getHeight();
}
