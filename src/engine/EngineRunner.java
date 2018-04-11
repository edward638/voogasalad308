package engine;

import java.awt.Point;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import authoring.GameObject;
import authoring.GameScene;
import engine.events.elementevents.ElementEvent;
import engine.events.elementevents.KeyInputEvent;
import engine.events.elementevents.MouseInputEvent;
import engine.events.elementevents.TimeEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.util.Duration;


public class EngineRunner {
    private Timeline animation;
    private Group root;
    private GameState gameState;
    private DisplayState displayState;
    private EventManager eventManager;
    private int level = 0;
    
	public static final int FRAMES_PER_SECOND = 60;
	
    //starts animation
	
	/**
	 * Create a new engine runner, which is used to run the engine for a certain game.
	 * 
	 * @param gameName 	Name of the game to run
	 */
	public EngineRunner(String gameName) {
		GameLoader loader = new GameLoader(gameName);
		initializeScene(loader);
		displayState = new DisplayState();
		eventManager = new EventManager();
	}
	
	private void initializeScene(GameLoader loader) {
		List<GameScene> scene = loader.getGameScene();
		List<GameObject> objects = scene.get(0).getMyElements();
		List<GameElement> gameElements = objects.stream()
				.map(o -> toGameElement(o))
				.collect(Collectors.toList());
	}
	
	/** Translate an authoring GameObject to an engine GameElement
	 * 
	 * @param object 	Authoring GameObject to be translated
	 * @return			GameElement representing the translated GameObject
	 */
	private GameElement toGameElement(GameObject object) {
		GameElement element = new GameElement();
		object.getBehaviors().stream().map(b -> element.addBehavior(b));
	}
	
    public void startAnimation() {
    	animation = new Timeline();
    	root = new Group();
    	gameState = new GameState();
        animation.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(1000/FRAMES_PER_SECOND),
                    e -> timeStep(1.0/FRAMES_PER_SECOND));
        animation.getKeyFrames().add(frame);
        animation.play();    
    }
    
    
    private Scene setupLevel (int width, int height, Paint background, String textfileName) {
    	Scene curr_scene = new Scene(root);
        curr_scene.setOnKeyPressed(e -> {
			try {
				handleKeyInput(e.getCode());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
        curr_scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY())); 
    	return curr_scene;

    }

	private void timeStep (double elapsedTime) {
		double gameSteps = elapsedTime*gameState.getGameSpeed();
		gameState.incrementGameTime(gameSteps);
    	eventManager.processInputEvent(new TimeEvent(gameSteps), gameState);
    	displayState.updateImageElements();
    	engine.updateDisplay(displayState);
    	
    }
	
	public List<ElementEvent> handleKeyInput(KeyCode code) {
		eventManager.processInputEvent(new KeyInputEvent(code), gameState);
	}
	
    private Object handleMouseInput(double x, double y) {
		eventManager.processInputEvent(new MouseInputEvent(x,y), gameState);
	}
	

}
