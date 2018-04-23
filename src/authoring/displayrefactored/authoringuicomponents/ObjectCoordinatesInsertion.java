package authoring.displayrefactored.authoringuicomponents;

import authoring.GameObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObjectCoordinatesInsertion {

	private String xPos;
	private String yPos;
	private GameObject gameObject;

	public ObjectCoordinatesInsertion(GameObject object, double xPos, double yPos) {
		gameObject = object;
		this.xPos = Double.toString(xPos);
		this.yPos = Double.toString(yPos);
	}

	public String getXpos() {
		return xPos;
	}

	public String getYpos() {
		return yPos;
	}

	public void setXpos(double xPos) {
		this.xPos = Double.toString(xPos);
		gameObject.setX(this.xPos);
	}
	
	public void setYpos(double yPos) {
		this.xPos = Double.toString(yPos);
		gameObject.setY(this.yPos);
	}
	
	

}
