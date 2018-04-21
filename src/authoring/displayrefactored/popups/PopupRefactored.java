package authoring.displayrefactored.popups;

import javafx.scene.Scene;
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
	
	protected void open(int xSize, int ySize) {
		Scene scene = new Scene(borderPane, xSize, ySize);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
	
	protected abstract void GeneratePopup();
	
	protected BorderPane getPane() {
		return borderPane;
	}
	
	protected abstract void mapButtons();
	
	protected void close() {
		stage.close();
	}
}
