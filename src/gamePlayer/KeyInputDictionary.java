package gamePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import engine.Engine;
import javafx.scene.input.KeyCode;

public class KeyInputDictionary {
	Map<KeyCode, KeyCode> dictionary;
	Engine engine;

	public KeyInputDictionary(Engine engine) {
		dictionary = new HashMap<KeyCode, KeyCode>();
		this.engine = engine;
	}

	public void replaceKey(KeyCode input, KeyCode mapTo, KeyCode oldInput) {

		dictionary.remove(oldInput);
		dictionary.put(input, mapTo);
	}

	public void handleAction(KeyCode input) {
		if (dictionary.containsKey(input)) {
			engine.handleKeyInput(dictionary.get(input));
//			System.out.println(dictionary.get(input));
		} 
	}

	public void setGame(Engine engine) {
		this.engine = engine;

	}

	public Set<KeyCode> getKeySet() {
		return dictionary.keySet();
	}

	public KeyCode getKey(KeyCode k) {
		return dictionary.get(k);
	}

	public void removeKey(KeyCode k) {
		System.out.println(dictionary);
		dictionary.remove(k);

	}

	public KeyCode getKeyForValue(KeyCode keyCode) {
		for (KeyCode key : dictionary.keySet()) {
			if (dictionary.get(key) == keyCode) {
				return key;
			}
		}
		return null;
	}
//
}
