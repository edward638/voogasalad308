package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.GameObject;
import authoring.GameScene;

public class GameObjectManager {
	
	private static final String defaultObjectLocation = "./data/gamedata/customobjects/";
	private Deserializer deserializer;
	private Serializer serializer;
	
	public GameObjectManager() {
		deserializer = new Deserializer();
		serializer = new Serializer();
	}
	
	public Map<String,GameObject> getSavedGameObjects(){
		TreeMap<String,GameObject> map = new TreeMap<>();
		
		File directory = new File(defaultObjectLocation);
	
        File[] directoryListing = directory.listFiles();
        
        if (directoryListing != null){
            for (File level : directoryListing){
                String path = level.getAbsolutePath();
               map.put(deserializer.getGameObject(path).getName(), deserializer.getGameObject(path));
            }
        }
        return map;
		
	}
	
	public void saveCustomGameObject(GameObject object, String name) throws IOException {
		serializer.saveGameObject(defaultObjectLocation, object, name);
	}
	
	
	
}
