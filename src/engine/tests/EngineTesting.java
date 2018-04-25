package engine.tests;

import java.util.ArrayList;
import java.util.List;

import engine.Engine;
import engine.GameMetaData;
import engine.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EngineTesting extends Application {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.WHITE;
	private ParallelCamera vcp;

	
	//private Timeline animation;
	
	private Engine gameEngine;
	private GameMetaData modelGameMetaData;

	public static void main(String[] args) {
		Application.launch(args);
	} 

	@Override
	public void start(Stage stage) throws Exception {
		List<GameState> levels = new ArrayList<GameState>();
		levels.add(new ModelGameState3().getState());
		levels.add(new ModelGameState2().getState());
		modelGameMetaData = new GameMetaData(levels, 0, new ModelGameState().getState(), "enginetestmario");
		gameEngine = new Engine(modelGameMetaData);
		stage.setScene(setupLevel(900, 590, BACKGROUND));
		stage.show();
		startAnimation();
	}
	
	private Scene setupLevel (int width, int height, Paint background) {
		Group root = new Group();
		Scene scene = new Scene(root, width, height, background);
		SubScene gameSubScene = gameEngine.getDisplay();
		root.getChildren().add(gameSubScene);
		scene.setOnKeyPressed(e -> gameEngine.handleKeyInput(e.getCode()));
        scene.setOnMouseClicked(e -> gameEngine.handleMouseInput(e.getX(), e.getY())); 
    	return scene;
    }

	private void startAnimation() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> gameEngine.timeStep(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
}
