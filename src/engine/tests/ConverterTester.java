package engine.tests;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.GameScene;
import engine.GameElement;
import engine.GameState;
import engine.authouringconversion.Converter2;
import engine.behaviors.Behavior;
import engine.behaviors.MandatoryBehavior;

class ConverterTester {
	GameState testState;
	Converter2 converter;
	ModelGameState2 stateMaker;
	
	@BeforeEach
	void setup() {
		stateMaker = new ModelGameState2();
		testState = stateMaker.getState();
		converter = new Converter2();
	}
	
//	@Test
	void testBehaviorEng2Auth () {
		Behavior mand = stateMaker.getMario().getBehavior(MandatoryBehavior.class);
		printAuthBehavior(converter.behavior2AuthBehavior(mand));
	}
	
	void printAuthBehavior(AuthBehavior authB) {
		System.out.println("AuthBehavior: " + authB);
		authB.getProperties().stream() 
		.forEach(prop -> System.out.println(prop));
	}
	
//	@Test
	void testGameElement2GameObject () {
		GameElement mario = stateMaker.getMario();
		printGameObject(converter.gameElement2GameObject(mario));
	}
	
	void printGameObject (GameObject go) {
		System.out.println("--------------------------");
		System.out.println("GameObject toString: " + go);
		System.out.println("Game Object Behaviors: " + go.getBehaviors() + " \n");
		for (AuthBehavior authB: go.getBehaviors()) {
			printAuthBehavior(authB);
			System.out.println();
		}
	}
	
//	@Test
	void testGameState2GameScene () {
		printScene(converter.gameState2GameScene(testState));
	}
	
	void printScene (GameScene scene) {
		System.out.println("Printing Scene: " + scene.getName());
		for (GameObject go: scene.getMyObjects()) {
			printGameObject(go);
		}
		System.out.println("Finished printing scene");
	}
	
	@Test
	void testConvertAndBack () {
		GameScene scene = converter.gameState2GameScene(testState);
		GameState s2 = converter.gameScene2GameState(scene);
		compareStates(testState, s2);
	}
	

	void compareStates(GameState s1, GameState s2) {
		List <GameElement> s1elems = s1.getElements();
		List <GameElement> s2elems = s2.getElements();
		
		System.out.println("State s1: ");

		for (GameElement s1elem: s1elems) {
			System.out.println(s1elem);
		}
		
		System.out.println("\nState s2: ");
		for (GameElement s2elem: s2elems) {
			System.out.println(s2elem);
		}
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
