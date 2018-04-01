package engine;

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
	
	public String getName() {
		return elementName;
	}
}
