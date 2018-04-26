package engine.authouringconversion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import authoring.AuthBehavior;
import authoring.Event;
import authoring.EventResponse;
import authoring.GameObject;
import authoring.GameScene;
import authoring.Property;
import engine.EventResponder;
import engine.GameElement;
import engine.GamePart;
import engine.actions.Action;
import engine.actions.GroovyAction;
import engine.behaviors.Behavior;
import engine.behaviors.MandatoryBehavior;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.TimeEvent;


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
	Printer printer = new Printer();
	
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
		
		Reflections reflections = new Reflections(ElementEvent.class.getPackage().getName());
		Set<Class<? extends ElementEvent>> elementEventSubTypes = reflections.getSubTypesOf(ElementEvent.class);
		for (Class<? extends ElementEvent> clazz: elementEventSubTypes) {
			go.addEvent(getListOfSameEventResponses(clazz, ge));
		}
		return go;
	}
	
	private Event getListOfSameEventResponses(Class<?> eventClass, GameElement ge) {
		Map<ElementEvent, Action> responses = ge.getResponder().getResponses();
		List<Event> authEvents = new ArrayList<>();
		List<Entry<ElementEvent, Action>> relevantResponses = responses.entrySet().stream()
				.filter(entry -> entry.getKey().getClass() == eventClass)
				.filter(entry -> entry.getValue() instanceof GroovyAction)
				.collect(Collectors.toList());
		
		Event authEvent = new Event();
		authEvent.setEventType(eventClass.getCanonicalName());
		relevantResponses.stream()
			.forEach(entry -> {
				EventResponse toAdd = new EventResponse();
				toAdd.setMyContent(((GroovyAction)(entry.getValue())).getContent());
				authEvent.addResponse(toAdd);
			});
		return authEvent;
		
	}

	public GamePart gameScene2GamePart(GameScene scene) {
		GamePart part = new GamePart(scene.getName(), "0");
		for (GameObject go: scene.getMyObjects()) {
			part.addGameElement(gameObject2GameElement(go));
		}
		printer.printState(part);
		return part;
	}
	
	/*
	 * Convert from game state to game scene
	 */
	
	public GameScene gamePart2GameScene(GamePart part) {
//		printState(state);
		GameScene scene = new GameScene("Default Name");
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
			System.out.println("f: " + f);
			try {
				System.out.println("authB.getProperty(f.getName()).getValue(): " + authB.getProperty(f.getName()));
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
		Map<ElementEvent, Action> responses = ge.getResponder().getResponses();
		for (Entry<ElementEvent, Action> response: responses.entrySet()) {
			if (!(response.getValue() instanceof GroovyAction)) {
				continue;
			}
			GroovyAction groovyAction = (GroovyAction) response.getValue();
			Event authEvent = new Event();
			authEvent.setEventType(response.getKey().getClass().getCanonicalName());
			EventResponse authResp = new EventResponse();
			authResp.setMyContent(groovyAction.getContent());
			authEvent.addResponse(authResp);
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
			for (EventResponse response: event.getResponses()) {
				Action action = eventResponse2Action(response);
				responder.addResponse(ee, action);
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
	
	public Action eventResponse2Action(EventResponse response) {
		GroovyAction groovyAction = new GroovyAction(response.getMyContent());
		return groovyAction;
	}
	
}
