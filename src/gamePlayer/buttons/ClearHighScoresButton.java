package gamePlayer.buttons;

public class ClearHighScoresButton extends PlayerButtons {

	public ClearHighScoresButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, "Clear High Scores");
	}

	protected void setAction() {
		this.setOnAction(event -> {
			buttonData.clearHighScores();
		});
	}

}
