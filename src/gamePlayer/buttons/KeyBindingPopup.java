package gamePlayer.buttons;

import java.awt.Font;
import java.util.ArrayList;

import gamePlayer.KeyInputDictionary;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
		Text title = new Text();
		title.setText("Key Bindings");
		title.setStyle("-fx-font: 24 Verdana;");
		title.setFill(Color.LIGHTSKYBLUE);
		title.setX(40);
		title.setY(30);
		this.getChildren().add(title);
	}

	private void setupChangeButtons() {

		makeKeyChangeButton(90, KeyCode.W, "Jump");
		makeKeyChangeButton(150, KeyCode.A, "Left");
		makeKeyChangeButton(210, KeyCode.D, "Right");

	}

	/**
	 * makes a button to change the key binding for a command
	 * 
	 * @param yVal:
	 *            y value of position
	 * @param keyCode:
	 *            DEFAULT keycode of action
	 * @param action:
	 *            name of action/command
	 */
	private void makeKeyChangeButton(int yVal, KeyCode keyCode, String action) {

		KeyCode currentBinding = keyMap.getKeyForValue(keyCode);
		
		ArrayList<Node> nodeList = new ArrayList<>();
		Button change = new Button("Change");
		Label actionText = new Label(action + ":");
		actionText.setStyle("-fx-font: 18 Euphemia;");
		actionText.setTextFill(Color.ALICEBLUE);
		Label keyText = new Label(currentBinding.toString());
		keyText.setStyle("-fx-font: 20 Euphemia;");
		keyText.setTextFill(Color.ALICEBLUE);

		nodeList.add(actionText);
		nodeList.add(keyText);
		nodeList.add(change);

		change.setOnAction(pushButtonEvent -> {
			this.setOnKeyPressed(keyPressInput -> {
				keyMap.replaceKey(keyPressInput.getCode(), keyCode, currentBinding);
				keyText.setText(keyPressInput.getCode().toString());
			});
		});
		int xVal = 80;
		for (Node n : nodeList) {
			n.setLayoutY(yVal);
			n.setLayoutX(xVal);
			xVal = xVal + 80;
		}

		this.getChildren().addAll(nodeList);

//		Button changeA = new Button("Key for " + action + " is: " + currentBinding.toString());
//		changeA.setLayoutY(yVal);
//		changeA.setLayoutX(200);
//		
//		changeA.setOnAction(pushButtonEvent -> {
//			this.setOnKeyPressed(keyPressInput -> {
//				keyMap.replaceKey(keyPressInput.getCode(), keyCode, currentBinding);
//				changeA.setText("Key for " + action + " is: " + keyPressInput.getCode().toString());
//			});
//		});
//
//		this.getChildren().add(changeA);
	}

	private void setupCloseButton() {
		Button close = new Button("Close");
		close.setLayoutX(175);
		close.setLayoutY(350);
		close.setOnAction(event -> {
			buttonData.resumeGame();
			buttonData.removeFromRoot(this);
		});
		this.getChildren().add(close);

	}

	private void setupBackground() {
		Rectangle background = new Rectangle(0, 0, 400, 400);
		background.setFill(Color.BLACK);
		background.setOpacity(0.3);
		this.getChildren().add(background);
	}

	public void show() {
		buttonData.addToRoot(this);

	}

}