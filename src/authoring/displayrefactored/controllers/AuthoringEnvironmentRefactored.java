package authoring.displayrefactored.controllers;

import java.util.ArrayList;
import java.util.List;

import authoring.Game;
import authoring.displayrefactored.controllers.Controller;
import authoring.displayrefactored.controllers.GameViewWindowController;
import authoring.displayrefactored.controllers.LevelPanelController;
import authoring.displayrefactored.popups.NewGameObjectPopupRefactored;
import data.ImageManager;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.layout.Pane;

public class AuthoringEnvironmentRefactored {

	private List<Controller> controllerList;
	private Game game;
	private GameViewWindowController gameViewWindowController;
	private LevelPanelController levelPanelController;
	private ObjectInfoPanelController objectInfoPanelController;
	private ImageManager imageManager;
	private Pane pane;
	
	public AuthoringEnvironmentRefactored(Game game) {
		this.game = game;
		imageManager = new ImageManager(game.getName());
		pane = new Pane();
		pane.setPrefSize(ResourceBundleManager.getPosition("ENVIRONMENTSIZE_X"), ResourceBundleManager.getPosition("ENVIRONMENTSIZE_Y"));
		pane.setStyle("-fx-border-color: black");
		createControllers();
		setUpControllers();
	}
	
	private void createControllers() {
		controllerList = new ArrayList<>();
//		//TODO:
//		gameViewWindowController = new GameViewWindowController(game);
		levelPanelController = new LevelPanelController(game.getSceneManager(), imageManager);
//		objectInfoPanelController = new ObjectInfoPanelController(game);
//		controllerList.add(gameViewWindowController);
//		controllerList.add(objectInfoPanelController);
		controllerList.add(levelPanelController);
		gameViewWindowController = levelPanelController.getGameViewWindowController();
		objectInfoPanelController = levelPanelController.getObjectInfoPanelController();
		controllerList.add(gameViewWindowController);
		controllerList.add(objectInfoPanelController);
	}
	
	private void setUpControllers() {
		for (Controller controller: controllerList) {
			controller.initializeScreenComponents();
			controller.setUpConnections();
			controller.addToGUI(pane);
		}
		game.getSceneManager().notifyMyObservers();
		game.getSceneManager().getCurrentScene().notifyMyObservers();
	}
	

	
	public Pane getGUI() {
		return pane;	
	}
	
}
