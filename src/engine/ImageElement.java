package engine;

import java.util.Map;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ImageElement extends ImageView {
	public GameElement elementReference;
	
	public ImageElement(GameElement elementReference) {
		updateState();
	}

	public void updateState() {
		Map<String, Object> properties = elementReference.reportProperties();
		this.setX((double) properties.get("xpos"));
	}
	
	public Shape toShape() {
		Rectangle s = new Rectangle();
		Bounds b = this.getBoundsInLocal();
		s.setX(b.getMinX());
		s.setY(b.getMinY());
		s.setWidth(b.getWidth());
		s.setHeight(b.getHeight());
		return s;
	}
}
