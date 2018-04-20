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
				Class clazz = Class.forName(authB.getName());
				System.out.println(clazz);
				Constructor[] constructors = clazz.getConstructors();
				Constructor use = Arrays.stream(constructors)
				.filter(cons -> cons.getParameterCount() == 1)
				.filter(cons -> cons.getParameterTypes()[0].equals(GameElement.class))
				.collect(Collectors.toList())
				.get(0);
				
				engine.behaviors.Behavior newEngineBehavior = (engine.behaviors.Behavior) use.newInstance(ge);
				Class behaviorInstanceClass = newEngineBehavior.getClass();
				
//				Arrays.stream(behaviorInstanceClass.getFields()) 
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Failed to convert GameObject " + go + " to game element");
			}
			
		}
		return ge;
	}
}
