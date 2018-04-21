package authoring.displayrefactored.controllers;

import java.util.ArrayList;
import java.util.List;

import authoring.Game;
import authoring.displayrefactored.AuthoringEnvironmentGUIRefactored;
import authoring.displayrefactored.controllers.Controller;
import authoring.displayrefactored.controllers.GameViewWindowController;
import authoring.displayrefactored.controllers.LevelPanelController;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.layout.Pane;

public class AuthoringEnvironmentRefactored {

	private AuthoringEnvironmentGUIRefactored authoringEnvironmentGUIRefactored;
	private List<Controller> controllerList;
	private Game game;
	Pane pane;
	
	public AuthoringEnvironmentRefactored(Game game) {
		this.game = game;
//		authoringEnvironmentGUIRefactored = new AuthoringEnvironmentGUIRefactored();
		pane = new Pane();
		pane.setPrefSize(ResourceBundleManager.getPosition("ENVIRONMENTSIZE_X"), ResourceBundleManager.getPosition("ENVIRONMENTSIZE_Y"));
		pane.setStyle("-fx-border-color: black");
		createControllers();
		setUpControllers();
	}
	
	private void createControllers() {
		controllerList = new ArrayList<>();
		controllerList.add(new LevelPanelController(game));
		controllerList.add(new GameViewWindowController(game));
	}
	
	private void setUpControllers() {
		for (Controller controller: controllerList) {
			controller.initializeScreenComponents();
			controller.setUpConnections();
			controller.addToGUI(pane);
		}
	}
	
	public Pane getGUI() {
		return pane;	
	}
	
}
