package engine.behaviors.shapes;

import data.ImageManager;
import engine.behaviors.MandatoryBehavior;
import engine.collision.SubtractiveRectangleExtrapolator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SmartShape extends ShapeDefinition {
	private Double width;
	private Double height;
	private SubtractiveRectangleExtrapolator ex;
	private ImageManager im;
	private Image image;

	public SmartShape(double width, double height) {
		this.width = width;
		this.height = height;
		ex = new SubtractiveRectangleExtrapolator();
		im = new ImageManager("enginetestmario");
		
	}
	@Override
	public Shape getShape(MandatoryBehavior mandatory) {
		if (image == null)
			image = im.getImage(mandatory.getImagePath());
		return ex.extrapolateShape(image, mandatory.getX(), mandatory.getY(), width, height);
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
