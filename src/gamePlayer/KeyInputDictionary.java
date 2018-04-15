package gamePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import engine.Engine;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInputDictionary {
	Map<KeyCode, KeyCode> dictionary;
	Engine engine;

	public KeyInputDictionary(Engine engine) {
		dictionary = new HashMap<KeyCode, KeyCode>();
		this.engine = engine;
	}

	public void replaceKey(KeyCode newInput, KeyCode oldInput) {
		dictionary.remove(oldInput);
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
	

	// public static void main(String[] args) {
	// KeyInputDictionary hi = new KeyInputDictionary();
	// hi.addKey(KeyCode.A, KeyCode.D);
	// hi.handleAction(KeyCode.A);
	// }
}
