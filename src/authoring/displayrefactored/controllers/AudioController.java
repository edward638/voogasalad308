package authoring.displayrefactored.controllers;

import authoring.GameScene;
import authoring.displayrefactored.authoringuicomponents.AudioPanel;
import data.ImageManager;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.layout.Pane;

public class AudioController extends Controller {

	private GameScene gameScene;
	private AudioPanel audioPanel;
	
	public AudioController(GameScene gameScene, ImageManager imageManager) {
		super(imageManager);
		this.gameScene = gameScene;
	}

	@Override
	protected void initializeScreenComponents() {
		// TODO Auto-generated method stub
		audioPanel = new AudioPanel(this);
	}

	@Override
	protected void setUpConnections() {
		// TODO Auto-generated method stub
		gameScene.addObserver(audioPanel);
	}

	@Override
	protected void addToGUI(Pane pane) {
		// TODO Auto-generated method stub
		int x = ResourceBundleManager.getPosition("AUDIOPANEL_X");
		int y = ResourceBundleManager.getPosition("AUDIOPANEL_Y");
		audioPanel.attachToPane(pane, x, y);
	}

	@Override
	protected void refreshView() {
		// TODO Auto-generated method stub
		
	}
	
	public void setAudio(String audioPath) {
		
	}

}
