
package engine;

import java.util.HashMap;
import java.util.Map;
import engine.behaviors.TimeTracker;
import engine.events.elementevents.KeyInputEvent;
import engine.events.elementevents.MouseInputEvent;
import engine.events.elementevents.TimeEvent;
import gamePlayer.PlayerUpdater;
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
	private PlayerUpdater playerUpdater;
	
	public Engine(String gameName, boolean newGame, PlayerUpdater playerUpdater) {
		currentGameState = new GameState(gameName);
		displayState = new DisplayState(currentGameState, gameName);
		eventManager = new EventManager2(currentGameState);
		gameMetaData = new GameMetaData(currentGameState);
		this.playerUpdater = playerUpdater;
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

    	Map<String, Object> info = new HashMap<>();
    	//GameElement mainCharacter = currentGameState.getCurrentGamePart().getMainCharacter();
    	//info.put("Name", mainCharacter.getIdentifier());
    	//info.put("Health", ((Killable)mainCharacter.getBehavior(Killable.class)).getHealth());
    	//info.put("Game Time", ((TimeTracker)mainCharacter.getBehavior(TimeTracker.class)).getTimePassed());
    	//playerUpdater.updateHUD(info);
    }
	
	@Override
	public void close() {
		//playerUpdater.addHighScore((int) ((TimeTracker)currentGameState.getCurrentGamePart().getMainCharacter().getBehavior(TimeTracker.class)).getTimePassed());
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
