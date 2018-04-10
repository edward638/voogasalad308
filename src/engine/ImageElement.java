package engine;

import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageElement extends ImageView {
	private GameElement elementReference;
	
	public ImageElement(GameElement elementReference) {
		//updateState();
		
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("Goomba.png"));
		this.setImage(image);
		//this.setFitWidth((double) elementReference.reportProperties().get("Width"));
		System.out.println(elementReference.reportProperties().get("shapeDef"));
		//this.setFitHeight((double) elementReference.reportProperties().get("Height"));
		System.out.println(elementReference.reportProperties());//.get("imagePath").toString());
	}

	public void updateState() {
		Map<String, Object> properties = elementReference.reportProperties();
		this.setX((double) properties.get("xpos"));
		this.setY((double) properties.get("ypos"));
	}
	
	public GameElement getReference() {
		return elementReference;
	}
}
