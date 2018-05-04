package authoring.display.controllers;

import java.awt.image.RenderedImage;

import authoring.GameObject;
import authoring.GameScene;
import authoring.SceneBackgroundImage;
import authoring.SceneBackgroundImageSerializable;
import authoring.ViewRefreshInterface;
import authoring.display.authoringuicomponents.GameViewWindow;
import data.ImageManager;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Edward Zhuang
 * Controller class for GameViewWindow. Allows GameViewWindow to connect with the current GameScene.
 */
public class GameViewWindowController extends Controller implements ViewRefreshInterface {

	private static final String GAMEVIEWWINDOW_X = "GAMEVIEWWINDOW_X";
	private static final String GAMEVIEWWINDOW_Y = "GAMEVIEWWINDOW_Y";
	private GameScene gameScene;
	private GameViewWindow gameViewWindowRefactored;
	
	public GameViewWindowController(GameScene gameScene, ImageManager imageManager) {
		super(imageManager);
		this.gameScene = gameScene;
	}

	@Override
	protected void initializeScreenComponents() {
		gameViewWindowRefactored = new GameViewWindow(this);
	}

	@Override
	protected void setUpConnections() {
		gameScene.addObserver(gameViewWindowRefactored);
	}

	@Override
	protected void addToGUI(Pane pane) {
		int x = ResourceBundleManager.getPosition(GAMEVIEWWINDOW_X);
		int y = ResourceBundleManager.getPosition(GAMEVIEWWINDOW_Y);
		gameViewWindowRefactored.attachToPane(pane, x, y);
	}

	/**
	 * Stores compositeBackgroundImage into game files through ImageManager and updates GameScene's background image path
	 * @param ri RenderedImage
	 */
	public void storeBackgroundImage(RenderedImage ri) {
		getImageManager().storeCompositeBackgroundImage(gameScene.getName().replaceAll("\\s", "")+"backgroundimage.png", ri);
		gameScene.setBackgroundImageName();
	}

	/**
	 * Gets a SceneBackgroundImage from a serializable form.
	 * @param s SceneBackgroundImageSerializable
	 * @return SceneBackgroundImage
	 */
	public SceneBackgroundImage getBackgroundImage(SceneBackgroundImageSerializable s) {
		return new SceneBackgroundImage(getImageManager().getBackgroundImage(s.getImagePath()), s);
	}
	
	@Override
	protected void refreshView() {
	}

	/**
	 * sets up observer/observable interactions between new GameScene. Used when the current GameScene switches.
	 * @param gameScene new gameScene
	 */
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
		gameScene.setCurrentGameObject(gameObject);
		gameScene.notifyMyObservers();
	
	}
	
	

}
