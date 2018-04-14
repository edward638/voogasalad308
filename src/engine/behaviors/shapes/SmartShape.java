package engine.behaviors.shapes;

import engine.behaviors.MandatoryBehavior;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SmartShape extends ShapeDefinition {
	private ImageView iv;

	public SmartShape(ImageView iv) {
		this.iv = iv;
	}
	@Override
	public Shape getShape(MandatoryBehavior mandatory) {
		mandatory.getImagePath();
		return new Rectangle(mandatory.getX(), mandatory.getY(), width, height);
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
