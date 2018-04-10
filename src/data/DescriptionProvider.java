package data;

import javax.imageio.ImageIO;
import java.awt.*;

public interface DescriptionProvider {
	
	 /**
     * Gets name of the game
     * @return name of game
     */
	String getGameName();
	
	/**
     * Gets description of game
     * @return description of game
     */
	String getGameDescription();
	
	/**
     * Gets a game description image
     * @return game description image
     */
	Image getDescriptionImage();
	
}
