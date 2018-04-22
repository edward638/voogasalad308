package authoring.displayrefactored.authoringuicomponents;

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
		GenerateComponent();
	}
	
	protected abstract void GenerateComponent();
	
	protected BorderPane getBorderPane() {
		return borderPane;
	}
	
	public void AttachToPane(Pane pane, int xPosition, int yPosition) {
		borderPane.setLayoutX(xPosition);
		borderPane.setLayoutY(yPosition);
		pane.getChildren().add(borderPane);
	}
	
}
