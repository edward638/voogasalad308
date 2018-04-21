package authoring.displayrefactored;

import authoring.Game;
import javafx.scene.layout.Pane;

public class AuthoringEnvironmentRefactored {

	private AuthoringEnvironmentGUIRefactored authoringEnvironmentGUIRefactored;
	
	public AuthoringEnvironmentRefactored(Game game) {
	
		authoringEnvironmentGUIRefactored = new AuthoringEnvironmentGUIRefactored();
		
	}
	
	public Pane getGUI() {
		return authoringEnvironmentGUIRefactored.getPane();
		
	}
	
}
