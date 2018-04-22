package engine.tests;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.GameScene;
import authoring.Property;
import engine.GameElement;
import engine.GameState;
import engine.authouringconversion.Converter;
import engine.behaviors.Movable;

class ConverterTester {
	GameState testState;
	Converter converter;
	
	@BeforeEach
	void setup() {
		testState = new ModelGameState2().getState();
		converter = new Converter();
	}
	
	@Test
	void testMakeGameObject() {
		AuthBehavior behave = new AuthBehavior(Movable.class.getCanonicalName(), new HashSet<Property>());
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
		AuthBehavior behave = new AuthBehavior(Movable.class.getCanonicalName(), new HashSet<Property>());
		Property propXvel = new Property("xVel", Double.class);
		propXvel.setValue(10.0);
		Property propYvel = new Property("yVel", Double.class);
		propYvel.setValue(1.0);
		behave.addProperty(propXvel);
		behave.addProperty(propYvel);
		GameObject go = new GameObject(behave);
		go.addBehavior(behave);
		Converter converter = new Converter();
	}
	
	void printTestState() {
		testState.getElements().forEach(el -> System.out.println(el.reportProperties()));
	}
	
	@Test
	void convertStateToScene() {
		GameScene scene = converter.gameState2GameScene(testState);
		for (GameObject go: scene.getMyObjects()) {
//			System.out.println(go.getName());
		}
		GameState g2 = converter.gameScene2GameState(scene);
		System.out.println(testStateEquality(testState, g2));
		System.out.println("Converted State to Scene");
	}
	
	boolean testStateEquality(GameState s1, GameState s2) {
		List <GameElement> s1elems = s1.getElements();
		List <GameElement> s2elems = s2.getElements();
		if (s1elems.size() != s2elems.size()) {return false;}
		
		for (GameElement s1elem: s1elems) {
			if (!s2elems.contains(s1elem)) {
				return false;
			}
		}
		return true;

	}

}
