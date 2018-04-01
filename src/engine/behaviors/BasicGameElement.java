package engine.behaviors;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;

public class BasicGameElement extends Behavior{
	
	private Double xPos;
	private Double yPos;
	private String elementName;
	
	public BasicGameElement(GameElement ge, String name, Double startX, Double startY) {
		super(ge);
		xPos = startX;
		yPos = startY;
		elementName = name;
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

}
