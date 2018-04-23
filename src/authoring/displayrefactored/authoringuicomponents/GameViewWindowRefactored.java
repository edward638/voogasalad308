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
	
	
	private int sizeX;
	private int sizeY;
	private List<ImageView> objectImageViews;
	private StackPane stackPane;
	private Pane backgroundPane;
	private Pane foregroundPane;
	private GameViewWindowController controller;
	private GameViewObservable gameViewObservable = null;
	
	private static final int DEFAULTSIZEX = 1000;
	private static final int DEFAULTSIZEY = 1000;
	
	
	public GameViewWindowRefactored(GameViewWindowController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		objectImageViews = new ArrayList<>();
		updatePaneSize(DEFAULTSIZEX, DEFAULTSIZEY);
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
//		stackPane.getChildren().clear();
		backgroundPane = pane;
//		stackPane.getChildren().add(backgroundPane);
//		stackPane.getChildren().add(foregroundPane);
	}
	
	public void switchPanes(String key) {
		if (key.equals("Background")) {
			stackPane.getChildren().clear();
			stackPane.getChildren().add(backgroundPane);
		} 
		if (key.equals("Foreground")) {
			stackPane.getChildren().clear();
			stackPane.getChildren().add(backgroundPane);
			stackPane.getChildren().add(foregroundPane);
		}
	}
	
	public void updatePaneSize(int x_size, int y_size) {
		sizeX = x_size;
		sizeY = y_size;
		
		backgroundPane.setMinSize(sizeX, sizeY);
		foregroundPane.setMinSize(sizeX, sizeY);
		
		System.out.println("From gameviewwindow refactored: the size should have changed");
	}
}
