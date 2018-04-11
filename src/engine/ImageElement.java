package engine;

import java.util.Map;

import engine.behaviors.shapes.ShapeDefinition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageElement extends ImageView {
	private GameElement elementReference;
	
	public ImageElement(GameElement elementReference) {
		//updateState();
		
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("./Goomba.png"));
		this.setImage(image);
		this.setFitWidth(((ShapeDefinition)elementReference.reportProperties().get("shapeDef")).getWidth());
		this.setFitHeight(((ShapeDefinition)elementReference.reportProperties().get("shapeDef")).getHeight());
		this.setTranslateX((double) elementReference.reportProperties().get("xPos"));
		this.setTranslateY((double) elementReference.reportProperties().get("yPos"));
		System.out.println(elementReference.reportProperties().get("imagePath").toString());
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
