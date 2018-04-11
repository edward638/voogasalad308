package engine;

import java.util.Map;

import data.ImageManager;
import engine.behaviors.shapes.ShapeDefinition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageElement extends ImageView {
	private GameElement elementReference;
	
	ImageManager imageManager = new ImageManager("enginetestmario");
	String imageName;
	
	public ImageElement(GameElement elementReference) {
		this.elementReference = elementReference;
		
		imageName = (String)elementReference.reportProperties().get("imagePath");
		Image image = imageManager.getImage(imageName);
		this.setImage(image);
		
		updateState();
		
		System.out.println(elementReference.reportProperties());
		
	}

	public void updateState() {
		if (!((String)elementReference.reportProperties().get("imagePath")).equals(imageName)){
			imageName = (String)elementReference.reportProperties().get("imagePath");
			Image image = imageManager.getImage(imageName);
			this.setImage(image);
		}
		this.setFitWidth(((ShapeDefinition)elementReference.reportProperties().get("shapeDef")).getWidth());
		this.setFitHeight(((ShapeDefinition)elementReference.reportProperties().get("shapeDef")).getHeight());
		this.setTranslateX((double) elementReference.reportProperties().get("xPos"));
		this.setTranslateY((double) elementReference.reportProperties().get("yPos"));
	}
	
	public GameElement getReference() {
		return elementReference;
	}
}
