package engine.events.elementevents;

import javafx.scene.input.KeyCode;

public class KeyInputEvent extends ElementEvent {
	KeyCode code;
	
	public KeyInputEvent(KeyCode code) {
		this.code = code;
	}
	
	public KeyCode getKeyCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Key Input Event: " + code.toString() + " key pressed";
	}
	
}
