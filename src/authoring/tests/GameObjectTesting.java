package authoring.tests;

import java.util.HashSet;

import authoring.Behavior;
import authoring.GameObject;
import authoring.Property;

public class GameObjectTesting {

	public GameObjectTesting() {
		
	}
	
	public static void main(String[] args) {
		Behavior b = new Behavior("Behavior!", new HashSet<Property>());
<<<<<<< HEAD
		GameObject go = new GameObject(b);
		System.out.println(go.getBehaviors());
		Property prop0 = new Property("awesomeness",  Double.class);
		Property prop1 = new Property("coolness", Double.class);
		go.addBehavior(b);
		go.getBehavior("Behavior!").addProperty(prop0);
		go.getBehavior("Behavior!").addProperty(prop1);
=======
//		Property prop0 = new Property("awesomeness", new Double(1000000));
//		Property prop1 = new Property("coolness", new Double(10));
		GameObject go = new GameObject(b);
		System.out.println(go.getBehaviors());
//		go.getBehavior("Behavior!").addProperty(prop0);
//		go.getBehavior("Behavior!").addProperty(prop1);
>>>>>>> f12c54599b039135274a26071bd11788fadbf878
		System.out.println(go.getBehaviors());
	}

}
