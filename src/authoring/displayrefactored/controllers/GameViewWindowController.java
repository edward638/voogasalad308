package authoring.displayrefactored.controllers;

import authoring.Game;
import authoring.displayrefactored.AuthoringEnvironmentGUIRefactored;
import authoring.displayrefactored.authoringuicomponents.GameViewWindowRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.layout.Pane;

public class GameViewWindowController extends Controller {
	
	Game game;
	GameViewWindowRefactored gameViewWindowRefactored;
	
	public GameViewWindowController(Game game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}

	@Override
	protected void initializeScreenComponents() {
		// TODO Auto-generated method stub
		gameViewWindowRefactored = new GameViewWindowRefactored(this);
		
	}

	@Override
	protected void setUpConnections() {
		// TODO Auto-generated method stub
		game.addObserver(gameViewWindowRefactored);
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
		// TODO Auto-generated method stub
		game.notifyMyObservers();
	}
	
	

}
