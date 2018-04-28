package data;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import org.codehaus.groovy.tools.shell.ParseCode;

import authoring.GameScene;
import authoring.SceneBackgroundImageSerializable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageManager {

    private static final String BACKSLASH = "/";
    private static final String BACKGROUNDIMAGES = "backgroundimages";
    private static final String IMAGES = "images";
    private String gameLocation;
    private String gameImagesLocation;
    private String gameBackgroundImagesLocation;
    private static final String defaultImageLocation = "./data/gamedata/defaultimages/";
    private static final String baseLocation = "./data/gamedata/games/";
    private static final String GAMES = "games/";
    private static final String DEFAULT = "default";


    public ImageManager(String gameName){
    	if (gameName.equals(DEFAULT)) {
    		gameImagesLocation = defaultImageLocation;
    	} else {
	        gameLocation = baseLocation + gameName + BACKSLASH;
	        gameImagesLocation = gameLocation + IMAGES + BACKSLASH;
	        gameBackgroundImagesLocation = gameLocation + BACKGROUNDIMAGES + BACKSLASH;
    	}
    }
    
    /**
     * Provides a list of images for game authoring to use.
     * @return list of all images (can be used by 
     */
    public List<Image> getAllImages(){
    	ArrayList<Image> imageList = new ArrayList<>();
    	File directory = new File(gameImagesLocation);
        File[] directoryListing = directory.listFiles();
        
        if (directoryListing != null){
            for (File level : directoryListing){
                String path = level.getName();
                imageList.add(getImage("/" + path));
            }
        }
    	
    	return imageList;
    }

    private BufferedImage getBufferedImage(String imageName, String location) {
        BufferedImage img = null;
        try {	
//        	System.out.println(gameImagesLocation + imageName);
//        	System.out.println(location+imageName);
        	System.out.println("getBufferedImage"  + location+imageName);
            img = ImageIO.read(new File(location + imageName));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: remove this print stacktrace!
            throw new NullPointerException();
        }
        return img;
    }

    /**
     * Retrieves an FX Image from images folder of a game
     * @param imageName name of image desired
     * @return BufferedImage
     */
    public Image getImage(String imageName){
        return bufferedImagetoJavaFXImage(getBufferedImage(imageName, gameImagesLocation));
    }


    private void storeBufferedImage(String imageName, BufferedImage image, String location){
        try {
            System.out.println("storeBufferedImage " + location + imageName);
        	ImageIO.write(image, "png", new File(location + imageName + ".png"));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: remove this print stacktrace!
        }
    }
    
    
    public String storeBackgroundImage(Image image) {
    	ArrayList<Image> imageList = new ArrayList<>();
    	File directory = new File(gameBackgroundImagesLocation);
    	int number = directory.listFiles().length;
    	String background = "background" + number;
    	storeBufferedImage(background, javaFXToBufferedImage(image), gameBackgroundImagesLocation);
    	return background;
    	
    }
    
    public Image getBackgroundImage(String imageName) {
    	return bufferedImagetoJavaFXImage(getBufferedImage(imageName, gameBackgroundImagesLocation));
    }

    /**
     * Stores an FX Image as BufferedImage
     * @param imageName desired name of image
     * @param image to be stored
     */
    public void storeImage(String imageName, Image image){
    	System.out.println("storeImage " + imageName);
        storeBufferedImage(imageName, javaFXToBufferedImage(image), gameImagesLocation);
    }

    /**
     * Converts JavaFX image to BufferedImage
     * @param image
     * @return BufferedImage
     */
    private BufferedImage javaFXToBufferedImage(Image image){
        return SwingFXUtils.fromFXImage(image, null);
    }

    private Image bufferedImagetoJavaFXImage(BufferedImage bufferedImage){
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

}
