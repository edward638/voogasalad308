package engine.tests;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import authoring.Behavior;
import authoring.GameObject;
import authoring.Property;
import engine.GameElement;
import engine.authouringconversion.Converter;
import engine.behaviors.Movable;

class ConverterTester {

	@Test
	void testMakeGameObject() {
		Behavior behave = new Behavior(Movable.class.getCanonicalName(), new HashSet<Property>());
		Property propXvel = new Property("xVel", Double.class);
		propXvel.setValue(10.0);
		Property propYvel = new Property("yVel", Double.class);
		propYvel.setValue(1.0);
		behave.addProperty(propXvel);
		behave.addProperty(propYvel);
		GameObject go = new GameObject(behave);
		go.addBehavior(behave);
//		System.out.println(go);
	}
	
	@Test
	void convertGameObject() {
		Behavior behave = new Behavior(Movable.class.getCanonicalName(), new HashSet<Property>());
		Property propXvel = new Property("xVel", Double.class);
		propXvel.setValue(10.0);
		Property propYvel = new Property("yVel", Double.class);
		propYvel.setValue(1.0);
		behave.addProperty(propXvel);
		behave.addProperty(propYvel);
		GameObject go = new GameObject(behave);
		go.addBehavior(behave);
		Converter converter = new Converter();
		GameElement ge = converter.convert2GameElement(go);
		System.out.println(ge.reportProperties());
	}

}
