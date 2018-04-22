package authoring.displayrefactored.controllers;

import authoring.displayrefactored.AuthoringEnvironmentGUIRefactored;
import display.buttons.GUIButton;
import javafx.scene.layout.Pane;

public abstract class Controller {

	public Controller() {
		
	}
	
	protected abstract void initializeScreenComponents();
	
	protected abstract void setUpConnections();

	protected abstract void addToGUI(Pane pane);
	
	protected abstract void refreshView();
	
}


