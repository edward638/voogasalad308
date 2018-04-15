package gamePlayer;

import java.util.HashMap;
import java.util.Map;

import engine.Engine;
import javafx.scene.input.KeyCode;

public class KeyInputDictionary {
	Map<KeyCode, KeyCode> dictionary;
	Engine engine;

	public KeyInputDictionary() {
	}

	public KeyInputDictionary(Engine engine) {
		dictionary = new HashMap<KeyCode, KeyCode>();
		this.engine = engine;
	}

	public void addKey(KeyCode newInput, KeyCode oldInput) {
		dictionary.put(newInput, oldInput);
	}

	public void handleAction(KeyCode input) {
		if (dictionary.containsKey(input)) {
			engine.handleKeyInput(dictionary.get(input));
			System.out.println(dictionary.get(input));
		} else {
			engine.handleKeyInput(input);
			System.out.println(input);
		}
	}

	// public static void main(String[] args) {
	// KeyInputDictionary hi = new KeyInputDictionary();
	// hi.addKey(KeyCode.A, KeyCode.D);
	// hi.handleAction(KeyCode.A);
	// }
}
