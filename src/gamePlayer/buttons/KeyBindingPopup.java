package gamePlayer.buttons;

import gamePlayer.KeyInputDictionary;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class KeyBindingPopup extends Pane {

	private ButtonData buttonData;
	private KeyInputDictionary keyMap;

	public KeyBindingPopup(ButtonData buttonData) {
		this.setLayoutX(300);
		this.setLayoutY(100);
		keyMap = buttonData.getKeyBindings();

		this.buttonData = buttonData;

		setupBackground();
		setupChangeButtons();
		setupCloseButton();
		setUpTitle();

	}

	private void setUpTitle() {
		Label title = new Label("Press Button and Keyboard Input to Change Text");
		title.setLayoutX(10);
		title.setTextFill(Color.WHITE);
		this.getChildren().add(title);
	}


	private void setupChangeButtons() {
		makeKeyChangeButton(90, KeyCode.W, "Jump");
		makeKeyChangeButton(150, KeyCode.A, "Left");
		makeKeyChangeButton(210, KeyCode.D, "Right");
	}
	
	/**
	 * makes a button to change the key binding for a command
	 * @param yVal: y value of position
	 * @param keyCode: DEFAULT keycode of action
	 * @param action: name of action/command
	 */
	private void makeKeyChangeButton(int yVal, KeyCode keyCode, String action) {
		KeyCode currentBinding = keyMap.getKeyForValue(keyCode);
		Button changeA = new Button("Key for " + action + " is: " + currentBinding.toString());
		changeA.setLayoutY(yVal);
		changeA.setLayoutX(200);
		changeA.setOnAction(pushButtonEvent -> {

			this.setOnKeyPressed(keyPressInput -> {
				keyMap.replaceKey(keyPressInput.getCode(), keyCode, currentBinding);
				changeA.setText("Key for " + action + " is: " + keyPressInput.getCode().toString());
			});
		});
		this.getChildren().add(changeA);
	}
	
	private void setupCloseButton() {
		Button close = new Button("Close");
		close.setLayoutX(200);
		close.setLayoutY(30);
		close.setOnAction(event -> {
			buttonData.removeFromRoot(this);
		});
		this.getChildren().add(close);

	}

	private void setupBackground() {
		Rectangle background = new Rectangle(0, 0, 400, 400);
		background.setFill(Color.GREY);
		this.getChildren().add(background);
	}

	public void show() {
		buttonData.addToRoot(this);

	}

}