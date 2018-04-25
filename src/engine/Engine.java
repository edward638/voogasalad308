
package engine;

import java.util.ArrayList;
import java.util.List;

import engine.events.elementevents.KeyInputEvent;
import engine.events.elementevents.MouseInputEvent;
import engine.events.elementevents.TimeEvent;
import engine.tests.ModelGamePart1;
import engine.tests.ModelGamePart2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Engine implements EngineInterface{
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final double SUBSCENE_WIDTH =  900;
    public static final double SUBSCENE_HEIGHT = 590;
	
	private Timeline animation;
	private GameState currentGameState;
	private DisplayState displayState;
	private EventManager2 eventManager;
	private GameMetaData gameMetaData;
	
	public Engine(String gameName) {
		currentGameState = new GameState(gameName);
		
		displayState = new DisplayState(currentGameState, gameName);
		eventManager = new EventManager2(currentGameState);
		gameMetaData = new GameMetaData(currentGameState);
		startAnimation();
	}
	
	private void startAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> timeStep(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
	
	private void timeStep (double elapsedTime) {
		double gameSteps = elapsedTime * currentGameState.getGameSpeed();
    	eventManager.processElementEvent(new TimeEvent(gameSteps));
    	displayState.update(currentGameState);
    }
	
	@Override
	public void close() {
		currentGameState.getAudioManager().stop();
	}
	
	@Override
	public SubScene getDisplay() {
		SubScene engineSubScene = new SubScene(displayState.getSubSceneRoot(), SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
		return engineSubScene;
	}

	@Override
	public void handleKeyInput(KeyCode code) {
		eventManager.processElementEvent(new KeyInputEvent(code));
	}
	
	@Override
	public void handleMouseInput(double x, double y) {
		eventManager.processElementEvent(new MouseInputEvent(x,y));
	}
	
	@Override
	public void setVolume(double newVolume) {
		currentGameState.getAudioManager().setVolume(newVolume);
	}

	@Override
	public void pause() {
		animation.pause();
	}

	@Override
	public void play() {
		animation.play();
	}

	@Override
	public GameMetaData getGameMetaData() {
		return gameMetaData;
	}
}
