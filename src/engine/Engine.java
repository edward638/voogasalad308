package engine;

import java.util.Arrays;
import java.util.List;

import data.GameLoader;
import engine.behaviors.MainCharacter;
import engine.behaviors.MandatoryBehavior;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.KeyInputEvent;
import engine.events.elementevents.MouseInputEvent;
import engine.events.elementevents.TimeEvent;
import engine.tests.ModelGameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Engine /*extends Application*/ {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final double SUBSCENE_WIDTH =  900;
    public static final double SUBSCENE_HEIGHT = 590;
	/*public static final Paint BACKGROUND = Color.WHITE;*/
	
	private Timeline animation;
	
	private SubScene engineSubScene;
	private Group subSceneRoot = new Group();
	private GameState gameState;
	private DisplayState displayState;
	private EventManager2 eventManager;
	private ParallelCamera viewingCamera;
	
	private String musicPath = "data/music/WiiShopChannelMusic.mp3";
	
	public Engine(String gamePath) {
		//EngineRunner engineRunner = new EngineRunner(gamePath);
		GameLoader loader = new GameLoader(gamePath);
		
		ModelGameState modelGameState = new ModelGameState(); 
		gameState = modelGameState.getState();
		displayState = modelGameState.getDisplay();
		eventManager = new EventManager2(gameState, this);
		
		new AudioPlayer(musicPath);
		startAnimation();
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

	public List<ElementEvent> handleKeyInput(KeyCode code) {
		eventManager.processElementEvent(new KeyInputEvent(code));
		return null;
	}
	
	public Object handleMouseInput(double x, double y) {
		eventManager.processElementEvent(new MouseInputEvent(x,y));
		return null;
	}
	
	public void timeStep (double elapsedTime) {
		double gameSteps = elapsedTime*gameState.getGameSpeed();
		gameState.incrementGameTime(gameSteps);
    	eventManager.processElementEvent(new TimeEvent(gameSteps));
    	displayState.updateImageElements(scrollingAroundMainCharacter(gameState));
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
