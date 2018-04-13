package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<GameObject> getSavedGameObjects(){
		ArrayList<GameObject> objectList = new ArrayList<>();
		File directory = new File(defaultObjectLocation);
	
        File[] directoryListing = directory.listFiles();
        ArrayList<GameScene> list = new ArrayList<>();
        
        if (directoryListing != null){
            for (File level : directoryListing){
                String path = level.getAbsolutePath();
                objectList.add(deserializer.getGameObject(path));
            }
        }
        return objectList;
		
	}
	
	public void saveCustomGameObject(GameObject object, String name) throws IOException {
		serializer.saveGameObject(defaultObjectLocation, object, name);
	}
	
	
	
}
