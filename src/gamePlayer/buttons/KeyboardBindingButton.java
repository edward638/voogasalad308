package gamePlayer.buttons;

import javafx.scene.input.KeyCode;

public class KeyboardBindingButton extends PlayerButtons {

	private ButtonData buttonData;

	public KeyboardBindingButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height);
		this.buttonData = buttonData;
		this.setText("Change Key Bindings");
		setAction();
		initialiseOGKeyBindings();
	}

	private void initialiseOGKeyBindings() {
		buttonData.getKeyBindings().replaceKey(KeyCode.A, KeyCode.A ,KeyCode.A);
		buttonData.getKeyBindings().replaceKey(KeyCode.D, KeyCode.D, KeyCode.D);
		buttonData.getKeyBindings().replaceKey(KeyCode.W, KeyCode.W, KeyCode.W);
		buttonData.getKeyBindings().replaceKey(KeyCode.S, KeyCode.S, KeyCode.S);
	}

	@Override
	protected void setAction() {
		this.setOnAction(event -> {

			KeyBindingPopup keyPopup = new KeyBindingPopup(buttonData);

			keyPopup.show();
		});
	}

}
