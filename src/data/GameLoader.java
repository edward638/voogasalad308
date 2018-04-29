package data;

import authoring.GameObject;
import authoring.GameScene;
import authoring.GameSceneSerializable;
import data.propertiesFiles.ResourceBundleManager;
import engine.GamePart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GameLoader {

    private Deserializer deserializer;
    private String baseLocation = ResourceBundleManager.getPath("BASELOCATION");
    private String gameLocation;
    private String gameDescriptionLocation;
    private String gameScenesLocation;
    private String gameSavesLocation;


    /**
     * GameLoader constructor
     * @param fileName name of games root file
     */
    public GameLoader(String gameName){
        deserializer = new Deserializer();
        gameLocation = baseLocation + gameName + "/";
        gameDescriptionLocation = gameLocation + ResourceBundleManager.getPath("DESCRIPTION");
        gameScenesLocation = gameLocation + ResourceBundleManager.getPath("SCENES");
        gameSavesLocation = gameLocation + ResourceBundleManager.getPath("SAVES");
    }


    /**
     * Gets map for game authoring/game engine to use to load game
     * @return map
     */
    public List<GameScene> getGameScenes(){
    	List<GameScene> list = new ArrayList<>();
    		System.out.println("getGameScenes: " + gameScenesLocation);
    		System.out.println(deserializer.getGameSceneSerializables(gameScenesLocation));
        for (GameSceneSerializable s : deserializer.getGameSceneSerializables(gameScenesLocation)) {
        	list.add(new GameScene(s));
        }
        return list;
    }

    public GamePart getGamePart(){
    	return deserializer.getSavePart(gameSavesLocation);
    }

}
