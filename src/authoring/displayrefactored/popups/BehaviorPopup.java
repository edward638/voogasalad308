package authoring.displayrefactored.popups;

import java.util.List;

import authoring.GameObject;
import authoring.displayrefactored.controllers.BehaviorPopupController;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BehaviorPopup extends PopupRefactored {

	private static final int xSize = 500;
	private static final int ySize = 500;
	private static final int DEFAULT_SPACING = 10;
	private BehaviorPopupController controller;
	private List<GameObject> myGameObjects;
	private HBox myHBox;
	
	public BehaviorPopup(List<GameObject> currentObjects) {
		super();
		myGameObjects = currentObjects;
		controller = new BehaviorPopupController(myGameObjects);
		initializeRoot();
		open(xSize, ySize);
	}
	
	private void initializeRoot() {
		myHBox.getChildren().addAll(controller.getPanels());
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
