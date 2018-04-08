package authoring.display;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SaveBar extends AuthoringUIComponent {
	
	private HBox myHBox;

	public SaveBar(ResourceBundle resources) {
		super(resources);
		myHBox = new HBox();
		
		myHBox.getChildren().addAll(makeNewGameButton(), makeLoadGameButton(), makeSaveGameButton());
		for(Node child : myHBox.getChildren()) {
			HBox.setMargin(child, new Insets(4));
		}
	}
	
	public HBox asHBox() {
		return myHBox;
	}
	
	private Button makeNewGameButton() {
		return makeButton("NewGameButton", event -> doNothing());
	}
	
	private Button makeLoadGameButton() {
		return makeButton("LoadGameButton", event -> doNothing());
	}
	
	private Button makeSaveGameButton() {
		return makeButton("SaveGameButton", event -> doNothing());
	}

}
