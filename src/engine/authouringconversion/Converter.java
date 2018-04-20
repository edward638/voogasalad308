package engine.authouringconversion;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

import authoring.Behavior;
import authoring.GameObject;
import authoring.Property;
import engine.GameElement;

public class Converter {
	
	public GameElement convert2GameElement (GameObject go) {
		GameElement ge = new GameElement();
		for (Behavior authB: go.getBehaviors()) {
			try {
				Class clazz = Class.forName(go.getName());
				Constructor[] constructors = clazz.getConstructors();
				Constructor use = Arrays.stream(constructors)
				.filter(cons -> cons.getDeclaringClass().equals(Behavior.class)) 
				.collect(Collectors.toList())
				.get(0);
				engine.behaviors.Behavior newEngineBehavior = (engine.behaviors.Behavior) use.newInstance(ge);
				Class behaviorInstanceClass = newEngineBehavior.getClass();
				System.out.println(behaviorInstanceClass);
//				for (Property prop: authB.getProperties()) {
//					
//				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Failed to convert GameObject " + go + " to game element");
			}
			
		}
		return ge;
	}
}
