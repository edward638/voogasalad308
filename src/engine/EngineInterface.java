package engine;

import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;

/**
 * @author Yashas Manjunatha and Gouttham Chandraekar
 * Public interface dictating the access the GamePlayer has to the GameEngine.
 */
public interface EngineInterface {

	/**
	 * Closes this instance of the Engine and performs clean up events.
	 */
	public void close();
	
	/**
	 * Pauses the game play.
	 */
	public void pause();
	
	/**
	 * Resumes the game play.
	 */
	public void play();
	
	/**
	 * Gets the display of the game/Engine. All the elements of the display are populated onto one JavaFX node.
	 * @return SubScene JavaFX Node
	 */
	public SubScene getDisplay();
	
	/**
	 * Method to handle key input events and raise appropriate events 
	 * during the game to be handled by the Engine.
	 * @param code Key Code Pressed
	 */
	public void handleKeyInput(KeyCode code);
	
	/**
	 * Method to handle mouse input events and raise appropriate events 
	 * during the game to be handled by the Engine.
	 * @param x X location of mouse click.
	 * @param y Y location of mouse click.
	 */
	public void handleMouseInput(double x, double y);
	
	/**
	 * Set volume of the audio of the game.
	 * @param newVolume New Volume Level (double between 0 to 1)
	 */
	public void setVolume(double newVolume);
	
	/**
	 * Saves the current state of the game to game data.
	 */
	public void save();
}
