package display.text;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SplashText extends Text {

	public SplashText(String text, int x_loc, int y_loc, Color textColor, int fontSize) {
		this.setText(text);
		this.setLayoutX(x_loc);
		this.setLayoutY(y_loc);
		this.setFill(textColor);
		this.setFont(new Font("Helvetica", fontSize));
	}

}
