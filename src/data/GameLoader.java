package data;

import authoring.GameObject;
import authoring.GameScene;
import data.propertiesFiles.ResourceBundleManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GameLoader implements DescriptionProvider {

    private Deserializer deserializer;
    private String baseLocation = ResourceBundleManager.getPath("BASELOCATION");
    private String gameLocation;
    private String gameDescriptionLocation;
    private String gameScenesLocation;


    /**
     * GameLoader constructor
     * @param fileName name of games root file
     */
    public GameLoader(String gameName){
        deserializer = new Deserializer();
        gameLocation = baseLocation + gameName + "/";
        gameDescriptionLocation = gameLocation + ResourceBundleManager.getPath("DESCRIPTION");
        gameScenesLocation = gameLocation + ResourceBundleManager.getPath("SCENES");
    }

    /**
     * Gets name of the game
     * @return name of game
     */
    public String getGameName(){
        return retrieveStringFromTextFile(gameDescriptionLocation + ResourceBundleManager.getPath("NAME"));
    }

    /**
     * Gets description of game
     * @return description of game
     */
    public String getGameDescription(){
        return retrieveStringFromTextFile(gameDescriptionLocation + ResourceBundleManager.getPath("DESCRIPTIONTEXT"));
    }

    /**
     * Gets a game description image
     * @return game description image
     */
    public Image getDescriptionImage(){
        try {
            return ImageIO.read(new File(gameDescriptionLocation + ResourceBundleManager.getPath("DESCRIPTIONIMAGE")));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: fix!
        }
        return null;
    }

    /**
     * Gets map for game authoring/game engine to use to load game
     * @return map
     */
    public List<GameScene> getGameScenes(){
        return deserializer.getGameScenes(gameScenesLocation);
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
