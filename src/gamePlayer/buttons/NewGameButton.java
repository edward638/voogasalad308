package gamePlayer.buttons;

/**
 * for selecting a new game
 * @author jeffreyli, calvinma
 *
 */
public class NewGameButton extends PlayerButtons {

	public NewGameButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, "New Game");
	}

	protected void setAction() {
		this.setOnAction(event -> {
			GameSelector gs = new GameSelector(buttonData, true);
			buttonData.addToRoot(gs);
		});

	}

}
