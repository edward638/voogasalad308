package engine.behaviors;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class MandatoryBehavior extends Behavior{
	
	private Double xPos;
	private Double yPos;
	private String elementName;
	private String imagePath;
	
	private String shapeType;
	private Double displayWidth;
	private Double displayHeight;
	private Double hitBoxWidth;
	private Double hitBoxHeight;
	
	
	public static final String REFER_ALL_ELEMENTS = "ANY_ELEMENT";
	
	/**
	 * NEW way to initialize mandatory behavior w/ shape included
	 * 
	 */
	public MandatoryBehavior(GameElement ge, String name, Double startX, Double startY, String shapeType, Double displayWidth, Double displayHeight, Double hitBoxWidth, Double hitBoxHeight, String imagepath) {
		super(ge);
		xPos = startX;
		yPos = startY;
		elementName = name;
		this.shapeType = shapeType;
		this.displayHeight = displayHeight;
		this.displayWidth = displayWidth;
		this.hitBoxHeight = hitBoxHeight;
		this.hitBoxWidth = hitBoxWidth;
		imagePath = imagepath;
	}
	
	public MandatoryBehavior(GameElement ge, String name, Double startX, Double startY) {
		this(ge, name, startX, startY, "rectangle", 100.0, 100.0, 100.0, 100.0, "data/images/mario_1.jpg");
	}
	
	public MandatoryBehavior(GameElement ge, String name) {
		this(ge, name, 0.0, 0.0, "rectangle", 1.0, 1.0, 1.0, 1.0, "");
	}
	
	public MandatoryBehavior(GameElement ge) {
		this(ge, REFER_ALL_ELEMENTS);
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
	
	public String getName() {
		return elementName;
	}
	
	public void setWidth(Double newWidth) {
		displayWidth = newWidth;
	}
	
	public void setHeight(Double newHeight) {
		displayHeight = newHeight;
	}
	
	public Shape getShape() {
		if (shapeType.equals("rectangle")) {
			return new Rectangle(xPos + (displayWidth - hitBoxWidth)/2, yPos + (displayHeight - hitBoxHeight)/2, hitBoxWidth, hitBoxHeight);
		}
		else {
			return new Ellipse(xPos+displayWidth/2, yPos+displayHeight/2, hitBoxWidth/2, hitBoxHeight/2);
		}
	}

}
