package authoring.displayrefactored.controllers;

import authoring.Game;
import authoring.displayrefactored.AuthoringEnvironmentGUIRefactored;
import authoring.displayrefactored.authoringuicomponents.LevelPanelRefactored;
import data.propertiesFiles.ResourceBundleManager;

public class LevelPanelController extends Controller {

	Game game;
	LevelPanelRefactored levelPanelRefactored;
	
	public LevelPanelController(Game game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}
	
	@Override
	protected void initializeScreenComponents() {
		// TODO Auto-generated method stub
		levelPanelRefactored = new LevelPanelRefactored();
	}

	@Override
	protected void setUpConnections() {
		// TODO Auto-generated method stub
		game.addObserver(levelPanelRefactored);
	}

	@Override
	protected void addToGUI(AuthoringEnvironmentGUIRefactored gui) {
		// TODO Auto-generated method stub
		int x = ResourceBundleManager.getPosition("LEVELPANEL_X");
		int y = ResourceBundleManager.getPosition("LEVELPANEL_Y");
		levelPanelRefactored.AttachToPane(gui.getPane(), x, y);
	}
	

}
