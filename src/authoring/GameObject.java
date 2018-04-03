package authoring;

import java.util.Set;

import javafx.scene.image.Image;

public class GameObject {
	
	private double myX; 
	private double myY;
	private String myName;
	private Image myImage;
	private double myImageHeight;
	private double myImageWidth;
	private Set<Behavior> behaviors;
	
	public GameObject() {
		
	}
	
	//each game object will have properties that describe how it behaves. 
	public void addBehavior(Behavior behaviorToAdd) {
		behaviors.add(behaviorToAdd);
	}
	
	//the user can remove a property after assigning it
	public void removeBehavior(Behavior behaviorToRemove) {
		behaviors.remove(behaviorToRemove);
	}
	
	//each object is known by its name
	public String getName() {
		return myName;
	}
	
	//method sets or resets the name of the object
	public void setName(String newName) {
		myName = newName;
	}
	
	//returns the list of all properties associated with the object
	public Set<Behavior> getBehaviors() {
		return behaviors;
	}
	
	//sets the image that represents the object
	public void setImage(Image newImage) {
		myImage = newImage;
	}
	
	//returns the objects image
	public Image getImage() {
		return myImage;
	}
	
	//sets the size of the image
	public void setImageSize(double x, double y) {
		myImageHeight = x;
		myImageWidth = y;
	}
	
	//returns the width of the image representing the object
	public double getImageWidth() {
		return myImageWidth;
	}
	
	//returns the height of the image representing the object
	public double getImageHeight() {
		return myImageHeight;
	}
	
	public void setX(double x) {
		myX = x;
	}
	
	public double getX() {
		return myX;
	}
	
	public void setY(double y) {
		myY = y;
	}
	
	public double getY() {
		return myY;
	}
	
	public void setXY(double x, double y) {
		myX = x;
		myY = y;
	}
}
