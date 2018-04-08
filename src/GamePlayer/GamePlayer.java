package GamePlayer;

import java.io.IOException;

import javafx.scene.Scene;

public interface GamePlayer {
		

	/**
	 * called when a new game is started or old game loaded.
	 * initialises a new game engine with parameters appropriate to the specific game.
	 * throws an exception if the string passed in does not represent one of the games in the data files.
	 * @param game to be played
	 */
	 void playGame(String game);
	 
	 /**
	  * saves game, uses XML writer to write a file containing all current positions of game elements.
	 * @throws IOException 
	  * 
	  */
	 void saveGame() throws IOException;
	 
	 /**
	  * allows the display to retrieve the scene associated the game player
	  * @return scene to be displayed
	  */
	 public Scene getScene();
	 
}
