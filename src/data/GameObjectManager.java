package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.GameObjectAdder;
import authoring.GameScene;
import authoring.Property;
import authoring.display.authoringuicomponents.ObjectLibrary;
import authoring.display.objectinfoboxes.LibraryObjectInfoBox;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class GameObjectManager implements LibraryObjectSaver, LibraryObservable {
	
	private static final String defaultObjectLocation = "./data/gamedata/customobjects/";
	private String key = "block";
	private Deserializer deserializer;
	private Serializer serializer;
	private ImageManager defaultManager;
	private ImageManager gameImageManager;
	private GameObjectAdder levelPanelController;
	private GameObject currentObject;
	private ObjectLibrary library;
	private LibraryObserver observer;
	
	public GameObjectManager(GameObjectAdder adder, ImageManager imageManager) {
		deserializer = new Deserializer();
		serializer = new Serializer();
		levelPanelController = adder;
		gameImageManager = imageManager;
		defaultManager = new ImageManager("default");
		library = new ObjectLibrary(this);
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
	
	public void setObserver(LibraryObserver observer) {
		this.observer = observer;
	}
	
	public void changeObjectType(String key) {
		this.key = key;
		library.updateObjectList(getSavedGameObjects());
	}
	
	public void setCurrentObject(GameObject gameObject) {
		this.currentObject = gameObject;
		observer.notifyObserver();
	}
	
	public void saveCustomGameObject(String name, Image image) throws IOException {
		defaultManager.storeImage(name+"image",image);
		GameObject gameObject = new GameObject();
		AuthBehavior mandatory = gameObject.getMandatoryBehavior();
		gameObject.setName(name);
		mandatory.getProperty("xPos").setValue(0.0);
		mandatory.getProperty("yPos").setValue(0.0);
		mandatory.getProperty("imagePath").setValue(name+"image");
		System.out.println(defaultObjectLocation+key);
		serializer.saveGameObject(defaultObjectLocation + key + "/", gameObject, name);
		library.updateObjectList(getSavedGameObjects());
	}
	
	private void transferImageToGame(String imageName) {
		Image libraryImage = defaultManager.getImage(imageName + ".png");
		gameImageManager.storeImage(imageName, libraryImage);
	}
	
	public void addObjectToGame() {
		GameObject go = new GameObject(currentObject);
		Property imagePathProperty = currentObject.getMandatoryBehavior().getProperty("imagePath");
		String string = ((String) imagePathProperty.getValue());
		System.out.println("AddObjectToGame " + string);
		transferImageToGame(string);
		levelPanelController.addToCurrentScene(go);
	}
	
	public boolean checkUniqueName(String name) {
		boolean isUnique = true;
		String originalKey = key;
		
		String[] keys = {"player","block","npc"};
		for (int x = 0; x < keys.length; x++) {
			key = keys[x];
			for (GameObject go: getSavedGameObjects()) {
				if (name.equals(go.getName())) {
					isUnique = false;
				}
			}
		}
		key = originalKey;
		return isUnique;
	}
	
	public void addToGUI(Pane pane) {
		library.attachToPane(pane, ResourceBundleManager.getPosition("LIBRARY_X"), ResourceBundleManager.getPosition("LIBRARY_Y"));
		System.out.println("attached");
	}

	@Override
	public void saveUpdatedLibraryObject(GameObject gameObject) {
		// TODO Auto-generated method stub
		try {
			serializer.saveGameObject(defaultObjectLocation + key + "/", gameObject, gameObject.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		library.updateObjectList(getSavedGameObjects());
	}

	@Override
	public LibraryObjectInfoBox getLibraryObjectInfoBox() {
		// TODO Auto-generated method stub
		List<GameObject> allObjects = new ArrayList<>();
		
		String[] keys = {"player","block","npc"};
		for (int x = 0; x < keys.length; x++) {
			key = keys[x];
			for (GameObject go: getSavedGameObjects()) {
				allObjects.add(go);
			}
		}
		String imageName = currentObject.getName() + "image.png";
		
		return new LibraryObjectInfoBox(currentObject, allObjects, this, defaultManager.getImage(imageName));
	}
	
	
}
