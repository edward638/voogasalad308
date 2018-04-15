package data;

import authoring.GameObject;
import authoring.GameScene;
import engine.GameState;

import com.thoughtworks.xstream.XStream;
//
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Serializer {

    private static final String SCENE = "scene";
    private static final String SAVE = "save";
    private XStream xstream;

    public Serializer() {
        xstream = new XStream();
    }

    /**
     * Converts game to XML. Used by game authoring environment.
     *
     * @param fileName  desired name of XML game file to be created
     * @param gameSceneList map provided from authoring environment which maps GameScene to a list of GameObjects contained on that scene
     */
    //TODO: fix! maybe have the gamescene have a name so we know what the level is?
//    void gameAuthorToXML(String fileName, Map<GameScene, List<GameObject>> objectMap) throws IOException {
//        String topLevelGameDestination = fileName;
//        new File(topLevelGameDestination).mkdirs();
//
//        int x = 1;
//        for (GameScene key : objectMap.keySet()) {
//            String levelGameDestination = topLevelGameDestination + "/" + LEVEL + x;
//            for (GameObject gameObject : objectMap.get(key)) {
//                String xmlString = xstream.toXML(gameObject);
//                stringToDom(xmlString, levelGameDestination + "/" + gameObject + ".xml");
//            }
//            x++;
//        }
//    }

    public void gameAuthorToXML(String fileName, List<GameScene> gameSceneList) throws IOException {
        String topLevelGameDestination = fileName;
        new File(topLevelGameDestination).mkdirs();

        for (GameScene aGameSceneList : gameSceneList) {
            String levelGameDestination = topLevelGameDestination + "/" + SCENE + gameSceneList.indexOf(aGameSceneList);
            String xmlString = xstream.toXML(aGameSceneList);
            stringToDom(xmlString, levelGameDestination + ".xml");
        }
    }
    
  

    // taken from https://stackoverflow.com/questions/17853541/java-how-to-convert-a-xml-string-into-an-xml-file

    private void stringToDom(String xmlSource, String fileName)
            throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter(fileName);
        fw.write(xmlSource);
        fw.close();
    }

    /**
     * Converts game to XML. Used by game engine.
     *
     * @param fileName    desired name of XML game file to be created
     * @param gameObjects list of GameObjects which the game is comprised of
     */
    void gameEngineToXML(String fileName, List<Object> gameObjects) {

    }
    
    /**
     * 
     * @param fileName
     * @param gameState
     */
    public void saveStateToXML(String fileName, GameState gameState) throws IOException {
    	int x = 1;
    	String topLevelGameDestination = fileName;
    	String xmlString = xstream.toXML(gameState);
    	String levelGameDestination = topLevelGameDestination + "/" + SAVE;
        stringToDom(xmlString, levelGameDestination + ".xml");
    }

	public void saveGameObject(String location, GameObject object, String name) throws IOException {
		// TODO Auto-generated method stub
         String xmlString = xstream.toXML(object);
         stringToDom(xmlString, location + name  + ".xml");
	}
    
//    private int checkNumberOfSaves() {
//    	
//    };
    

    /**
     * Retrieves game information from an XML file specified by an inputted string
     * @param fileName name of game file
     * @return XML file of specified game
     */
//    File getXMLFile(String fileName) {
//        File file = new File();
//        return new File();
//    }

}


