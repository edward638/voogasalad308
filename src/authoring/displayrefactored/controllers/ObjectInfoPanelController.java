package authoring.displayrefactored.controllers;

import authoring.Game;
import authoring.GameObject;
import authoring.displayrefactored.authoringuicomponents.ObjectInfoPanelRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.layout.Pane;

public class ObjectInfoPanelController extends Controller {

	Game game;
	ObjectInfoPanelRefactored objectInfoPanelRefactored;
	
	public ObjectInfoPanelController(Game game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}
	
	@Override
	protected void initializeScreenComponents() {
		// TODO Auto-generated method stub
		objectInfoPanelRefactored = new ObjectInfoPanelRefactored(this);
		
	}

	@Override
	protected void setUpConnections() {
		// TODO Auto-generated method stub
		objectInfoPanelRefactored.setObservable(game);
		game.setObjectInfoObserver(objectInfoPanelRefactored);
		
	}

	@Override
	protected void addToGUI(Pane pane) {
		// TODO Auto-generated method stub
		int x = ResourceBundleManager.getPosition("OBJECTINFO_X");
		int y = ResourceBundleManager.getPosition("OBJECTINFO_Y");
		objectInfoPanelRefactored.AttachToPane(pane, x, y);
	}

	public void updatePositions() {
		refreshView();
	}
	
	@Override
	protected void refreshView() {
		// TODO Auto-generated method stub
		game.notifyMyObservers();
//		game.notifyObjectInfoObservers(game.getCurrentGameObject());
	}

}
