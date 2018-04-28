package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import data.ImageManager;
import engine.behaviors.MainCharacter;
import javafx.scene.Group;

public class DisplayState {
	public static final double SUBSCENE_WIDTH =  900;
    public static final double SUBSCENE_HEIGHT = 590;
	
	private List<ImageElement> imageElements;
	private Group subSceneRoot = new Group();
	private ImageManager imageManager;
	
	public DisplayState (GameState gameState, String gameName) {
		imageManager = new ImageManager(gameName);
		imageElements = new ArrayList<>();
		
		for(GameElement e : gameState.getCurrentGamePart().getElements()) {
			addElementToDisplay(e);
		}
		gameState.clearAddToDisplay();
	}
	
	private void addElementToDisplay(GameElement element) {
		ImageElement imageElement = new ImageElement(element, imageManager);
		imageElements.add(imageElement);
		subSceneRoot.getChildren().add(imageElement);
	}
	
	private void removeElementFromDisplay(GameElement element) {
		List<ImageElement> toRemove = new ArrayList<>();
		for (ImageElement i : imageElements) {
			if (i.getReference() == element) {
				toRemove.add(i);
			}
		}
		for (ImageElement i : toRemove) {
			imageElements.remove(i);
			subSceneRoot.getChildren().remove(i);
		}
	}
	
	public Group getSubSceneRoot() {
		return subSceneRoot;
	}
	
	public void update(GameState updatedGameState) {
		updateImageElements(scrollingAroundMainCharacter(updatedGameState.getCurrentGamePart()));
		
		for (GameElement e : updatedGameState.getAddToDisplay()) {
			addElementToDisplay(e);
		}
		updatedGameState.clearAddToDisplay();
		for (GameElement e : updatedGameState.getRemoveFromDisplay()) {
			removeElementFromDisplay(e);
		}
		updatedGameState.clearRemoveFromDisplay();
	}

	private void updateImageElements(List<Double> mainCharacterLocation) {
		for (ImageElement imageElement : imageElements) {
			imageElement.updateStateWithOffSet(mainCharacterLocation);
		}
	}
	
	private List<Double> scrollingAroundMainCharacter(GamePart gamePart) {
		List<Double> offset = Arrays.asList(0.0,0.0);
		for (GameElement e: gamePart.getElements()) {
			if (e.hasBehavior(MainCharacter.class)) {
				MainCharacter mc_props = (MainCharacter) e.getBehavior(MainCharacter.class);
				offset = mc_props.getImageViewOffset(SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
			}
		}
		return offset;
	}
}
