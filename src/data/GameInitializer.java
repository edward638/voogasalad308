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
	private String gameBackgroundImagesLocation;
	private String gameAudioLocation;
    
	public GameInitializer(String gameName) {
		
        gameLocation = baseLocation + gameName + "/";
        gameDescriptionLocation = gameLocation + ResourceBundleManager.getPath("DESCRIPTION");
        gameScenesLocation = gameLocation + ResourceBundleManager.getPath("SCENES");
        gameImagesLocation = gameLocation + ResourceBundleManager.getPath("IMAGES");
        gameBackgroundImagesLocation = gameLocation + ResourceBundleManager.getPath("BACKGROUNDIMAGES");
        gameSavesLocation = gameLocation + ResourceBundleManager.getPath("SAVES");
        gameAudioLocation = gameLocation + ResourceBundleManager.getPath("AUDIO");
        
        if (! new File(gameLocation).exists()){
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
        new File( gameBackgroundImagesLocation).mkdirs();
        new File(gameAudioLocation).mkdirs();
    }
    
    private void addDefaultDescription(String gameName) {
    	String description = "This is a placeholder description.";
    	try {
			GameSaver saver = new GameSaver(gameName);
	    	saver.addDescription(gameName, description);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
		
}
