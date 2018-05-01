package gamePlayer.buttons;

/**
 * a button for restarting the current game
 * @author jeffreyli
 *
 */
public class ReplayButton extends PlayerButtons {

	public ReplayButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, "Restart Game");
	}

	protected void setAction() {

		this.setOnAction(event -> {
			buttonData.playGame(buttonData.getMostRecentFile(), true);
		});
	}

}
