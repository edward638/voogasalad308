package engine.behaviors;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.shapes.RectangleShape;
import engine.behaviors.shapes.ShapeDefinition;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class MandatoryBehavior extends Behavior{
	
	private Double xPos;
	private Double yPos;
	private Double DEFAULTxPos;
	private Double DEFAULTyPos;
	private String elementName;
	private String imagePath;
	
	// Obsolete, use instance variables shapeType, displayWidth, displayHeight, hitBoxWidth, hitBoxHeight
	private ShapeDefinition shapeDef;
	
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
	
	/**
	 * Obsolete, DO NOT USE in the future
	 * 
	 */
	public MandatoryBehavior(GameElement ge, String name, Double startX, Double startY, ShapeDefinition shp, String imagepath, List<String> incomingTags) {
		super(ge);
		xPos = startX;
		yPos = startY;
		DEFAULTxPos = startX;
		DEFAULTyPos = startY;
		elementName = name;
		shapeDef = shp;
		imagePath = imagepath;
	}
	
	public MandatoryBehavior(GameElement ge, String name, Double startX, Double startY, ShapeDefinition shp, String imagepath) {
		this(ge, name, startX, startY, shp, imagepath, new ArrayList<String>());
	}

	public MandatoryBehavior(GameElement ge, String name, Double startX, Double startY) {
		this(ge, name, startX, startY, new RectangleShape(100.0, 100.0), "data/images/mario_1.jpg");
	}
	
	public MandatoryBehavior(GameElement ge, String name) {
		this(ge, name, 0.0, 0.0, new RectangleShape(1.0, 1.0), "");
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
	
	public void resetPosition() {
		this.setPosition(DEFAULTxPos, DEFAULTyPos);
	}
	
	public Double getX() {
		return xPos;
	}
	
	public Double getY() {
		return yPos;
	}
	
	
	public String getImagePath() {
		return imagePath;
	}
	public String getName() {
		return elementName;
	}
	
	// DO NOT USE THESE METHODS, USE METHODS BELOW
	public void setWidth(Double newWidth) {
		shapeDef = new RectangleShape(newWidth, getShape().getBoundsInLocal().getHeight());
	}
	
	public void setHeight(Double newHeight) {
		shapeDef = new RectangleShape(getShape().getBoundsInLocal().getWidth(), newHeight);
	}
	
	public Shape getShape() {
		return shapeDef.getShape(this);
	}
	
	// NEW METHODS TO USE
	public void newSetWidth(Double newWidth) {
		displayWidth = newWidth;
	}
	
	public void newSetHeight(Double newHeight) {
		displayHeight = newHeight;
	}
	
	public Double getDisplayWidth() {
		return displayWidth;
	}
	
	public Double getDisplayHeight() {
		return displayHeight;
	}
	
	public Shape newGetShape() {
		if (shapeType.equals("rectangle")) {
			return new Rectangle(xPos + (displayWidth - hitBoxWidth)/2, yPos + (displayHeight - hitBoxHeight)/2, hitBoxWidth, hitBoxHeight);
		}
		else {
			return new Ellipse(xPos+displayWidth/2, yPos+displayHeight/2, hitBoxWidth/2, hitBoxHeight/2);
		}
	}

}
