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
	//private ImageView iv;

	public SmartShape(double width, double height) {
		this.width = width;
		this.height = height;
		ex = new SubtractiveRectangleExtrapolator();
	}
	@Override
	public Shape getShape(MandatoryBehavior mandatory) {
		//System.out.println(mandatory.getImagePath());
		ImageManager im = new ImageManager("enginetestmario");
		Image i = im.getImage(mandatory.getImagePath());
		//Image i = new Image("data\\gamedata\\games\\enginetestmario\\images\\MarioSMR.png");
		ImageView iv = new ImageView();
		iv.setImage(i);
		iv.setX(mandatory.getX());
		iv.setY(mandatory.getY());
		return ex.extrapolateShape(iv);
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
