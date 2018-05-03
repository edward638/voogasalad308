package authoring.display.controllers;

import java.util.ArrayList;
import java.util.List;

import authoring.Game;
import authoring.display.controllers.Controller;
import authoring.display.controllers.GameViewWindowController;
import authoring.display.controllers.LevelPanelController;
import authoring.display.popups.NewGameObjectPopup;
import data.AudioManager;
import data.GameObjectManager;
import data.ImageManager;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Edward Zhuang
 *
 */
public class AuthoringEnvironment {

	private List<Controller> controllerList;
	private Game game;
	private GameViewWindowController gameViewWindowController;
	private LevelPanelController levelPanelController;
	private ObjectInfoPanelController objectInfoPanelController;
	private AudioController audioController;
	private GameObjectManager gameObjectManager;
	private ImageManager imageManager;
	private AudioManager audioManager;
	private Pane pane;
	
	public AuthoringEnvironment(Game game) {
		this.game = game;
		imageManager = new ImageManager(game.getName());
		audioManager = new AudioManager(game.getName());
		pane = new Pane();
		pane.setPrefSize(ResourceBundleManager.getPosition("ENVIRONMENTSIZE_X"), ResourceBundleManager.getPosition("ENVIRONMENTSIZE_Y"));
		pane.setStyle("-fx-border-color: black");
		createControllers();
		setUpControllers();
		addLibrary();
	}
	
	private void createControllers() {
		controllerList = new ArrayList<>();
//		//TODO:
//		gameViewWindowController = new GameViewWindowController(game);
		levelPanelController = new LevelPanelController(game.getSceneManager(), imageManager, audioManager);
//		objectInfoPanelController = new ObjectInfoPanelController(game);
//		controllerList.add(gameViewWindowController);
//		controllerList.add(objectInfoPanelController);
		controllerList.add(levelPanelController);
		gameViewWindowController = levelPanelController.getGameViewWindowController();
		objectInfoPanelController = levelPanelController.getObjectInfoPanelController();
		audioController = levelPanelController.getAudioController();
		controllerList.add(gameViewWindowController);
		controllerList.add(objectInfoPanelController);
		controllerList.add(audioController);
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
	
	private void addLibrary() {
		gameObjectManager = new GameObjectManager(levelPanelController, imageManager);
		gameObjectManager.addToGUI(pane);
		levelPanelController.getObjectInfoPanelController().getObjectInfoPanelRefactored().setLibraryObservable(gameObjectManager);
		gameObjectManager.setObserver(levelPanelController.getObjectInfoPanelController().getObjectInfoPanelRefactored());
	}

	
	public Pane getGUI() {
		return pane;	
	}
	
}
