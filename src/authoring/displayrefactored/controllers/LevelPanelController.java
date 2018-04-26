package authoring.displayrefactored.controllers;


import org.codehaus.groovy.runtime.metaclass.MethodMetaProperty.GetBeanMethodMetaProperty;

import authoring.AuthBehavior;
import authoring.Game;
import authoring.GameObject;
import authoring.GameScene;
import authoring.SceneBackgroundImageSerializable;
import authoring.SceneManager;
import authoring.displayrefactored.authoringuicomponents.GameViewWindowRefactored;
import authoring.displayrefactored.authoringuicomponents.LevelPanelRefactored;
import data.ImageManager;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Edward Zhuang
 *
 */
public class LevelPanelController extends Controller {

	SceneManager sceneManager;
	LevelPanelRefactored levelPanelRefactored;
	ObjectInfoPanelController objectInfoPanelController;
	GameViewWindowController gameViewWindowController;
	
	private static final String MANDATORY_BEHAVIOR_NAME = "MandatoryBehavior";
	
	public LevelPanelController(SceneManager sceneManager, ImageManager imageManager) {
		super(imageManager);
		this.sceneManager = sceneManager;
		objectInfoPanelController = new ObjectInfoPanelController(this.sceneManager.getCurrentScene(),getImageManager());
		System.out.println("this is current scene: " + this.sceneManager.getCurrentScene());
		gameViewWindowController = new GameViewWindowController(this.sceneManager.getCurrentScene(),getImageManager());
	}
	
	public ObjectInfoPanelController getObjectInfoPanelController() {
		return objectInfoPanelController;
	}
	
	public GameViewWindowController getGameViewWindowController() {
		return gameViewWindowController;
	}
	
	@Override
	protected void initializeScreenComponents() {
		levelPanelRefactored = new LevelPanelRefactored(this);
	}

	@Override
	protected void setUpConnections() {
		sceneManager.addObserver(levelPanelRefactored);
		System.out.println("setupconnections");
	}

	@Override
	protected void addToGUI(Pane pane) {
		int x = ResourceBundleManager.getPosition("LEVELPANEL_X");
		int y = ResourceBundleManager.getPosition("LEVELPANEL_Y");
		levelPanelRefactored.AttachToPane(pane, x, y);	
	}
	
	@Override
	protected void refreshView() {
		
	}
	
	public void addLevel(String name, int level) {
		GameScene scene = sceneManager.makeScene(name, level);
		setLevel(scene);
//		levelPanelRefactored.updateLevelDropdown(level - 1, scene);
	}
	
	public void setLevel(GameScene gameScene) {
		sceneManager.setCurrentScene(gameScene);
		updateMyControllers(gameScene);
	}
	
	private void updateMyControllers(GameScene gameScene) {
		objectInfoPanelController.setGameScene(gameScene);
		gameViewWindowController.setGameScene(gameScene);
	}
}
