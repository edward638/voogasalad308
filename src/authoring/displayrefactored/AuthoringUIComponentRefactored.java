package authoring.displayrefactored;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Edward Zhuang
 *
 */
public abstract class AuthoringUIComponentRefactored {

	private BorderPane borderPane;
	
	public AuthoringUIComponentRefactored() {
		borderPane = new BorderPane();
	}
	
	public void GenerateComponent() {
		
	}
	
	public BorderPane getUIComponent() {
		
		return borderPane;
	}
	
	protected BorderPane getBorderPane() {
		return borderPane;
	}
	
	public void AttachToPane(Pane pane, int xPosition, int yPosition) {
		
	}
	
}
