package authoring.displayrefactored.controllers;

import java.io.File;

import authoring.GameScene;
import authoring.displayrefactored.authoringuicomponents.AudioPanel;
import data.AudioManager;
import data.ImageManager;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.layout.Pane;

public class AudioController extends Controller {

	private GameScene gameScene;
	private AudioPanel audioPanel;
	private AudioManager audioManager;
	
	public AudioController(GameScene gameScene, ImageManager imageManager, AudioManager audioManager) {
		super(imageManager);
		this.gameScene = gameScene;
		this.audioManager = audioManager;
		initializeScreenComponents();
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
	
	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
		setUpConnections();
		gameScene.notifyMyObservers();
	}
	
	public void setAudio(File audio) {
		System.out.println(audio.getName());
		audioManager.storeAudio(audio);
		gameScene.setAudioName(audio.getName());
	}

}
