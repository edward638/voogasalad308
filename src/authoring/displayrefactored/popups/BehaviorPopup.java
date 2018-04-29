package authoring.displayrefactored.popups;

import java.util.List;

import authoring.GameObject;
import authoring.displayrefactored.controllers.BehaviorPopupController;
import javafx.scene.layout.HBox;

public class BehaviorPopup extends PopupRefactored {

	private static final int xSize = 500;
	private static final int ySize = 700;
	private static final int DEFAULT_SPACING = 10;
	private BehaviorPopupController myController;
	private List<GameObject> myGameObjects;
	private HBox myHBox;
	
	public BehaviorPopup(List<GameObject> currentObjects) {
		super();
		myHBox = new HBox(DEFAULT_SPACING);
		myGameObjects = currentObjects;
		myController = new BehaviorPopupController(myGameObjects);
		initializeRoot();
		generatePopup();
		open(xSize, ySize);
	}
	
	private void initializeRoot() {
		myHBox.getChildren().addAll(myController.getPanels());
	}

	@Override
	protected void generatePopup() {
		getPane().getChildren().add(myHBox);
	}

	@Override
	protected void mapButtons() {
		//no buttons being mapped
	}

}
