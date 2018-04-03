package engine.tests;

import java.util.Map;

import engine.GameElement;
import engine.behaviors.Behavior;
import engine.behaviors.Movable;

public class GameElementTesting {
	
	public static void main(String[] args) {
		addBehaviorTest();
	}
	public static void addBehaviorTest() {
		GameElement ge = new GameElement();
		System.out.println(ge.reportProperties());
		Behavior b = new Movable(ge);
		ge.addBehavior(b);
		System.out.println(ge.reportProperties());
	}
}
