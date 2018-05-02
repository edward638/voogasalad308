package gamePlayer.buttons;

/**
 * button user presses to launch the game loader
 * 
 * @author jeffreyli, calvinma
 *
 */
public class LoadButton extends PlayerButtons {

	public LoadButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, "Load Game");
	}

	protected void setAction() {
		this.setOnAction(event -> {
			GameSelector gs = new GameSelector(buttonData, false);
			buttonData.addToRoot(gs);
		});

	}

}
