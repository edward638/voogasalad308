package engine.tests;

import engine.Engine;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EngineTesting extends Application {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.WHITE;
	
	//private Timeline animation;
	
	private Engine gameEngine;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		gameEngine = new Engine("");
		stage.setScene(setupLevel(900, 590, BACKGROUND));
		stage.show();
		
		startAnimation();
	}
	
	private Scene setupLevel (int width, int height, Paint background) {
		Group root = new Group();
		Scene scene = new Scene(root, width, height, background);
		
		root.getChildren().add(gameEngine.getDisplay());
		
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
