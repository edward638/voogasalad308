package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.GameObject;
import authoring.GameObjectAdder;
import authoring.GameScene;

public class GameObjectManager {
	
	private static final String defaultObjectLocation = "./data/gamedata/customobjects/";
	private String key = "block";
	private Deserializer deserializer;
	private Serializer serializer;
	private ImageManager defaultManager;
	private ImageManager gameImageManager;
	private GameObjectAdder levelPanelController;
	
	public GameObjectManager(GameObjectAdder adder, ImageManager imageManager) {
		deserializer = new Deserializer();
		serializer = new Serializer();
		levelPanelController = adder;
		gameImageManager = imageManager;
		defaultManager = new ImageManager("default");
	}
	
	public List<GameObject> getSavedGameObjects(){
		
		List<GameObject> list = new ArrayList<>();
		File directory = new File(defaultObjectLocation + key);
        File[] directoryListing = directory.listFiles();
        
        if (directoryListing != null){
            for (File level : directoryListing){
                String path = level.getAbsolutePath();
                list.add(deserializer.getGameObject(path));
            }
        }
        return list;
	}
	
	public void changeObjectType(String key) {
		this.key = key;
	}
	
	public void saveCustomGameObject(GameObject object, String name) throws IOException {
		serializer.saveGameObject(defaultObjectLocation, object, name);
	}
	
	private void transferImageToGame(String imageName) {
		
	}
	
	public void addObjectToGame(GameObject gameObject) {
		
		levelPanelController.addToCurrentScene(gameObject);
	}
	
	
}
