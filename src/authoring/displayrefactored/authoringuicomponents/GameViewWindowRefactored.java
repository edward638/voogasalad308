package authoring.displayrefactored.authoringuicomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoring.GameObject;
import authoring.GameViewObservable;
import authoring.SceneBackground;
import authoring.displayrefactored.controllers.GameViewWindowController;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameViewWindowRefactored extends AuthoringUIComponentRefactored implements Observer {

	private List<ImageView> objectImageViews;
	private StackPane stackPane;
	private Pane backgroundPane;
	private Pane foregroundPane;
	private GameViewWindowController controller;
	private GameViewObservable gameViewObservable = null;
	
	public GameViewWindowRefactored(GameViewWindowController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		objectImageViews = new ArrayList<>();
		
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		stackPane = new StackPane();
		backgroundPane = new Pane();
		foregroundPane = new Pane();
		stackPane.setStyle("-fx-border-color: black");
		stackPane.setPrefSize(ResourceBundleManager.getPosition("GAMEVIEWSIZE_X"), ResourceBundleManager.getPosition("GAMEVIEWSIZE_Y"));
		borderPane.setCenter(stackPane);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		gameViewObservable = (GameViewObservable) o;
		updateForeground(gameViewObservable.getImageViews());
		updateBackground(gameViewObservable.getSceneBackgroundPane());
	}
	
	private void updateForeground(List<ImageView> list) {
		foregroundPane.getChildren().clear();
		foregroundPane.getChildren().addAll(list);
	}
	
	private void updateBackground(Pane pane) {
		stackPane.getChildren().clear();
		backgroundPane = pane;
		stackPane.getChildren().add(backgroundPane);
		stackPane.getChildren().add(foregroundPane);
	}

}
