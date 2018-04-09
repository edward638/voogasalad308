package data;

import authoring.GameObject;
import authoring.GameScene;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameSaver {

    private Serializer serializer;
    private static final String baseLocation = "./data/gamedata/games/";
    private String gameLocation;
    private String gameDescriptionLocation;
    private String gameLevelLocation;
    private String gameLevelSceneLocation;
    private String gameLevelObjectsLocation;
    private String gameImagesLocation;

    /**
     * GameSaver constructor
     * @param gameName desired name of game root file
     */
    public GameSaver(String gameName){
        gameLocation = baseLocation + gameName + "/";
        gameDescriptionLocation = gameLocation + "description" + "/";
        gameLevelLocation = gameLocation + "levels" + "/";
        gameLevelSceneLocation = gameLevelLocation + "scene" + "/";
        gameLevelObjectsLocation = gameLevelLocation + "objects" + "/";
        gameImagesLocation = gameLocation + "images" + "/";
        serializer = new Serializer();

        if (! new File(gameLocation).exists()){
            makeDirectories();
        }
    }

    /**
     * makes directories for a game
     */
    private void makeDirectories(){
        new File(gameLocation).mkdirs();
        new File(gameDescriptionLocation).mkdirs();
        new File(gameLevelLocation).mkdirs();
//        new File(gameLevelObjectsLocation).mkdirs();
//        new File(gameLevelSceneLocation).mkdirs();
        new File(gameImagesLocation).mkdirs();
    }

    /**
     * provides a description of the game
     * @param name of game
     * @param description description of game
     * @param descriptionImage thumbnail image to represent game
     */
    public void addDescription(String name, String description, BufferedImage descriptionImage) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(gameDescriptionLocation + "name.txt")){
            out.println(name);
        }
        try (PrintWriter out = new PrintWriter(gameDescriptionLocation + "description.txt")){
            out.println(description);
        }
        try {
            ImageIO.write(descriptionImage, "jpg", new File(gameDescriptionLocation + "descriptionImage.jpg"));
        } catch (IOException e) {
            e.printStackTrace(); //TODO: remove this print stacktrace!
        }

    }
    /**
     * Saves the game that authoring environment has created.
     * A folder is created for each level, and in that folder the scene is created
     * along with xml files for each game object
     * @param objectMap map of gamescenes (levels) to game objects in that level
     */
    public void gameAuthorToXML(List<GameScene> gameSceneList) throws IOException {
        serializer.gameAuthorToXML(gameLevelLocation, gameSceneList);
    }

    /**
     * Saves the game when the game is being played in game engine
     */
    public void saveGameState() {

    }

}
