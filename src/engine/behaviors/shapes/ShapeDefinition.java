package engine.behaviors.shapes;

import engine.behaviors.MandatoryBehavior;
import javafx.scene.shape.Shape;

public abstract class ShapeDefinition {
	
	public abstract Shape getShape(MandatoryBehavior mandatory);
}
