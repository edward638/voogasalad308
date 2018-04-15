package gamePlayer.buttons;

public class KeyboardBindingButton extends PlayerButtons {

	private ButtonData buttonData;

	public KeyboardBindingButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height);
		this.buttonData = buttonData;
		this.setText("Change Key Bindings");
		setAction();
	}

	@Override
	protected void setAction() {
		this.setOnAction(event -> {

			KeyBindingPopup keyPopup = new KeyBindingPopup(buttonData);
			keyPopup.show();
		});
	}

}
