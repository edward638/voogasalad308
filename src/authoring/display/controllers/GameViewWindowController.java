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
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Edward Zhuang
 *
 */
public class GameViewWindowController extends Controller implements ViewRefreshInterface {
	
	private GameScene gameScene;
	private GameViewWindow gameViewWindowRefactored;
	
	public GameViewWindowController(GameScene gameScene, ImageManager imageManager) {
		// TODO Auto-generated constructor stub
		super(imageManager);
		this.gameScene = gameScene;
	}

	@Override
	protected void initializeScreenComponents() {
		// TODO Auto-generated method stub
		gameViewWindowRefactored = new GameViewWindow(this);
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
	
//	public void storeBackgroundImage(RenderedImage ri) {
//		getImageManager().storeCompositeBackgroundImage(gameScene.getName().replaceAll("\\s", "")+"backgroundimage.png", ri);
//		gameScene.setBackgroundImageName();
//	}

	public void storeBackgroundImage(Pane pane) {
		System.out.println(pane.getWidth() + " " + pane.getHeight());
		WritableImage wi = new WritableImage((int) pane.getWidth(), (int) pane.getHeight());
		pane.snapshot(new SnapshotParameters(), wi);
		RenderedImage ri = SwingFXUtils.fromFXImage(wi, null);
		getImageManager().storeCompositeBackgroundImage(gameScene.getName().replaceAll("\\s", "")+"backgroundimage.png", ri);
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
