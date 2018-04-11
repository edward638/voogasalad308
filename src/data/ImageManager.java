package data;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {

    private static final String BACKSLASH = "/";
    private static final String IMAGES = "images";
    private String gameLocation;
    private String gameImagesLocation;
    private static final String baseLocation = "./data/gamedata/games/";


    public ImageManager(String gameName){
        gameLocation = baseLocation + gameName + BACKSLASH;
        gameImagesLocation = gameLocation + IMAGES + BACKSLASH;
    }

    private BufferedImage getBufferedImage(String imageName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(gameImagesLocation + imageName));
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
        return bufferedImagetoJavaFXImage(getBufferedImage(imageName));
    }


    private void storeBufferedImage(String imageName, BufferedImage image){
        try {
            ImageIO.write(image, "png", new File(gameImagesLocation + imageName + ".png"));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: remove this print stacktrace!
        }
    }

    /**
     * Stores an FX Image as BufferedImage
     * @param imageName desired name of image
     * @param image to be stored
     */
    public void storeImage(String imageName, Image image){
        storeBufferedImage(imageName, javaFXToBufferedImage(image));
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
