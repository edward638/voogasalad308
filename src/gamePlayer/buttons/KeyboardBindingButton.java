package gamePlayer.buttons;

import javafx.scene.input.KeyCode;

public class KeyboardBindingButton extends PlayerButtons {

	public KeyboardBindingButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, "Change Key Bindings");
		initialiseOGKeyBindings();
	}

	private void initialiseOGKeyBindings() {
		buttonData.getKeyBindings().replaceKey(KeyCode.A, KeyCode.A, KeyCode.A);
		buttonData.getKeyBindings().replaceKey(KeyCode.D, KeyCode.D, KeyCode.D);
		buttonData.getKeyBindings().replaceKey(KeyCode.W, KeyCode.W, KeyCode.W);
	}

	@Override
	protected void setAction() {
		this.setOnAction(event -> {
			KeyBindingPopup keyPopup = new KeyBindingPopup(buttonData);
			keyPopup.show();
		});
	}

}
