package engine.authouringconversion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import authoring.AuthBehavior;
import authoring.Event;
import authoring.GameObject;
import authoring.GameScene;
import authoring.Property;
import engine.EventResponder;
import engine.GameElement;
import engine.GamePart;
import engine.actions.Action;
import engine.actions.GroovyAction;
import engine.behaviors.Behavior;
import engine.behaviors.MainCharacter;
import engine.behaviors.MandatoryBehavior;
import engine.events.elementevents.ElementEvent;


/** 
 * Converts between authoring environment objects and engine objects
 * including 2 way between
 * GameScene <-> GamePart
 * GameObject <-> GameElement
 * AuthBehavior <-> Behavior
 * 
 * @author: Trishul
 **/

public class Converter2 {
	
	/*
	 * converts an authoring environment GameObject to an
	 * engine environment GameElement
	 */
	Printer printer = new Printer();
	
	public GameElement gameObject2GameElement(GameObject go) {
		GameElement ge = new GameElement();
		// Must add MandatoryBehavior first
		Behavior mandEngB = authBehavior2Behavior(go.getBehavior(MandatoryBehavior.class.getCanonicalName()), ge);
		ge.addBehavior(mandEngB);
		// Add remaining Behaviors besides MainCharacter
		
		for (AuthBehavior authB: go.getBehaviors()) {
			if (authB.getName().contains("Mandatory") || authB.getName().contains("MainCharacter")) {continue;}
			ge.addBehavior(authBehavior2Behavior(authB, ge));
		}
		
		// Add MainCharacter Behavior
		Integer size = go.getBehaviors()
				.stream()
				.filter(authB -> authB.getName().contains("MainCharacter"))
				.collect(Collectors.toList())
				.size();
		if (size > 0) {
			ge.addBehavior(authBehavior2Behavior(go.getBehavior(MainCharacter.class.getCanonicalName()), ge));
		}
			
		addResponsesAuth2Engine(ge, go);	
		return ge;
	}
	
	
	public GameObject gameElement2GameObject(GameElement ge) {
		GameObject go = new GameObject();
		// Remove the default Authoring MandatoryBehavior
		go.removeBehavior(go.getBehavior(MandatoryBehavior.class.getCanonicalName()));
		// Translate MandatoryBehavior to AuthoringBehavior and add it
		go.setName(ge.getIdentifier());
		go.addBehavior(behavior2AuthBehavior(ge.getBehavior(MandatoryBehavior.class))); 
		for (Behavior engB: ge.getAllBehaviors()) {
			if (engB.getClass().equals(MandatoryBehavior.class)) { continue;}
			go.addBehavior(behavior2AuthBehavior(engB));
		}
		
		addResponsesEngine2Auth(ge, go);
		return go;
	}
	

	public GamePart gameScene2GamePart(GameScene scene) {
		GamePart part = new GamePart(scene.getName(), "0");
		for (GameObject go: scene.getMyObjects()) {
			part.addGameElement(gameObject2GameElement(go));
		}
		return part;
	}
	
	/*
	 * Method reviews game objects stored as parts
	 */
//	private GamePart fillGameObjects(GamePart part, GameScene scene) {
//		List<Property> properties2fix = new ArrayList<>();
//		for (GameObject go: scene.getMyObjects()) {
//			go.getBehaviors().stream().forEach(beh -> {
//				beh.getProperties().stream().forEach(prop -> {
//					if (prop.getValue() instanceof GameElement) {
//						properties2fix.add(prop);
//					}
//				});
//			});
//		}
//		properties2fix.stream().forEach(prop -> fixProperty(prop, part));
//	}
	
	public GameScene gamePart2GameScene(GamePart part) {
		GameScene scene = new GameScene(part.getGamePartID(), part.getMyLevelID());
		for (GameElement element: part.getElements()) {
			scene.addObject(gameElement2GameObject(element));
		}
		return scene;
	}
	
	/*
	 * Converts authoring behavior into Engine Behavior through reflection
	 */
	public Behavior authBehavior2Behavior (AuthBehavior authB, GameElement ge) {
		Behavior newEngBehavior;
		try {
			//System.out.println(authB.getName());
			Constructor<?> use = getConstructor(Class.forName(authB.getName()));
			System.out.println("COnstructor made");
			System.out.println(use);
			newEngBehavior = (Behavior) use.newInstance(ge);
			
			System.out.println("Behaior instantiatted");
		} catch (ClassNotFoundException|InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
			throw (new RuntimeException("Failed to instantiate newEngBehavior from " + authB.getName()));
		}
		Class<?> newEngBehaviorClass = newEngBehavior.getClass();
		for (Field f: newEngBehaviorClass.getDeclaredFields()) {
			if (Modifier.isPublic(f.getModifiers())) {continue;}
			f.setAccessible(true);
			//System.out.println("Field: " + f);
			try {
				f.set(newEngBehavior, authB.getProperty(f.getName()).getValue());
			} catch (IllegalArgumentException | IllegalAccessException e) {
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
	
	/*
	 * Method that converts a GameElement Behavior into an Authoring Behavior
	 */
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
	

	public void addResponsesEngine2Auth(GameElement ge, GameObject go) {
		Map<ElementEvent, Action> responses = new HashMap<>();
		responses.putAll(ge.getResponder().getResponses());
		for (Entry<ElementEvent, Action> response: responses.entrySet()) {
			if (!(response.getValue() instanceof GroovyAction)) {
				continue;
			}
			GroovyAction groovyAction = (GroovyAction) response.getValue();
			Event authEvent = new Event();
			authEvent.setEventType(response.getKey().getClass().getCanonicalName());
			authEvent.setTrigger(response.getKey().getTriggerString());
			authEvent.addResponse(groovyAction);
			go.addEvent(authEvent);
		}
	}

	
	/*
	 * Convert an authoring EventResponse object, defining how an object responds 
	 * to events to Engine Action events that can be executed on an element
	 */
	public void addResponsesAuth2Engine(GameElement ge, GameObject go) {
		EventResponder responder = ge.getResponder();
		for (Event event: go.getEvents()) {
			ElementEvent ee = authEvent2ElementEvent(ge, event);
			System.out.println("event.getResponses class: " + event.getResponses().size());
			for (GroovyAction response: event.getResponses()) {
				responder.addResponse(ee, response);
			}
		}
	}
	
	/*
	 * Private helper method to get the ElementEvent type from an Event
	 */
	private ElementEvent authEvent2ElementEvent(GameElement ge, Event authE) {
		EventConverter eventConverter = new EventConverter();
		ElementEvent retEvent = eventConverter.authEvent2ElementEvent(ge, authE);
		return retEvent;
	}
	
//	public Action eventResponse2Action(EventResponse response) {
//		GroovyAction groovyAction = new GroovyAction(response.getMyContent());
//		return groovyAction;
//	}
	
}
