package engine;

import java.util.ArrayList;
import java.util.List;

import data.ImageManager;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DisplayState {
	protected List<ImageElement> activeElements;
	protected List<ImageElement> newElements;
	protected List<ImageElement> removeElements;
	
	private String gameName;
	
	public DisplayState(String gameName) {
		this.gameName = gameName;
		activeElements = new ArrayList<>();
		newElements = new ArrayList<>();
		removeElements = new ArrayList<>();
	}
	
	public void addNewElement(GameElement element) {
		ImageElement imageElement = new ImageElement(element, new ImageManager(gameName));
		newElements.add(imageElement);
		activeElements.add(imageElement);
	}
	
	protected void removeElement(GameElement element) {
		activeElements.stream().filter(c -> c.getReference() == element).map(c -> activeElements.remove(c));
		activeElements.stream().filter(c -> c.getReference() == element).map(c -> removeElements.add(c));
	}


	protected void updateImageElements(List<Double> mainCharacterLocation) {
		for (ImageElement imageElement : activeElements) {
			imageElement.updateStateWithOffSet(mainCharacterLocation);
		}
	}
	
	public static void main(String[] args) {
		Rectangle r1 = new Rectangle();
		Rectangle r2 = new Rectangle();
		r1.setX(4);
		r1.setY(4);
		r1.setWidth(4);
		r1.setHeight(4);
		r2.setX(3);
		r2.setY(2);
		r2.setWidth(4);
		r2.setHeight(5);
		Shape intersect = Shape.intersect(r1, r2);
		System.out.println(((Path) intersect).getElements());
		//int side = getCollisionSide(getCenter(r1), getCenter(intersect));
		//System.out.println(side);
		
	}
	
}
