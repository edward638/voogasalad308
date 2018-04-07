package GamePlayer;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ConcreteGamePlayer implements GamePlayer {

	Scene myScene;
	Stage myStage;
	Button saveButton;
	Button loadButton;
	HUD hud;
	ConcreteHighScores highScores;
	
	private final static double SCREEN_HEIGHT = 300;
	private final static double SCREEN_WIDTH = 500;// 915;
	private Group root;
	private final static Paint BACKGROUND = Color.ANTIQUEWHITE;

	public ConcreteGamePlayer(Stage stage) {
		root = new Group();
		myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
		myStage = stage;
		myStage.setScene(myScene);
		
		highScores = new ConcreteHighScores("hi");
		highScores.addScore("bfd", 4);
		highScores.printQ();
		root.getChildren().add(highScores.getScores());
		highScores.addScore("43", 9);


		setupButtons();
		initializeEngine();
		hud = new ConcreteHUD();
		root.getChildren().add((Node) hud);
		
	}
	

	private void initializeEngine() {
		// TODO Auto-generated method stub

	}

	private void setupButtons() {
		saveButton = new SaveButton(30, 30, 30, 30);
		root.getChildren().add(saveButton);

		loadButton = new LoadButton(60, 60, 60, 60, myStage);
		root.getChildren().add(loadButton);
		
		

	}

	@Override
	public void playGame(String game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public Scene getScene() {
		return myScene;
	}

}
