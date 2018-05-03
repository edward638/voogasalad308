package gamePlayer.buttons;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;

/**
 * button pressed for changing the username
 * @author jeffreyli
 *
 */
public class ChangeNameButton extends PlayerButtons {
	String newName;

	public ChangeNameButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, "Change");
	}

	@Override
	protected void setAction() {
		this.setOnAction(event -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Username");
			dialog.setHeaderText("Change your username!");
			dialog.setContentText("Please enter your username:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(name -> newName = result.get());

			buttonData.changeUsername(newName);
		});
	}

}
