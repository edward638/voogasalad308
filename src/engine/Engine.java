package engine;

import java.util.Arrays;
import java.util.List;

import data.GameLoader;
import engine.behaviors.MainCharacter;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.KeyInputEvent;
import engine.events.elementevents.MouseInputEvent;
import engine.events.elementevents.TimeEvent;
import engine.tests.ModelGameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Engine {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final double SUBSCENE_WIDTH =  900;
    public static final double SUBSCENE_HEIGHT = 590;
	
	private Timeline animation;
	private SubScene engineSubScene;
	private Group subSceneRoot = new Group();
	private GameState gameState;
	private DisplayState displayState;
	private EventManager2 eventManager;
	private String musicPath = "data/music/WiiShopChannelMusic.mp3";
	private AudioPlayer audioPlayer;
	
	
	/**
	 * NEW way to instantiate engine with a MetaData object
	 * @param gamePath
	 */
	public Engine(GameMetaData metaData) {
		gameState.setState(metaData.getCurrentLevel());
		displayState = new DisplayState(metaData.getGameName(), gameState);
		eventManager = new EventManager2(gameState);
		audioPlayer = new AudioPlayer(musicPath);
		startAnimation();
	}
	
	public Engine(String gamePath) {
		ModelGameState modelGameState = new ModelGameState(); 
		gameState = modelGameState.getState();
		displayState = modelGameState.getDisplay();
		eventManager = new EventManager2(gameState);
		audioPlayer = new AudioPlayer(musicPath);
		startAnimation();
	}
	
	public Engine(GameState g) {
		gameState = g;
		displayState = new DisplayState("enginetestmario", g);
		eventManager = new EventManager2(gameState);
		audioPlayer = new AudioPlayer(musicPath);
		startAnimation();
	}
	
	public void close() {
		audioPlayer.stop();
	}
	
	public SubScene getDisplay() {
		engineSubScene = new SubScene(subSceneRoot, SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
		return engineSubScene;
	}
	
	private void startAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> timeStep(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

	public void handleKeyInput(KeyCode code) {
		eventManager.processElementEvent(new KeyInputEvent(code));
	}
	
	public void handleMouseInput(double x, double y) {
		eventManager.processElementEvent(new MouseInputEvent(x,y));
	}
	
	public void timeStep (double elapsedTime) {
		double gameSteps = elapsedTime*gameState.getGameSpeed();
		gameState.incrementGameTime(gameSteps);
	    	eventManager.processElementEvent(new TimeEvent(gameSteps));
	    	displayState.updateImageElements(scrollingAroundMainCharacter(gameState));
	    	displayState.update(gameState);
	    	updateDisplay(displayState.newElements, displayState.removeElements);
    }

	protected void updateDisplay(List<ImageElement> newElements, List<ImageElement> removeElements) {
		for (ImageView e:newElements) {
			subSceneRoot.getChildren().add(e);
		}
		newElements.clear();
		
		for (ImageView e:removeElements) {
			subSceneRoot.getChildren().remove(e);
		}
		removeElements.clear();
	}
	
	private List<Double> scrollingAroundMainCharacter(GameState gameState) {
		List<Double> offset = Arrays.asList(0.0,0.0);
		for (GameElement e: gameState.getElements()) {
			if (e.hasBehavior(MainCharacter.class)) {
			
				MainCharacter mc_props = (MainCharacter) e.getBehavior(MainCharacter.class);
				offset = mc_props.getImageViewOffset(SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
			}
		}
		return offset;
	}
	
	
}
