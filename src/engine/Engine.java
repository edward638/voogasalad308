package engine;

import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Engine {
	private Pane enginePane = new Pane();
	
	public Engine(String gamePath) {
		//EngineRunner engineRunner = new EngineRunner(gamePath);
	}
	
	public Pane startGame() {
		return enginePane;
	}

	public void updateDisplay(List<ImageElement> newElements, List<ImageElement> removeElements) {
		for (ImageView e:newElements) {
			enginePane.getChildren().add(e);
		}
		
		for (ImageView e:removeElements) {
			enginePane.getChildren().remove(e);
		}
	}

	public Pane getDisplay() {
		// TODO Auto-generated method stub
		return null;
	}
}
