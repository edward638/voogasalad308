package authoring;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class BehaviorFactoryTest {

	@Test
	void testGameObjectAddBehavior() {
		GameObject go = new GameObject(new Behavior());
		assertEquals(1, go.getBehaviors().size());
	}
	
}
