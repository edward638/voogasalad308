package engine;

import java.util.Map;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ImageElement extends ImageView {
	private GameElement elementReference;
	
	public ImageElement(GameElement elementReference) {
		updateState();
	}

	public void updateState() {
		Map<String, Object> properties = elementReference.reportProperties();
		this.setX((double) properties.get("xpos"));
	}
	
	public GameElement getReference() {
		return elementReference;
	}
}
