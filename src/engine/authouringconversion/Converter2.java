package engine.authouringconversion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import authoring.AuthBehavior;
import authoring.Event;
import authoring.GameObject;
import authoring.GameScene;
import authoring.Property;
import engine.GameElement;
import engine.GamePart;
import engine.actions.Action;
import engine.behaviors.Behavior;
import engine.behaviors.MandatoryBehavior;
import engine.events.elementevents.ElementEvent;


/** 
 * Converts between authoring environment objects and engine objects
 * including 2 way between
 * GameScene <-> GameState
 * GameObject <-> GameElement
 * AuthBehavior <-> Behavior
 * 
 * @author: Trishul
 **/

public class Converter2 {
	
	/*
	 * converts an authoring environemnt GameObject to an
	 * engine environment GameElement
	 */
	public GameElement gameObject2GameElement(GameObject go) {
		GameElement ge = new GameElement();
		// Must add MandatoryBehavior first
		Behavior engB = authBehavior2Behavior(go.getBehavior(MandatoryBehavior.class.getCanonicalName()), ge);
		ge.addBehavior(engB);
		// Add remaining Behaviors
		for (AuthBehavior authB: go.getBehaviors()) {
			if (authB.getName().contains("Mandatory")) {continue;}
			ge.addBehavior(authBehavior2Behavior(authB, ge));
		}
		return ge;
	}
	
	public GameObject gameElement2GameObject(GameElement ge) {
		GameObject go = new GameObject();
		// Remove the default Authoring MandatoryBehavior
		go.removeBehavior(go.getBehavior(MandatoryBehavior.class.getCanonicalName()));
		// Translate MandatoryBehavior to AuthoringBehavior and add it
		go.addBehavior(behavior2AuthBehavior(ge.getBehavior(MandatoryBehavior.class))); 
		for (Behavior engB: ge.getAllBehaviors()) {
			if (engB.getClass().equals(MandatoryBehavior.class)) { continue;}
			go.addBehavior(behavior2AuthBehavior(engB));
		}
		return go;
	}

	public GamePart gameScene2GamePart(GameScene scene) {
		GamePart part = new GamePart();
		for (GameObject go: scene.getMyObjects()) {
			part.addGameElement(gameObject2GameElement(go));
		}
		printPart(part);
		return part;
	}
	
	public GameScene gamePart2GameScene(GamePart part) {
//		printState(state);
		GameScene scene = new GameScene("Default Name");
		for (GameElement element: part.getElements()) {
			scene.addObject(gameElement2GameObject(element));
		}
		return scene;
	}
	
	public Behavior authBehavior2Behavior (AuthBehavior authB, GameElement ge) {
		Behavior newEngBehavior;
		try {
			Constructor<?> use = getConstructor(Class.forName(authB.getName()));
			newEngBehavior = (Behavior) use.newInstance(ge);
		} catch (ClassNotFoundException|InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
			throw (new RuntimeException("Failed to instantiate newEngBehavior from " + authB.getName()));
		}

		Class<?> newEngBehaviorClass = newEngBehavior.getClass();
		for (Field f: newEngBehaviorClass.getDeclaredFields()) {

			if (Modifier.isPublic(f.getModifiers())) {continue;}
			f.setAccessible(true);
			try {
				f.set(newEngBehavior, authB.getProperty(f.getName()).getValue());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw(new RuntimeException("Failed to set " + authB.getProperty(newEngBehaviorClass.getCanonicalName()).getValue() + " for " + f.getName() + " of " + newEngBehaviorClass));
			}
		}
		return newEngBehavior;
		
	}

	/*
	 * Gets the single GameElement constructor for a BehaviorConstructor
	 */
	private Constructor<?> getConstructor(Class<?> clazz) {
		Constructor<?>[] constructors = clazz.getConstructors();
		return Arrays.stream(constructors)
		.filter(cons -> cons.getParameterCount() == 1)
		.filter(cons -> cons.getParameterTypes()[0].equals(GameElement.class))
		.collect(Collectors.toList())
		.get(0);
	}
	public AuthBehavior behavior2AuthBehavior(Behavior engB) {
		AuthBehavior authB = new AuthBehavior(engB.getClass().getCanonicalName(), new HashSet<>());
		Map<String, Object> engBproperties = engB.reportProperties();
		for (Entry<String,Object> entry: engBproperties.entrySet()) {
			Property newProp = new Property(entry.getKey(), entry.getKey().getClass());
			newProp.setValue(entry.getValue());
			authB.addProperty(newProp);
		}
		return authB;
	}
	
	private void printPart(GamePart part ) {
		System.out.println("GameState: " + part);
		part.getElements().stream().forEach(el -> {printGameElement(el); System.out.println();});
	}
	
	private void printScene (GameScene scene) {
		System.out.println("Printing Scene: " + scene.getName());
		for (GameObject go: scene.getMyObjects()) {
			printGameObject(go);
		}
		System.out.println("Finished printing scene");
	}
	
	private void printEngineBehavior(Behavior b) {
		System.out.println("Behavior: " + b.getClass().getCanonicalName());
		System.out.println(b.reportProperties());
	}
	
	private void printAuthBehavior(AuthBehavior authB) {
		System.out.println("AuthBehavior: " + authB);
		authB.getProperties().stream() 
		.forEach(prop -> System.out.println(prop));
	}
	
	private void printGameElement(GameElement ge) {
		System.out.println("**********************");
		System.out.println("Game Element: " + ge);
		for (Behavior b: ge.getAllBehaviors()) {
			System.out.println();
			printEngineBehavior(b);
		}
		for (Entry<ElementEvent, Action> entry: ge.getResponder().getResponses().entrySet()) {
			System.out.println("Action Map: " + entry.getKey());
		}
	}
	
	
	
	private void printGameObject (GameObject go) {
		System.out.println("--------------------------");
		System.out.println("GameObject toString: " + go);
		System.out.println("Game Object Behaviors: " + go.getBehaviors() + " \n");
		for (AuthBehavior authB: go.getBehaviors()) {
			printAuthBehavior(authB);
			System.out.println();
		}
		
		for (Event ev: go.getEvents()) {
			printEvent(ev);
			System.out.println();
		}
	}
	
	private void printEvent(Event ev) {
		System.out.println(ev);
	}
}
