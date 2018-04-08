package Data;

import authoring.GameObject;
import authoring.GameScene;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Serializer {

    private static final String LEVEL = "level";
    private static final String GAMEOBJECT = "gameobject";
    private XStream xstream;

    public Serializer(){
        xstream = new XStream();
    }

    /**
     * Converts game to XML. Used by game authoring environment.
     *
     * @param fileName    desired name of XML game file to be created
     * @param objectMap  map provided from authoring environment which maps GameScene to a list of GameObjects contained on that scene
     */
    public void gameAuthorToXML(String fileName, Map<GameScene, List<GameObject>> objectMap) throws IOException {
        String topLevelGameDestination = "./src/Data/XMLFiles/" + fileName;
        new File(topLevelGameDestination).mkdirs();

        int x = 1;
        for (GameScene key: objectMap.keySet()){
            String levelGameDestination = topLevelGameDestination + "/" + LEVEL + x;
            for (GameObject gameObject: objectMap.get(key)){
                String xmlString = xstream.toXML(gameObject);
                stringToDom(xmlString, levelGameDestination + "/" + gameObject.getName() + ".xml");
            }
            x++;
        }
    }

    // taken from https://stackoverflow.com/questions/17853541/java-how-to-convert-a-xml-string-into-an-xml-file

    private static void stringToDom(String xmlSource, String fileName)
            throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter(fileName);
        fw.write(xmlSource);
        fw.close();
    }

    void testGameAuthorToXML(String fileName, TestObject gameObjects) throws IOException {
        String test = xstream.toXML(gameObjects);
        stringToDom(test, "./src/Data/XMLFiles/hello/test.xml");
        System.out.println(test);
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
     * Retrieves game information from an XML file specified by an inputted string
     * @param fileName name of game file
     * @return XML file of specified game
     */
//    File getXMLFile(String fileName) {
//        File file = new File();
//        return new File();
//    }


      public static void main(String args[]){
          Serializer serializer = new Serializer();

          String[] stringList = {"hello", "goodbye", "yo", "hello"};
          TestObject testObject = new TestObject(3, "biiiiich", stringList, "Goomba.png");
          try {
              serializer.testGameAuthorToXML("hello", testObject);
          } catch (IOException e) {
              e.printStackTrace();
          }

//


      }

	public String getGameName(String file) {
		// TODO Auto-generated method stub
		return null;
	}
}
