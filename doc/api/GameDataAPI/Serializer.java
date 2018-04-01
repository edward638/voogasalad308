public interface Serializer {

    // Converts game to XML. can be used by both authoring environment and game engine for saving game.
    void gameAuthorToXML(String location, Object[] gameObjects);

    void gameEngineToXML(String location, Object[] gameObjects);

    // Retrieves game information from an XML file specified by an inputted string
    File getXMLFile(String fileName);

}