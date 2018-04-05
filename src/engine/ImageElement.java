package engine;

import java.util.Map;

import javafx.scene.image.ImageView;

public class ImageElement extends ImageView{
	public GameElement elementReference;

	public void updateState() {
		Map<String, Object> properties = elementReference.reportProperties();
		this.setX((double) properties.get("xpos"));
	}
}
