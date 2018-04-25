package authoring.displayrefactored.controllers;

import authoring.Game;
import authoring.GameScene;
import authoring.displayrefactored.authoringuicomponents.GameViewWindowRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.layout.Pane;

public class GameViewWindowController extends Controller {
	
	GameScene gameScene;
	GameViewWindowRefactored gameViewWindowRefactored;
	
	public GameViewWindowController(GameScene gameScene) {
		// TODO Auto-generated constructor stub
		this.gameScene = gameScene;
	}

	@Override
	protected void initializeScreenComponents() {
		// TODO Auto-generated method stub
		gameViewWindowRefactored = new GameViewWindowRefactored(this);
	}

	@Override
	protected void setUpConnections() {
		// TODO Auto-generated method stub
		gameScene.addObserver(gameViewWindowRefactored);
	}

	@Override
	protected void addToGUI(Pane pane) {
		// TODO Auto-generated method stub
		int x = ResourceBundleManager.getPosition("GAMEVIEWWINDOW_X");
		int y = ResourceBundleManager.getPosition("GAMEVIEWWINDOW_Y");
		gameViewWindowRefactored.AttachToPane(pane, x, y);
	}

	@Override
	protected void refreshView() {
	}
	
	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
		setUpConnections();
	}
	
	

}
