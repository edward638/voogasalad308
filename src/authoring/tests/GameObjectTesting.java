package authoring.tests;

import java.util.HashSet;

import authoring.Behavior;
import authoring.GameElement;
import authoring.Property;

public class GameObjectTesting {

	public GameObjectTesting() {
		
	}
	
	public static void main(String[] args) {
		Behavior b = new Behavior("Behavior!", new HashSet<Property>());
		Property prop0 = new Property("awesomeness", new Double(1000000));
		Property prop1 = new Property("coolness", new Double(10));
		GameElement go = new GameElement(b);
		System.out.println(go.getBehaviors());
		go.getBehavior("Behavior!").addProperty(prop0);
		go.getBehavior("Behavior!").addProperty(prop1);
		System.out.println(go.getBehaviors());
	}

}
