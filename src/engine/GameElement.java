package engine;

import java.util.List;
import java.util.ArrayList;

public class GameElement {
	private String elementName;
	private Double xPos;
	private Double yPos;
	
	public GameElement(String name, Double x, Double y) {
		elementName = name;
		xPos = x;
		yPos = y;
	}
	
	public GameElement(String name) {
		this(name, 0.0, 0.0);
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
