package data;

import authoring.GameObject;
import authoring.GameScene;
import engine.GamePart;
import authoring.GameSceneSerializable;
import engine.GameState;

import com.thoughtworks.xstream.XStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.*;

/**
 * Class used to deserialize XML files. Used by the GameLoader class.
 */
public class Deserializer {

    private Map<GameScene, List<GameObject>> objectMap;
    private XStream xstream;
    private static final String SAVE = "save";
    
    public Deserializer(){
        xstream = new XStream();
    }

    /**
     * Creates the gameMap used by GameAuthoringEngine to produce the game
     * @param filename file name of the levels directory
     * @return map with gamescene mapped to list of game objects
     */
//    public Map<GameScene, List<GameObject>> getGameMap(String filename){
//        File directory = new File(filename);
//        File[] directoryListing = directory.listFiles();
//        objectMap = new TreeMap<>();
//        if (directoryListing != null){
//            for (File level : directoryListing){
//                String path = level.getAbsolutePath();
////                System.out.println(path);
//                List<GameObject> objectList = retrieveGameObjectsFromLevel(path + "/" + "objects" + "/");
//                GameScene gameScene = retrieveGameScene(path + "/" + "scene.xml");
//                objectMap.put(gameScene, objectList);
//            }
//        }
//        return objectMap;
//    }

    public List<GameSceneSerializable> getGameSceneSerializables(String filename){
        File directory = new File(filename);
        File[] directoryListing = directory.listFiles();
        List<GameSceneSerializable> list = new ArrayList<>();
        
        if (directoryListing != null){
            for (File level : directoryListing){
                String path = level.getAbsolutePath();
//                System.out.println(path);
                list.add(retrieveGameSceneSerializable(path));
            }
        }
        return list;
    }
    
    public GameObject getGameObject(String fileName) {
    	 File file = new File(fileName);
         return (GameObject) xstream.fromXML(convertXMLFileToString(file));
    }

    
    /**
     * gets a list of game objects for getGameMap
     * @param fileName directory with game objects
     * @return list of game objects
     */
    private List<GameObject> retrieveGameObjectsFromLevel(String fileName){
        List<GameObject> list = new ArrayList<>();
        File directory = new File(fileName);
        File[] directoryListing = directory.listFiles();
        if (directoryListing != null){
            for (File child : directoryListing){
                GameObject gameObject = (GameObject) xstream.fromXML(convertXMLFileToString(child));
                list.add(gameObject);
            }
        }
        return list;
    }

    /**
     * Gets a gamescene for getGameMap
     * @param fileName name of gamescene file;
     * @return GameScene
     */
    private GameSceneSerializable retrieveGameSceneSerializable(String fileName) {
        File file = new File(fileName);
        return (GameSceneSerializable) xstream.fromXML(convertXMLFileToString(file));
    }
    
    public GamePart getSavePart(String fileName) {
    	String saveState = fileName + "/" + SAVE + ".xml";
    	File file = new File(saveState);
        return (GamePart) xstream.fromXML(convertXMLFileToString(file));
    }
   

    /**
     * converts a file to string. taken from http://techdiary.bitourea.com/2008/07/convert-xml-file-to-xml-string-in-java.html
     * @param file desired xml file
     * @return string of xml
     */
    private String convertXMLFileToString(File file)
    {
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            InputStream inputStream = new FileInputStream(file);
            org.w3c.dom.Document doc = documentBuilderFactory.newDocumentBuilder().parse(inputStream);
            StringWriter stw = new StringWriter();
            Transformer serializer = TransformerFactory.newInstance().newTransformer();
            serializer.transform(new DOMSource(doc), new StreamResult(stw));
            return stw.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
