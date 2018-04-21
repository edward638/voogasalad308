package engine.authouringconversion;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

import authoring.Behavior;
import authoring.GameObject;
import authoring.GameScene;
import authoring.Property;
import engine.GameElement;
import engine.GameState;
import engine.behaviors.MandatoryBehavior;

public class Converter {
	
	public GameElement gameObject2GameElement (GameObject go) {
		GameElement ge = new GameElement();
		ge.addBehavior(getEngineBehavior(go.getBehavior(MandatoryBehavior.class.getName()), ge));
		for (Behavior authB: go.getBehaviors()) {
			try {
				Constructor<?> use = getConstructor(Class.forName(authB.getName()));
				engine.behaviors.Behavior newEngineBehavior = (engine.behaviors.Behavior) use.newInstance(ge);
				setUpEngineBehavior(newEngineBehavior, authB);
				ge.addBehavior(newEngineBehavior);
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to convert GameObject " + go + " to game element");
			}
			
		}
		return ge;
	}
	
	private engine.behaviors.Behavior getEngineBehavior(Behavior authB, GameElement ge) {
		engine.behaviors.Behavior engB = null;
		
		Constructor<?> use = getConstructor(getClassName(authB));
		try {
			engB = (engine.behaviors.Behavior) use.newInstance(ge);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException("Could not construct Engine Behavior from " + authB.getName());
		}
		setUpEngineBehavior(engB, authB);
		return engB;
	}
	
	private Class<?> getClassName(Behavior authB) {
		Class<?> clazz;
		try {
			clazz = Class.forName(authB.getName());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not convert " + authB.getName() + " to CLass<?>");
		}
		return clazz;
	}
	private void setUpEngineBehavior(engine.behaviors.Behavior engB, Behavior authB) {
		Arrays.stream(engB.getClass().getDeclaredFields())
		.forEach(field -> {
			System.out.println(field.getName());
			if (!Modifier.isPublic(field.getModifiers())) { 
				field.setAccessible(true);
				authB.getProperties().stream().forEach(prop -> {
					if (prop.getName().equals(field.getName())) {
						try {
							field.set(engB, prop.getValue());
						} catch (IllegalArgumentException | IllegalAccessException e) {
							throw new RuntimeException("Could not match Behavior " + field.getName() + " property name to value");
						}
					}
				});
			}
		});
	}
	
	private Constructor<?> getConstructor(Class<?> clazz) {
		Constructor<?>[] constructors = clazz.getConstructors();
		return Arrays.stream(constructors)
		.filter(cons -> cons.getParameterCount() == 1)
		.filter(cons -> cons.getParameterTypes()[0].equals(GameElement.class))
		.collect(Collectors.toList())
		.get(0);
	}
	
	public GameState gameScene2GameState (GameScene gs) {
		GameState state = new GameState();
		gs.getMyObjects().stream()
		.forEach(gameObj -> {
			System.out.println(gameObj.getName());
			state.addGameElement(gameObject2GameElement(gameObj));
		});
		return state;
	}
	
	public GameObject gameElement2GameObject(GameElement ge) {
		GameObject go = new GameObject(ge.getIdentifier());
		for (engine.behaviors.Behavior b: ge.getAllBehaviors()) {
			System.out.println(b.reportProperties());
			Behavior authB = new Behavior(b.getClass().getName(), new HashSet<Property>());
			Map<String, Object> engBehaviorProperties = b.reportProperties();
			for (String key: b.reportProperties().keySet()) {
				Property prop = new Property(key, engBehaviorProperties.getClass());
				prop.setValue(engBehaviorProperties.get(key));
				authB.addProperty(prop);
			}
			go.addBehavior(authB);
		}
		return go;
	}
	
	public GameScene gameState2GameScene(GameState state) {
		GameScene scene = new GameScene("DefaultName");
		for (GameElement ge: state.getElements()) {
			scene.addObject(gameElement2GameObject(ge));
		}
		return scene;
	}
}
