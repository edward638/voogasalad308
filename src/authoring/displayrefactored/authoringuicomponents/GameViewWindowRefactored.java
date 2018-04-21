package authoring.displayrefactored.authoringuicomponents;

import java.util.List;
import java.util.Observer;

import authoring.displayrefactored.controllers.GameViewWindowController;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameViewWindowRefactored extends AuthoringUIComponentRefactored implements Observer {

	List<ImageView> objectImageViews;
	StackPane stackPane;
	Pane backgroundPane;
	Pane foregroundPane;
	GameViewWindowController controller;
	
	public GameViewWindowRefactored(GameViewWindowController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		stackPane = new StackPane();
		backgroundPane = new Pane();
		foregroundPane = new Pane();
		stackPane.setStyle("-fx-border-color: black");
		stackPane.setPrefSize(800, 800);
		borderPane.setCenter(stackPane);
		
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
