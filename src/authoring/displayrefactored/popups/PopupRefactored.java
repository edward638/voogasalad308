package authoring.displayrefactored.popups;

import authoring.display.NewComponentWindow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
	
public abstract class PopupRefactored {
		
	private Stage stage;
	private BorderPane borderPane;
	
	public PopupRefactored() {
		
		stage = new Stage();
		borderPane = new BorderPane();
		
	}
	
	protected abstract void GeneratePopup();
	
	protected BorderPane getPane() {
		return borderPane;
	}
	
	
}
