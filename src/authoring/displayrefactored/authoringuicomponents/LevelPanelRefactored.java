package authoring.displayrefactored.authoringuicomponents;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class LevelPanelRefactored extends AuthoringUIComponentRefactored implements Observer{

	Pane pane;
	
	public LevelPanelRefactored() {
		// TODO Auto-generated constructor stub
		pane = new Pane();
	
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		borderPane.setCenter(pane);
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
