package engine;

import java.util.List;

import data.ImageManager;
import engine.behaviors.shapes.ShapeDefinition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageElement extends ImageView {
	private GameElement elementReference;
	
	ImageManager imageManager;
	String imageName;
	
	public ImageElement(GameElement elementReference, ImageManager imageManager) {
		this.elementReference = elementReference;
		
		this.imageManager = imageManager;
		imageName = (String)elementReference.reportProperties().get("imagePath");
		Image image = imageManager.getImage(imageName);
		this.setImage(image);
		
		updateState();
		
		//System.out.println(elementReference.reportProperties());
	}

	public void updateState() {
		if (!((String)elementReference.reportProperties().get("imagePath")).equals(imageName)){
			imageName = (String)elementReference.reportProperties().get("imagePath");
			Image image = imageManager.getImage(imageName);
			this.setImage(image);
		}
		this.setFitWidth((double)elementReference.reportProperties().get("displayWidth"));
		this.setFitHeight((double)elementReference.reportProperties().get("displayHeight"));
		this.setTranslateX((double) elementReference.reportProperties().get("xPos"));
		this.setTranslateY((double) elementReference.reportProperties().get("yPos"));
	}
	
	public void updateStateWithOffSet(List<Double> mainCharacterLocation) {
		if (!((String)elementReference.reportProperties().get("imagePath")).equals(imageName)){
			imageName = (String)elementReference.reportProperties().get("imagePath");
			Image image = imageManager.getImage(imageName);
			this.setImage(image);
		}
		this.setFitWidth((double)elementReference.reportProperties().get("displayWidth"));
		this.setFitHeight((double)elementReference.reportProperties().get("displayHeight"));
		this.setTranslateX((double) elementReference.reportProperties().get("xPos") + mainCharacterLocation.get(0));
		this.setTranslateY((double) elementReference.reportProperties().get("yPos") + mainCharacterLocation.get(1));
	}
	
	public GameElement getReference() {
		return elementReference;
	}
}
