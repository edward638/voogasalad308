package display.buttons;

import display.buttonevents.ButtonEvent;
import javafx.scene.control.Button;

/**
 * @author August Ning
 * A GUI button that will be used in the splash screen and can be used in other GUI packages
 */
public class GUIButton extends Button {

	private String buttonLabel;
	private ButtonEvent eventResponse;
	private static final int X_LOC = 0;
	private static final int Y_LOC = 0;
	private static final int X_SIZE = 0;
	private static final int Y_SIZE = 0;
	
	public GUIButton(String label, ButtonEvent response) {
		buttonLabel = label;
		eventResponse = response;
		this.setText(buttonLabel);
	}

}
