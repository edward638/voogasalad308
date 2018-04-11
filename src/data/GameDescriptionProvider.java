package data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import data.propertiesFiles.ResourceBundleManager;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * 
 * @author edwar
 *
 */
public class GameDescriptionProvider {
	
	private String baseLocation = ResourceBundleManager.getPath("BASELOCATION");
	
	public GameDescriptionProvider() {
		
	};

	public String getGameName(String gameName) {
		String gameLocation = baseLocation + gameName + "/";
		String gameDescriptionLocation = gameLocation + ResourceBundleManager.getPath("DESCRIPTION");
		return retrieveStringFromTextFile(gameDescriptionLocation + ResourceBundleManager.getPath("NAME"));
	}


	public String getGameDescription(String gameName) {
		String gameLocation = baseLocation + gameName + "/";
		String gameDescriptionLocation = gameLocation + ResourceBundleManager.getPath("DESCRIPTION");
		return retrieveStringFromTextFile(gameDescriptionLocation + ResourceBundleManager.getPath("DESCRIPTIONTEXT"));
	}

	public Image getDescriptionImage(String gameName) {
		String gameLocation = baseLocation + gameName + "/";
		String gameDescriptionLocation = gameLocation + ResourceBundleManager.getPath("DESCRIPTION");
		
	    try {
	    	BufferedImage bi = null;
	    	bi = ImageIO.read(new File(gameDescriptionLocation + ResourceBundleManager.getPath("DESCRIPTIONIMAGE")));
	    	 return SwingFXUtils.toFXImage(bi, null);
        } catch (IOException e) {
            e.printStackTrace(); //TODO: fix!
        }
        return null;
	}
	
	
    /**
     * Converts a text file into a string
     * @param fileName text file
     * @return string version of text file
     */
    private String retrieveStringFromTextFile(String fileName){
        Scanner scanner = null;
        try {
            scanner = new Scanner( new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //TODO: fix!
        }
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();

        return text;
    }

}
