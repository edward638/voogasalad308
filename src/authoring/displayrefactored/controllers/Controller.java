package authoring.displayrefactored.controllers;

import authoring.displayrefactored.AuthoringEnvironmentGUIRefactored;
import display.buttons.GUIButton;

public abstract class Controller {

	public Controller() {
		
	}
	
	protected abstract void initializeScreenComponents();
	
	protected abstract void setUpConnections();

	protected abstract void addToGUI(AuthoringEnvironmentGUIRefactored gui);
	
}


