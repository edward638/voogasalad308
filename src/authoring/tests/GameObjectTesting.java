package authoring.tests;

import java.util.HashSet;

import authoring.GameObject;
import authoring.Behavior;
import authoring.Property;

public class GameObjectTesting {

	public GameObjectTesting() {
		
	}
	
	public static void main(String[] args) {
		GameObject go = new GameObject();
		System.out.println(go.getBehaviors());
		Behavior b = new Behavior("Behavior!", new HashSet<Property>());
		Property prop0 = new Property("awesomeness",  Double.class);
		Property prop1 = new Property("coolness", Double.class);
		go.addBehavior(b);
		go.getBehavior("Behavior!").addProperty(prop0);
		go.getBehavior("Behavior!").addProperty(prop1);
		System.out.println(go.getBehaviors());
	}

}
