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
		this.setLayoutY(100);
		keyMap = buttonData.getKeyBindings();

		keyMap.replaceKey(KeyCode.A, KeyCode.A);
		keyMap.replaceKey(KeyCode.D, KeyCode.D);
		keyMap.replaceKey(KeyCode.W, KeyCode.W);

		this.buttonData = buttonData;

		setupBackground();
		setupChangeButtons();
		setupCloseButton();

	}

	private void setupChangeButtons() {

		// for (KeyCode k : keyMap.keySet()) {
		// System.out.println(k.toString());
		// }
		int yVal = 70;
		for (KeyCode k : keyMap.getKeySet()) {
			Button changeA = new Button("Press and Type Key to Change Binding. Current Key = " + k.toString());
			changeA.setLayoutY(yVal);
			changeA.setLayoutX(35);
			yVal = yVal + 60;
			System.out.println(yVal);
			changeA.setOnAction(event -> {
				this.setOnKeyPressed(event1 -> {
					keyMap.replaceKey(event1.getCode(), k);
					changeA.setText("Press and Type Key to Change Binding. Current Key = " + event1.getCode().toString());
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
