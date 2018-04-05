package engine;

import java.util.LinkedList;
import java.util.List;

import engine.events.ElementEvent;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public class DisplayState {
	public List<ImageElement> activeElements;
	public List<ImageElement> newElements;
	public List<ImageElement> removeElements;
	
	public void addNewElements() {
		
	}

	public void updateImageElements() {
		for (ImageElement imageElement : activeElements) {
			imageElement.updateState();
		}
	}
	
	public List<ElementEvent> detectCollisions() {
		List<ElementEvent> collisionEvents = new LinkedList<CollisionEvent>();
		for (ImageElement element1 : activeElements) {
			for (ImageElement element2 : activeElements) {
				if (element1 != element2) {
					if (element1.getBoundsInLocal().intersects(element2.getBoundsInLocal())) {
						ElementEvent collision = new CollisionEvent(e1, e2);
						collisionEvents.add(collision);
					}
					//Shape intersect = Shape.intersect(element1, element2.getImageView());
					//if (intersect.getBoundsInLocal().getWidth() != -1) {
					
				}
			}
		}
		return collisionEvents;
	}
	
	
	
}
