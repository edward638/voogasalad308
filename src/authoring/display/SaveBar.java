package authoring.display;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SaveBar extends AuthoringUIComponent {
	
	private ResourceBundle myResources;
	private HBox myHBox;

	public SaveBar(ResourceBundle resources) {
		super(resources);
		myHBox = new HBox();
		
		myHBox.getChildren().add(makeButton("NewGameButton", event -> doNothing()));
		myHBox.getChildren().add(makeButton("LoadGameButton", event -> doNothing()));
		myHBox.getChildren().add(makeButton("SaveGameButton", event -> doNothing()));
		for(Node child : myHBox.getChildren()) {
			HBox.setMargin(child, new Insets(4));
		}
	}
	
	public HBox asHBox() {
		return myHBox;
	}

}
