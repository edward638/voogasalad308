package authoring.displayrefactored.authoringuicomponents;

import java.util.List;
import java.util.Observer;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameViewWindowRefactored extends AuthoringUIComponentRefactored implements Observer {

	List<ImageView> objectImageViews;
	StackPane stackPane;
	Pane backgroundPane;
	Pane foregroundPane;
	
	public GameViewWindowRefactored() {
		// TODO Auto-generated constructor stub
		stackPane = new StackPane();
		backgroundPane = new Pane();
		foregroundPane = new Pane();
		
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		borderPane.getChildren().add(stackPane);
		
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
