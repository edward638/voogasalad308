package authoring.displayrefactored.controllers;


import authoring.Behavior;
import authoring.Game;
import authoring.GameObject;
import authoring.GameScene;
import authoring.displayrefactored.AuthoringEnvironmentGUIRefactored;
import authoring.displayrefactored.authoringuicomponents.LevelPanelRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.image.Image;

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
	protected void addToGUI(AuthoringEnvironmentGUIRefactored gui) {
		// TODO Auto-generated method stub
		int x = ResourceBundleManager.getPosition("LEVELPANEL_X");
		int y = ResourceBundleManager.getPosition("LEVELPANEL_Y");
		levelPanelRefactored.AttachToPane(gui.getPane(), x, y);
	}
	
	public void addLevel(String name, int level) {
		game.getSceneManager().makeScene(name, level);
	}
	
	public void addGameObject(String name, Double xPos, Double yPos, String imageName) {
		GameObject gameObject = new GameObject(MANDATORY_BEHAVIOR_NAME);
		Behavior mandatory = gameObject.getBehavior(MANDATORY_BEHAVIOR_NAME);
		mandatory.getProperty("elementName").setValue(name);
		mandatory.getProperty("xPos").setValue(xPos);
		mandatory.getProperty("yPos").setValue(yPos);
		mandatory.getProperty("imagePath").setValue(imageName);
		game.getSceneManager().getCurrentScene().getMyObjects().add(gameObject);
	}
	
	public void setLevel(GameScene gameScene) {
		game.getSceneManager().setCurrentScene(gameScene);
	}
	
	public void addBackgroundImage(Image image) {
		game.getSceneManager().getCurrentScene().getSceneBackground().addImage(image);
	}
	
}
