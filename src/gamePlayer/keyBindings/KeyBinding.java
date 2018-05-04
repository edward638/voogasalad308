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
	private FadeTransition flashTransition;
	private Pane popUp;
	private KeyInputDictionary keyMap;
	private Label keyText;
	private Label repeatedKeyText;
	private static final String errorMessage = "KEY ALREADY BOUND";
	private static final Color errorColor = Color.RED;

	public KeyBinding(KeyCode keyCode, String theAction, KeyInputDictionary keyMap, Pane popUp) {
		defaultKeyCode = keyCode;
		action = theAction;
		this.keyMap = keyMap;
		currentBinding = keyMap.getKeyForValue(keyCode);
		this.popUp = popUp;
		nodeList = new ArrayList<>();
		addWords();
		setupRepeatKeyText();
		setupFlashTransition();
		setUpFadeTransition();
		makeChangeButton();
	}

	private void setupFlashTransition() {
		flashTransition = new FadeTransition(Duration.millis(300), repeatedKeyText);
		flashTransition.setFromValue(0);
		flashTransition.setToValue(1);
		flashTransition.setCycleCount(2);
		flashTransition.setAutoReverse(true);

	}

	private void setupRepeatKeyText() {
		repeatedKeyText = new Label(errorMessage);
		repeatedKeyText.setLayoutX(110);
		repeatedKeyText.setLayoutY(275);
		repeatedKeyText.setStyle("-fx-font: 18 Euphemia;");
		repeatedKeyText.setTextFill(errorColor);
		repeatedKeyText.setOpacity(0);
		popUp.getChildren().add(repeatedKeyText);
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
		fadeTransition = new FadeTransition(Duration.millis(300), keyText);
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
						flashTransition.play();
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
