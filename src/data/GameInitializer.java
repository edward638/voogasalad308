package data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.propertiesFiles.ResourceBundleManager;

/**
 * Used when a game is first made. Creates all the necessary game files
 * @author Edward Zhuang
 *
 */
public class GameInitializer {
	
    private String baseLocation = ResourceBundleManager.getPath("BASELOCATION");
    private String gameLocation;
    private String gameDescriptionLocation;
    private String gameScenesLocation;
    private String gameImagesLocation;
	private String gameSavesLocation;
    
	public GameInitializer(String gameName) {
		
        gameLocation = baseLocation + gameName + "/";
        gameDescriptionLocation = gameLocation + ResourceBundleManager.getPath("DESCRIPTION");
        gameScenesLocation = gameLocation + ResourceBundleManager.getPath("SCENES");
        gameImagesLocation = gameLocation + ResourceBundleManager.getPath("IMAGES");
        gameSavesLocation = gameLocation + ResourceBundleManager.getPath("SAVES");

        if ( new File(gameLocation).exists()){
        	System.out.println("game exists already!");
        } else {
        	 makeDirectories();
        	 addDefaultDescription(gameName);
        }
		
	}
	
    /**
     * makes directories for a game
     */
    private void makeDirectories(){
        new File(gameLocation).mkdirs();
        new File(gameDescriptionLocation).mkdirs();
        new File(gameScenesLocation).mkdirs();
        new File(gameImagesLocation).mkdirs();
        new File(gameSavesLocation).mkdirs();
    }
    
    private void addDefaultDescription(String gameName) {
    	String description = "This is a placeholder description.";
    	try {
			BufferedImage img = ImageIO.read(new File(ResourceBundleManager.getPath("DEFAULTIMAGE")));
			GameSaver saver = new GameSaver(gameName);
	    	saver.addDescription(gameName, description, img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
		
}
