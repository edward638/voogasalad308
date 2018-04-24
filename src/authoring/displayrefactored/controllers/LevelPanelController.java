package authoring.displayrefactored.controllers;


import authoring.AuthBehavior;
import authoring.Game;
import authoring.GameObject;
import authoring.GameScene;
import authoring.SceneBackgroundImageSerializable;
import authoring.displayrefactored.AuthoringEnvironmentGUIRefactored;
import authoring.displayrefactored.authoringuicomponents.GameViewWindowRefactored;
import authoring.displayrefactored.authoringuicomponents.LevelPanelRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class LevelPanelController extends Controller {

	Game game;
	LevelPanelRefactored levelPanelRefactored;
	
	private static final String MANDATORY_BEHAVIOR_NAME = "MandatoryBehavior";
	
	public LevelPanelController(Game game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}
	
	@Override
	protected void initializeScreenComponents() {
		// TODO Auto-generated method stub
		levelPanelRefactored = new LevelPanelRefactored(this);
	}
	

	@Override
	protected void setUpConnections() {
		// TODO Auto-generated method stub
		game.addObserver(levelPanelRefactored);
	}

	@Override
	protected void addToGUI(Pane pane) {
		// TODO Auto-generated method stub
		int x = ResourceBundleManager.getPosition("LEVELPANEL_X");
		int y = ResourceBundleManager.getPosition("LEVELPANEL_Y");
		levelPanelRefactored.AttachToPane(pane, x, y);
		
	}
	
	public void addLevel(String name, int level) {
		GameScene scene = game.getSceneManager().makeScene(name, level);
		game.getSceneManager().setCurrentScene(scene);
		levelPanelRefactored.updateLevelDropdown(level - 1, scene);
		refreshView();
	}
	
	public void addGameObject(String name, Double xPos, Double yPos, String imageName, Image image) {
		game.getImageManager().storeImage(imageName, image);
		GameObject gameObject = new GameObject();
		AuthBehavior mandatory = gameObject.getMandatoryBehavior();
		gameObject.setName(name);
//		xPos = 0.0;
//		yPos = 0.0;
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
	
	public void setLevel(GameScene gameScene) {
		game.getSceneManager().setCurrentScene(gameScene);
		refreshView();
	}
	
	public void addBackgroundImage(Image image) {
		SceneBackgroundImageSerializable serializable = new SceneBackgroundImageSerializable(0.0, 0.0, 200.0, 200.0, game.getImageManager().storeBackgroundImage(image)+".png");
		game.getSceneManager().getCurrentScene().addBackgroundImageSerializable(serializable);
//		System.out.println(serializable.getImagePath());
		refreshView();
	}
	
	public void saveImage() {
		
	}
	
	public void deleteGameObject(int index) {
		game.getGameObjects().remove(index);
		refreshView();
	}

	@Override
	protected void refreshView() {
		// TODO Auto-generated method stub
		game.notifyMyObservers();
	}

	public boolean checkUniqueName(String nameText) {
		boolean isUniqueName = game.checkUniqueObjectNames(nameText);
		return isUniqueName;
	}
	
}
