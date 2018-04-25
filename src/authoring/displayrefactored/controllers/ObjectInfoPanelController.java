package authoring.displayrefactored.controllers;

import authoring.AuthBehavior;
import authoring.Game;
import authoring.GameObject;
import authoring.GameScene;
import authoring.SceneBackgroundImageSerializable;
import authoring.displayrefactored.authoringuicomponents.ObjectInfoPanelRefactored;
import authoring.displayrefactored.authoringuicomponents.ObjectListPanelRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class ObjectInfoPanelController extends Controller {

	GameScene gameScene;
	ObjectInfoPanelRefactored objectInfoPanelRefactored;
	ObjectListPanelRefactored objectListPanelRefactored;
	
	public ObjectInfoPanelController(GameScene gameScene) {
		// TODO Auto-generated constructor stub
		this.gameScene = gameScene;
	}
	
	@Override
	protected void initializeScreenComponents() {
		objectInfoPanelRefactored = new ObjectInfoPanelRefactored(this);
		objectListPanelRefactored = new ObjectListPanelRefactored(this);
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
		objectInfoPanelRefactored.AttachToPane(pane, infoX, infoY);
		objectListPanelRefactored.AttachToPane(pane, listX, listY);
	}
	
	@Override
	protected void refreshView() {
	}

	public void updatePositions() {
		refreshView();
	}
	
	public void addGameObject(String name, Double xPos, Double yPos, String imageName, Image image) {
		game.getImageManager().storeImage(imageName, image);
		GameObject gameObject = new GameObject();
		AuthBehavior mandatory = gameObject.getMandatoryBehavior();
		gameObject.setName(name);
		mandatory.getProperty("xPos").setValue(xPos);
		mandatory.getProperty("yPos").setValue(yPos);
		mandatory.getProperty("imagePath").setValue(imageName);
		game.getSceneManager().getCurrentScene().getMyObjects().add(gameObject);
		setCurrentGameObject(gameObject);
		refreshView();
	}
	
	public void setCurrentGameObject(GameObject gameObject) {
		game.notifyObjectInfoObservers(gameObject);
	}
	
	public void addBackgroundImage(Image image) {
		SceneBackgroundImageSerializable serializable = new SceneBackgroundImageSerializable(0.0, 0.0, 200.0, 200.0, game.getImageManager().storeBackgroundImage(image)+".png");
		game.getSceneManager().getCurrentScene().addBackgroundImageSerializable(serializable);
//		System.out.println(serializable.getImagePath());
		refreshView();
	}
	
	public void deleteGameObject(int index) {
		game.getGameObjects().remove(index);
		refreshView();
	}
	
	public boolean checkUniqueName(String nameText) {
		boolean isUniqueName = game.checkUniqueObjectNames(nameText);
		return isUniqueName;
	}
	
	public void duplicateGameObject() {
//		game.duplicateGameObject();
	}

}
