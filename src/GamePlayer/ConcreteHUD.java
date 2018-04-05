package GamePlayer;

import java.util.Map;


import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ConcreteHUD extends HBox implements HUD{
	
	public ConcreteHUD() {
		setupPane();
		
	}
	
	private void setupPane() {
		this.setLayoutX(90);
		this.setLayoutY(90);
		this.setWidth(90);
		this.setHeight(20);
		Rectangle rect = new Rectangle(0,0,500,500);
		rect.setFill(Color.BLACK);
		this.getChildren().add(rect);
	}
	
	public Pane getPane() {
		return this;
	}
	

	@Override
	public void updateInfo(int score, int lives, Map<?, ?> OtherInfo) {
		// TODO Auto-generated method stub
		
	}

}
