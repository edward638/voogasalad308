package engine.tests;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import authoring.Behavior;
import authoring.GameObject;
import authoring.Property;

class ConverterTester {

	@Test
	void test() {
		Behavior behave = new Behavior("Moveable", new HashSet<Property>());
		Property propXvel = new Property("xVel", Double.class);
		propXvel.setValue(10.0);
		Property propXvel = new Property("yVel", Double.class);
		propXvel.setValue(10.0);
		Property propXvel = new Property("yVel", Double.class);
		propXvel.setValue(10.0);
		behave.addProperty(prop);
		GameObject go = new GameObject(behave);
	}

}
