package gamePlayer.keyBindings;

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class KeyBinding {
	private KeyCode defaultKeyCode;
	private KeyCode currentBinding;
	private String action;
	private ArrayList<Node> nodeList;
	private boolean keyIsChanged = false;
	private FadeTransition fadeTransition;
	private Pane popUp;
	private KeyInputDictionary keyMap;
	private Label keyText;

	public KeyBinding(KeyCode keyCode, String theAction, KeyInputDictionary keyMap, Pane popUp) {
		defaultKeyCode = keyCode;
		action = theAction;
		this.keyMap = keyMap;
		currentBinding = keyMap.getKeyForValue(keyCode);
		this.popUp = popUp;
		nodeList = new ArrayList<>();
		addWords();
		makeChangeButton();
		setUpFadeTransition();
	}

	/**
	 * adds the text side of the keyBinding
	 */
	private void addWords() {
		Label actionText = new Label(action + ":");
		actionText.setStyle("-fx-font: 18 Euphemia;");
		actionText.setTextFill(Color.ALICEBLUE);
		nodeList.add(actionText);

		keyText = new Label(currentBinding.toString());
		keyText.setStyle("-fx-font: 20 Euphemia;");
		keyText.setTextFill(Color.ALICEBLUE);
		nodeList.add(keyText);
	}

	private void setUpFadeTransition() {
		fadeTransition = new FadeTransition(Duration.millis(500), keyText);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.1);
		fadeTransition.setCycleCount(Timeline.INDEFINITE);
		fadeTransition.setAutoReverse(true);
	}

	/**
	 * makes a button to change the key binding for a command
	 */
	private void makeChangeButton() {

		Button change = new Button("Change");
		change.setOnAction(pushButtonEvent -> {
			fadeTransition.play();
			keyIsChanged = false;
			popUp.setOnKeyPressed(keyPressInput -> {
				KeyCode keyPressInputCode = keyPressInput.getCode();
				if (keyIsChanged == false) {
					if (!keyMap.containsKey(keyPressInputCode)) {
						keyMap.replaceKey(keyPressInputCode, defaultKeyCode, currentBinding);
						keyText.setText(keyPressInputCode.toString());
						fadeTransition.stop();
						keyText.setOpacity(1);
						keyIsChanged = true;
					} else if (keyMap.containsKey(keyPressInputCode)) {
						// add code here to show something that the key is already set to a command
					}

				}
			});
		});
		nodeList.add(change);
	}

	public ArrayList<Node> getNodeList() {
		return this.nodeList;
	}
}
