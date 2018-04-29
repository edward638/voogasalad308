package gamePlayer;

import java.util.Optional;

import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Font;

public class Username {
	private String userName;
	private static final String FONT = "Din Alternate";
	private Label nameText;

	public Username() {
		setupUserNameInput();
		setupUserNameText();
	}

	private void setupUserNameInput() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Username");
		dialog.setHeaderText("Welcome to VoogaSalad!");
		dialog.setContentText("Please enter your username:");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> userName = result.get());
	}

	private void setupUserNameText() {
		nameText = new Label("User: " + userName);
		nameText.setLayoutX(970);
		nameText.setLayoutY(10);
		nameText.setFont(Font.font(FONT, 20));
	}

	public Label getNameText() {
		return nameText;
	}
	
	/**
	 * changes the username and updates the label to have the new name
	 * @param newName
	 */
	public void changeName(String newName) {
		userName = newName;
		nameText.setText("User: " + userName);
	}

	public String getName() {
		return userName;
	}

}
