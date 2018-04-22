package gamePlayer.keyBindings;

import gamePlayer.buttons.ButtonData;
import gamePlayer.buttons.PlayerButtons;
import javafx.scene.input.KeyCode;
/**
 * the button to press if you want to launch key bindings editing
 * @author jeffreyli
 *
 */
public class KeyboardBindingButton extends PlayerButtons {

	public KeyboardBindingButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, "Change Key Bindings");
		initialiseOGKeyBindings();
	}
	
	/**
	 * initialises the hardcoded default key bindings
	 */
	private void initialiseOGKeyBindings() {
		buttonData.getKeyBindings().replaceKey(KeyCode.A, KeyCode.A, KeyCode.A);
		buttonData.getKeyBindings().replaceKey(KeyCode.D, KeyCode.D, KeyCode.D);
		buttonData.getKeyBindings().replaceKey(KeyCode.W, KeyCode.W, KeyCode.W);
	}

	@Override
	protected void setAction() {
		this.setOnAction(event -> {
			buttonData.pauseGame();
			KeyBindingPopup keyPopup = new KeyBindingPopup(buttonData);
			keyPopup.show();
		});
	}

}
