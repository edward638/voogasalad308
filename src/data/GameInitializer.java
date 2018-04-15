package data;

import java.io.File;

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
		
}
