package Data;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.util.List;

public class Serializer {

    private XStream xstream;

    public Serializer(){
        xstream = new XStream();
        System.out.println("hi");
    }

    /**
     * Converts game to XML. Used by game authoring environment.
     *
     * @param fileName    desired name of XML game file to be created
     * @param gameObjects list of GameObjects which the game is comprised of
     */
    void gameAuthorToXML(String fileName, TestObject gameObjects) {
        String test = xstream.toXML(gameObjects);
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
          TestObject testObject = new TestObject(3, "biiiiich", stringList);

          serializer.gameAuthorToXML("hello", testObject);


      }
}
