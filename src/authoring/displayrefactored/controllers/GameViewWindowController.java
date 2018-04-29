package authoring.displayrefactored.controllers;

import java.awt.image.RenderedImage;

import authoring.GameObject;
import authoring.GameScene;
import authoring.SceneBackgroundImage;
import authoring.SceneBackgroundImageSerializable;
import authoring.ViewRefreshInterface;
import authoring.displayrefactored.authoringuicomponents.GameViewWindowRefactored;
import data.ImageManager;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Edward Zhuang
 *
 */
public class GameViewWindowController extends Controller implements ViewRefreshInterface {
	
	private GameScene gameScene;
	private GameViewWindowRefactored gameViewWindowRefactored;
	
	public GameViewWindowController(GameScene gameScene, ImageManager imageManager) {
		// TODO Auto-generated constructor stub
		super(imageManager);
		this.gameScene = gameScene;
	}

	@Override
	protected void initializeScreenComponents() {
		// TODO Auto-generated method stub
		gameViewWindowRefactored = new GameViewWindowRefactored(this);
	}

	@Override
	protected void setUpConnections() {
		gameScene.addObserver(gameViewWindowRefactored);
	}

	@Override
	protected void addToGUI(Pane pane) {
		// TODO Auto-generated method stub 
		int x = ResourceBundleManager.getPosition("GAMEVIEWWINDOW_X");
		int y = ResourceBundleManager.getPosition("GAMEVIEWWINDOW_Y");
		gameViewWindowRefactored.attachToPane(pane, x, y);
	}
	
	public void storeBackgroundImage(RenderedImage ri) {
		getImageManager().storeCompositeBackgroundImage(gameScene.getName()+"backgroundimage.png", ri);
	}

	public SceneBackgroundImage getBackgroundImage(SceneBackgroundImageSerializable s) {
		return new SceneBackgroundImage(getImageManager().getBackgroundImage(s.getImagePath()), s);
	}
	
	@Override
	protected void refreshView() {
	}
	
	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
		setUpConnections();
		gameScene.notifyMyObservers();
	}

	@Override
	public void backupGameScene() {
		gameScene.backupGameScene();
	}
	
	@Override
	public void notifyObjectInfoObservers(GameObject gameObject) {
		// TODO Auto-generated method stub
		gameScene.setCurrentGameObject(gameObject);
		gameScene.notifyMyObservers();
	
	}
	
	

}
