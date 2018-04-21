package engine.authouringconversion;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import authoring.Behavior;
import authoring.GameObject;
import authoring.GameScene;
import authoring.Property;
import engine.GameElement;
import engine.GameState;

public class Converter {
	
	public GameElement gameObject2GameElement (GameObject go) {
		GameElement ge = new GameElement();
		for (Behavior authB: go.getBehaviors()) {
			try {
				Constructor<?> use = getConstrutor(Class.forName(authB.getName()));
				
				engine.behaviors.Behavior newEngineBehavior = (engine.behaviors.Behavior) use.newInstance(ge);
				Arrays.stream(newEngineBehavior.getClass().getDeclaredFields())
				.forEach(field -> {
					field.setAccessible(true);
					authB.getProperties().stream().forEach(prop -> {
						if (prop.getName().equals(field.getName())) {
							try {
								field.set(newEngineBehavior, prop.getValue());
							} catch (IllegalArgumentException | IllegalAccessException e) {
								throw new RuntimeException("Could not match Behavior property name to value");
							}
						}
					});
				});
				ge.addBehavior(newEngineBehavior);
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to convert GameObject " + go + " to game element");
			}
			
		}
		System.out.println(ge.reportProperties());
		return ge;
	}
	
	private Constructor<?> getConstrutor(Class<?> clazz) {
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
		.forEach(gameObj -> state.addGameElement(gameObject2GameElement(gameObj)));
		return state;
	}
	
	public GameObject gameElement2GameObject(GameElement ge) {
		GameObject go = new GameObject();
		for (engine.behaviors.Behavior b: ge.getAllBehaviors()) {
			Behavior authB = new Behavior();
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
	
	public GameScene gameState2GameElement(GameState state) {
		GameScene scene = new GameScene();
	}
}
