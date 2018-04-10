package authoring.display;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import authoring.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public abstract class AuthoringUIComponent {
	private ResourceBundle myResources;
	private Game myGame;

	
	public AuthoringUIComponent(ResourceBundle resources, Game game) {
		myResources = resources;
		myGame = game;
	}
	
	public Button makeButton (String property, EventHandler<ActionEvent> handler) {
		Button result = new Button();
		String label = "";
		try {
		label = myResources.getString(property);
		}
		catch(MissingResourceException e){
			label = property;
		}
		result.setText(label);
		result.setOnAction(handler);
		return result;
	}
	
	protected ResourceBundle getResources() { //merits of protected vs private?
		return myResources;
	}
	
	//this will be a filler method for buttons and such, before their actual functions are implemented
	public void doNothing() {
		
	}

}
