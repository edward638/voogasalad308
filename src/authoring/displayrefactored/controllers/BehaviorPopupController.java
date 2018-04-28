package authoring.displayrefactored.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.displayrefactored.popups.behaviorspopup.BehaviorPanel;
import authoring.displayrefactored.popups.behaviorspopup.PropertyPanel;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class BehaviorPopupController extends PopupController {
	private List<GameObject> myGameObjects;
	private BehaviorPanel myBehaviorPanel;
	private PropertyPanel myPropertyPanel;


	public BehaviorPopupController(List<GameObject> gameObjects) {
		myGameObjects = gameObjects;
		initializeScreenComponents();
	}

	@Override
	protected void initializeScreenComponents() {
		myBehaviorPanel = new BehaviorPanel(this, myGameObjects);
		myPropertyPanel = new PropertyPanel(this, myGameObjects);
	}

	@Override
	protected void setUpConnections() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addToGUI(Pane pane) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void refreshView() {
		// TODO Auto-generated method stub

	}
	
	public List<Node> getPanels() {
		List<Node> panels = new ArrayList<>();
		panels.add(myBehaviorPanel.asNode());
		panels.add(myPropertyPanel.asNode());
		return panels;
	}
	
	private Set<AuthBehavior> getAllBehaviors() {
		return null;
	}
	
	public AuthBehavior getCurrBehavior() {
		return myBehaviorPanel.getCurrBehavior();
	}

}
