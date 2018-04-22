package gamePlayer.keyBindings;


import java.util.ArrayList;
import gamePlayer.buttons.ButtonData;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class KeyBindingPopup extends Pane {

	private ButtonData buttonData;
	private KeyInputDictionary keyMap;
	

	private static final int SPACE_BETWEEN_BUTTONS = 80;

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

		KeyBinding W = new KeyBinding(KeyCode.W, "Jump", keyMap, this);
		KeyBinding A = new KeyBinding(KeyCode.A, "Left", keyMap, this);
		KeyBinding D = new KeyBinding(KeyCode.D, "Right", keyMap, this);
		
		addNodeListToGui(90, 80, W.getNodeList());
		addNodeListToGui(150, 80, A.getNodeList());
		addNodeListToGui(210, 80, D.getNodeList());
	}

	/**
	 * adds the nodeList (which represents one row in the key binding screen) to the
	 * gui
	 * 
	 * @param yPosition:
	 *            of row
	 * @param xPosition:
	 *            of first node
	 * @param nodeList:
	 *            nodes to add
	 */
	private void addNodeListToGui(int yPosition, int xPosition, ArrayList<Node> nodeList) {
		for (Node n : nodeList) {
			n.setLayoutY(yPosition);
			n.setLayoutX(xPosition);
			xPosition = xPosition + SPACE_BETWEEN_BUTTONS;
		}
		this.getChildren().addAll(nodeList);
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