package engine.behaviors;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.shapes.RectangleShape;
import engine.behaviors.shapes.ShapeDefinition;
import javafx.scene.shape.Shape;

public class MandatoryBehavior extends Behavior{
	
	private Double xPos;
	private Double yPos;
	private String elementName;
	private ShapeDefinition shapeDef;
	private String imagePath;
	
	public MandatoryBehavior(GameElement ge, String name, Double startX, Double startY, ShapeDefinition shp, String imagepath) {
		super(ge);
		xPos = startX;
		yPos = startY;
		elementName = name;
		shapeDef = shp;
		imagePath = imagepath;
	}
	
	public MandatoryBehavior(GameElement ge, String name, Double startX, Double startY) {
		this(ge, name, startX, startY, new RectangleShape(100.0, 100.0), "fakeImage");
	}
	
	public void setPosition(double x, double y) {
		xPos = x;
		yPos = y;
	}
	
	public List<Double> getPosition() {
		List<Double> ret = new ArrayList<>();
		ret.add(xPos); ret.add(yPos);
		return ret;
	}
	
	public Double getX() {
		return xPos;
	}
	
	public Double getY() {
		return yPos;
	}
	
	public Shape getShape() {
		return shapeDef.getShape(this);
	}
	public String getName() {
		return elementName;
	}

}
