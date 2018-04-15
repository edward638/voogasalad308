package gamePlayer.buttons;

import java.util.Map;

import gamePlayer.KeyInputDictionary;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class KeyBindingPopup extends Pane {

	private ButtonData buttonData;
	private KeyInputDictionary keyMap;

	public KeyBindingPopup(ButtonData buttonData) {
		this.setLayoutX(300);
		this.setLayoutY(300);
		keyMap = buttonData.getKeyBindings();

		this.buttonData = buttonData;

		setupBackground();
		setupCloseButton();
		setupChangeButtons();

	}

	private void setupChangeButtons() {
		int yVal = 50;
		for (KeyCode keyCode : keyMap.getKeySet()) {
			Button changeA = new Button("Press and Type Key to Change Binding. Current Key = " + keyCode.toString());
			changeA.setLayoutY(yVal);
			yVal = yVal + 60;
			System.out.println(yVal);
			changeA.setOnAction(pushButtonEvent -> {
				this.setOnKeyPressed(keyPressInput -> {
					keyMap.replaceKey(keyPressInput.getCode(), keyCode, keyCode);
					changeA.setText("Press and Type Key to Change Binding. Current Key = "
							+ keyPressInput.getCode().toString());
				});
			});
			this.getChildren().add(changeA);
		}
	}

	private void setupCloseButton() {
		Button close = new Button("Close");

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
