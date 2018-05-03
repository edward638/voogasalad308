package authoring.display.controllers;

import java.util.List;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.GameScene;
import authoring.SceneBackgroundImageSerializable;
import authoring.display.authoringuicomponents.ObjectInfoPanel;
import authoring.display.authoringuicomponents.ObjectListPanel;
import data.ImageManager;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


/**
 * 
 * @author Edward Zhuang
 *
 */
public class ObjectInfoPanelController extends Controller implements ObjectInfoSaver {

	GameScene gameScene;
	ObjectInfoPanel objectInfoPanelRefactored;
	ObjectListPanel objectListPanelRefactored;
	
	public ObjectInfoPanelController(GameScene gameScene, ImageManager imageManager) {
		// TODO Auto-generated constructor stub
		super(imageManager);
		this.gameScene = gameScene;
	}
	
	public void setGameScene(GameScene gameScene) {
		this.gameScene.deleteObservers();
		this.gameScene = gameScene;
		setUpConnections();
		refreshView();
	}
	
	public ObjectInfoPanel getObjectInfoPanelRefactored() {
		return objectInfoPanelRefactored;
	}
	
	@Override
	protected void initializeScreenComponents() {
		objectInfoPanelRefactored = new ObjectInfoPanel(this);
		objectListPanelRefactored = new ObjectListPanel(this);
	}

	@Override
	protected void setUpConnections() {
		gameScene.addObserver(objectInfoPanelRefactored);
		gameScene.addObserver(objectListPanelRefactored);
	}

	@Override
	protected void addToGUI(Pane pane) {
		int infoX = ResourceBundleManager.getPosition("OBJECTINFO_X");
		int infoY = ResourceBundleManager.getPosition("OBJECTINFO_Y");
		int listX = ResourceBundleManager.getPosition("OBJECTLIST_X");
		int listY = ResourceBundleManager.getPosition("OBJECTLIST_Y");
		objectInfoPanelRefactored.attachToPane(pane, infoX, infoY);
		objectListPanelRefactored.attachToPane(pane, listX, listY);
	}
	
	@Override
	protected void refreshView() {
		gameScene.notifyMyObservers();
	}

	@Override
	public void updatePositions() {
		refreshView();
	}
	
	public void addGameObject(String name, Double xPos, Double yPos, String imageName, Image image) {
		getImageManager().storeImage(imageName, image);
		GameObject gameObject = new GameObject();
		AuthBehavior mandatory = gameObject.getMandatoryBehavior();
		gameObject.setName(name);
		mandatory.getProperty("xPos").setValue(xPos);
		mandatory.getProperty("yPos").setValue(yPos);
		mandatory.getProperty("imagePath").setValue(imageName);
		gameScene.getMyObjects().add(gameObject);
		setCurrentGameObject(gameObject);
	}
	
	public void setCurrentGameObject(GameObject gameObject) {
		gameScene.setCurrentGameObject(gameObject);
	}
	
	public void addBackgroundImage(Image image) {
		SceneBackgroundImageSerializable serializable = new SceneBackgroundImageSerializable(0.0, 0.0, 200.0, 200.0, getImageManager().storeBackgroundImage(image)+".png");
		gameScene.addBackgroundImageSerializable(serializable);
	}

	
	public void deleteGameObject(int index) {
		gameScene.getMyObjects().remove(index);
	}
	
	public boolean checkUniqueName(String nameText) {
		boolean isUniqueName = gameScene.checkUniqueObjectNames(nameText);
		return isUniqueName;
	}
	
	@Override
	public void duplicateGameObject() {
		gameScene.duplicateGameObject();
	}
	@Override
	public List<GameObject> getAllGameObjects() {
		return gameScene.getMyObjects();
	}
	public void restorePreviousGameScene() {
		gameScene.restorePreviousGameScene();
	}

	@Override
	public void setImage(Image image, String imageName) {
		// TODO Auto-generated method stub
		getImageManager().storeImage(imageName, image);
	}

}
