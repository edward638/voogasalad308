package authoring.display.controllers;

import javafx.scene.layout.Pane;

/**
 * 
 * @author Edward Zhuang
 *
 */
public abstract class PopupController {
	protected abstract void initializeScreenComponents();
	
	protected abstract void setUpConnections();

	protected abstract void addToGUI(Pane pane);
	
	protected abstract void refreshView();
}
