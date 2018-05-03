package gamePlayer.buttons;

/**
 * a button for saving the game
 * 
 * @author jeffreyli
 *
 */
public class SaveButton extends PlayerButtons {

	public SaveButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, "Save Current State");
	}

	protected void setAction() {
		this.setOnAction(event -> {
			buttonData.saveGame();
		});
	}

}
